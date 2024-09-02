package io.demoprojects.dak.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import io.demoprojects.dak.dto.MainRequestDto;
import io.demoprojects.dak.model.Directorate;
import io.demoprojects.dak.model.MainRequisition;
import io.demoprojects.dak.model.MainRequisitionStatusHistory;
import io.demoprojects.dak.model.Requisition;
import io.demoprojects.dak.model.RequisitionItem;
import io.demoprojects.dak.model.RequisitionStatusHistory;
import io.demoprojects.dak.model.RequisitionUser;
import io.demoprojects.dak.repository.DirectorateRepository;
import io.demoprojects.dak.repository.MainRequisitionRepo;
import io.demoprojects.dak.repository.MainRequisitionStatusHistoryRepo;
import io.demoprojects.dak.repository.RequisitionItemRepository;
import io.demoprojects.dak.repository.RequisitionRepository;
import io.demoprojects.dak.repository.RequisitionStatusHistoryRepository;
import io.demoprojects.dak.repository.RequisitionUserRepository;
import io.demoprojects.dak.util.RequisitionDetailDto;
import io.demoprojects.dak.util.RequisitionUpdateDto;
import jakarta.transaction.Transactional;


	@Service
	public class RequisitionServiceImpl implements IRequisitionService {

	    @Autowired
	    private RequisitionRepository requisitionRepository;

	    @Autowired
	    private RequisitionUserRepository requisitionUserRepository;

	    @Autowired
	    private DirectorateRepository directorateRepository;
	    
	    @Autowired
	    private RequisitionStatusHistoryRepository requisitionStatusHistoryRepository;
	    
	    @Autowired
	    private RequisitionItemRepository requisitionItemRepository;
	    
	    @Autowired
	    private MainRequisitionStatusHistoryRepo statusHistoryRepo;
	    
	    @Autowired
	    private MainRequisitionRepo mainRequisitionRepo;
	    
	    @Override
	    public Requisition saveRequisition(Requisition requisition) {
	        return requisitionRepository.save(requisition);
	    }

	    @Override
	    public Optional<Requisition> getRequisition(Long requestId) {
	        return requisitionRepository.findById(requestId);
	    }

//	    @Override
//	    public void deleteRequisition(Long requestId) {
//	        requisitionRepository.deleteById(requestId);
//	    }

	    @Transactional
	    public void createRequisition(RequisitionDetailDto createDto) {
	        // Check if directorate exists, otherwise create it
	        Directorate directorate = directorateRepository.findByDirectorate(createDto.getDirectorate())
	                .orElseGet(() -> {
	                    Directorate newDirectorate = new Directorate();
	                    newDirectorate.setDirectorate(createDto.getDirectorate());
	                    // Set other properties if needed
	                    System.out.println("Directorate : :"+newDirectorate.getDirectorate());
	                    return directorateRepository.save(newDirectorate);
	                });

	        // Check if user exists, otherwise create it
	        RequisitionUser user = requisitionUserRepository.findByUsername(createDto.getUsername())
	                .orElseGet(() -> {
	                    RequisitionUser newUser = new RequisitionUser();
	                    newUser.setUsername(createDto.getUsername());
	                    newUser.setDirectorate(directorate);
	                    return requisitionUserRepository.save(newUser);
	                });

	        // Create and save the requisition
	        Requisition requisition = new Requisition();
	        requisition.setUser(user);
	        requisition.setRequestdate(createDto.getRequestdate());
	        requisitionRepository.save(requisition);

	        // Create and save the requisition item
	        RequisitionItem requisitionItem = new RequisitionItem();
	        requisitionItem.setRequisition(requisition);
	        requisitionItem.setItemtype(createDto.getItemtype());
	        requisitionItem.setItemspec(createDto.getItemspec());
	        requisitionItem.setReqqty(createDto.getReqqty());
	        requisitionItemRepository.save(requisitionItem);

	        // Create and save the requisition status history
	        RequisitionStatusHistory statusHistory = new RequisitionStatusHistory();
	        statusHistory.setRequisition(requisition);
	        statusHistory.setStatus(createDto.getStatus());
	        statusHistory.setStatusChangeDate(new Date());
	        requisitionStatusHistoryRepository.save(statusHistory);
	    }
	    
//	    @Override
//	    public Requisition updateRequisition(Long requestId, RequisitionDetailDto requisitionDetailDto) {
//	        Optional<Requisition> requisitionOpt = requisitionRepository.findById(requestId);
//	        if (requisitionOpt.isPresent()) {
//	            Requisition requisition = requisitionOpt.get();
//	            requisition.setRequestdate(requisitionDetailDto.getRequestdate());
//	            // Update other fields
//	            return requisitionRepository.save(requisition);
//	        } else {
//	            throw new RuntimeException("Requisition not found");
//	        }
//	    }
	    @Override
	    public Requisition updateRequisition(Long id, RequisitionUpdateDto updateDto) throws ResourceNotFoundException {
	        Requisition requisition = requisitionRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Requisition not found for this id :: " + id));

	        // Update fields only if they are present in the DTO
	        if (updateDto.getUsername() != null) {
	            RequisitionUser user = requisitionUserRepository.findByUsername(updateDto.getUsername())
	                    .orElseGet(() -> {
	                        RequisitionUser newUser = new RequisitionUser();
	                        newUser.setUsername(updateDto.getUsername());
	                        return requisitionUserRepository.save(newUser);
	                    });
	            requisition.setUser(user);
	        }

	        if (updateDto.getDirectorate() != null) {
	            Directorate directorate = directorateRepository.findByDirectorate(updateDto.getDirectorate())
	                    .orElseGet(() -> {
	                        Directorate newDirectorate = new Directorate();
	                        newDirectorate.setDirectorate(updateDto.getDirectorate());
	                        return directorateRepository.save(newDirectorate);
	                    });
	            requisition.getUser().setDirectorate(directorate);
	        }

	        if (updateDto.getRequestdate() != null) {
	            requisition.setRequestdate(updateDto.getRequestdate());
	        }

	        if (updateDto.getItemtype() != null) {
	        	requisition.getItems().forEach(item -> item.setItemtype(updateDto.getItemtype()));
	        }

	        if (updateDto.getItemspec() != null) {
	            requisition.getItems().forEach(item -> item.setItemspec(updateDto.getItemspec()));
	        }

	        if (updateDto.getReqqty() != 0) {
	            requisition.getItems().forEach(item -> {
	                // Handle updating reqqty for each item
	                item.setReqqty(updateDto.getReqqty());
	            });
	        }

	        if (updateDto.getStatus() != null) {
	            RequisitionStatusHistory statusHistory = new RequisitionStatusHistory();
	            statusHistory.setRequisition(requisition);
	            statusHistory.setStatus(updateDto.getStatus());
//	            statusHistory.setStatusChangeDate(new Date());
	            requisitionStatusHistoryRepository.save(statusHistory);
	        }

	        return requisitionRepository.save(requisition);
//	    @Override
//	    public Optional<RequisitionUser> findUserByDirectorate(String directorate) {
//	        return requisitionUserRepository.findByDirectorate(directorate);
//	    }
	}
	    
	    @Override
	    public List<RequisitionDetailDto> getAllRequisitions() {
	    	List<RequisitionDetailDto> dtoobj = requisitionRepository.findAllRequisitionDetails();
	    	for (RequisitionDetailDto detail : dtoobj) {
	    		System.out.println(detail.getUsername());
	    	}
	    	
	        return requisitionRepository.findAllRequisitionDetails();
	    }

	    @Override
	    public RequisitionDetailDto getRequisitionDetails(Long requestid) {
	        return requisitionRepository.findRequisitionDetailsByRequestid(requestid)
	                .stream()
	                .findFirst()
	                .orElseThrow(() -> new ResourceNotFoundException("Requisition not found for this id :: " + requestid));
	    }

		@Override
		public MainRequisition createMainRequisition(MainRequisition  mainRequisition ) {
			// TODO Auto-generated method stub
			// Save the requisition
	        MainRequisition savedRequisition = mainRequisitionRepo.save(mainRequisition);
	        
	        // Create status history entry
	        MainRequisitionStatusHistory statusHistory = new MainRequisitionStatusHistory();
	        statusHistory.setRequisition(savedRequisition);
	        statusHistory.setStatus("PENDING");
	        statusHistory.setStatusChangeDate(new Date());
	        
	        // Save the status history
	        statusHistoryRepo.save(statusHistory);

	        return savedRequisition;
		}

		@Override
		public List<MainRequisition> getAllRequests() {
			// TODO Auto-generated method stub
	        return mainRequisitionRepo.findAll();
		}

		@Override
		public List<MainRequisitionStatusHistory> getStatusHistoriesByRequisitionId(Long requisitionId) {
			// TODO Auto-generated method stub
	        return statusHistoryRepo.findByRequisition_Requestid(requisitionId);
		}

		@Override
		public List<MainRequisitionStatusHistory> getLatestStatusByRequisitionId(Long requisitionId) {
			// TODO Auto-generated method stub
	        return statusHistoryRepo.findLatestStatusByRequisitionId(requisitionId);
		}

		@Override
		public List<MainRequisition> getAllRequestsByLatestStatus() {
		    List<MainRequisition> requisitions = mainRequisitionRepo.findAll();

		    // Populate each requisition with only its latest status history
		    requisitions.forEach(requisition -> {
		        List<MainRequisitionStatusHistory> latestStatus = getLatestStatusByRequisitionId(requisition.getRequestid());
		        requisition.setStatusHistories(latestStatus.isEmpty() ? Collections.emptyList() : Collections.singletonList(latestStatus.get(0)));
		    });

		    return requisitions;
		}

		@Override
		public List<MainRequestDto> getAllRequisitionsWithLatestStatus() {
			// TODO Auto-generated method stub
	        List<MainRequisition> requisitions = mainRequisitionRepo.findAll();

			return requisitions.stream().map(requisition -> {
	            MainRequestDto dto = new MainRequestDto();
	            dto.setRequestid(requisition.getRequestid());
	            dto.setRequestdate((Timestamp) requisition.getRequestdate());
	            dto.setDirectorate(requisition.getDirectorate());
	            dto.setReceivedfrom(requisition.getReceivedfrom());
	            dto.setSubject(requisition.getSubject());
	            dto.setRemarks(requisition.getRemarks());
	            // Get the latest status
	            MainRequisitionStatusHistory latestStatus = statusHistoryRepo.findTopByRequisitionOrderByStatusChangeDateDesc(requisition);
	            if (latestStatus != null) {
	                dto.setStatus(latestStatus.getStatus());
	                dto.setStatusChangeDate(latestStatus.getStatusChangeDate());
	            }

	            return dto;
	        }).collect(Collectors.toList());
		}
		
		@Override
		public MainRequisition updateRequisition(Long id, MainRequestDto dto) {
		    MainRequisition requisition = mainRequisitionRepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Requisition not found for this id :: " + id));

		    requisition.setRequestdate(dto.getRequestdate());
		    requisition.setReceivedfrom(dto.getReceivedfrom());
		    requisition.setDirectorate(dto.getDirectorate());
		    requisition.setSubject(dto.getSubject());
		    requisition.setRemarks(dto.getRemarks());

		    // Update status
		    MainRequisitionStatusHistory statusHistory = new MainRequisitionStatusHistory();
		    statusHistory.setRequisition(requisition);
		    statusHistory.setStatus(dto.getStatus());
		    statusHistory.setStatusChangeDate(new Date()); // Automate status change date

		    statusHistoryRepo.save(statusHistory);

		    return mainRequisitionRepo.save(requisition);
		}

		@Override
		public MainRequisition updateMainRequisition(Long id, MainRequestDto requisitionDTO) {
			// TODO Auto-generated method stub
			 MainRequisition requisition = mainRequisitionRepo.findById(id)
				        .orElseThrow(() -> new ResourceNotFoundException("Requisition not found for this id :: " + id));

				    requisition.setRequestdate(requisitionDTO.getRequestdate());
				    requisition.setReceivedfrom(requisitionDTO.getReceivedfrom());
				    requisition.setDirectorate(requisitionDTO.getDirectorate());
				    requisition.setSubject(requisitionDTO.getSubject());
				    requisition.setRemarks(requisitionDTO.getRemarks());

				    // Update status
				    MainRequisitionStatusHistory statusHistory = new MainRequisitionStatusHistory();
				    statusHistory.setRequisition(requisition);
				    statusHistory.setStatus(requisitionDTO.getStatus());
				    statusHistory.setStatusChangeDate(new Date()); // Automate status change date

				    statusHistoryRepo.save(statusHistory);

				    return mainRequisitionRepo.save(requisition);
		}

		@Override
		public void deleteRequisition(Long id) {
			// TODO Auto-generated method stub
			mainRequisitionRepo.deleteById(id);
		}

//		alternate for the above
		
//		@Service
//		public class RequisitionServiceImpl implements IRequisitionService {
//
//		    @Autowired
//		    private MainRequisitionRepo mainRequisitionRepo;
//
//		    @Autowired
//		    private MainRequisitionStatusHistoryRepo statusHistoryRepo;
//
//		    @Override
//		    public MainRequisition updateRequisition(Long id, MainRequestDto dto) {
//		        MainRequisition existingRequisition = mainRequisitionRepo.findById(id)
//		            .orElseThrow(() -> new ResourceNotFoundException("Requisition not found for this id :: " + id));
//
//		        // Compare and update only the changed fields
//		        if (dto.getRequestdate() != null && !dto.getRequestdate().equals(existingRequisition.getRequestdate())) {
//		            existingRequisition.setRequestdate(dto.getRequestdate());
//		        }
//		        if (dto.getReceivedfrom() != null && !dto.getReceivedfrom().equals(existingRequisition.getReceivedfrom())) {
//		            existingRequisition.setReceivedfrom(dto.getReceivedfrom());
//		        }
//		        if (dto.getDirectorate() != null && !dto.getDirectorate().equals(existingRequisition.getDirectorate())) {
//		            existingRequisition.setDirectorate(dto.getDirectorate());
//		        }
//		        if (dto.getSubject() != null && !dto.getSubject().equals(existingRequisition.getSubject())) {
//		            existingRequisition.setSubject(dto.getSubject());
//		        }
//		        if (dto.getRemarks() != null && !dto.getRemarks().equals(existingRequisition.getRemarks())) {
//		            existingRequisition.setRemarks(dto.getRemarks());
//		        }
//		        
//		        // Update status if provided and different from the existing status
//		        if (dto.getStatus() != null && !dto.getStatus().equals(getCurrentStatus(existingRequisition))) {
//		            MainRequisitionStatusHistory statusHistory = new MainRequisitionStatusHistory();
//		            statusHistory.setRequisition(existingRequisition);
//		            statusHistory.setStatus(dto.getStatus());
//		            statusHistory.setStatusChangeDate(new Date()); // Automate status change date
//
//		            statusHistoryRepo.save(statusHistory);
//		        }
//
//		        return mainRequisitionRepo.save(existingRequisition);
//		    }
//
//		    // Helper method to get the current status of the requisition
//		    private String getCurrentStatus(MainRequisition requisition) {
//		        return requisition.getStatusHistories().stream()
//		                .max(Comparator.comparing(MainRequisitionStatusHistory::getStatusChangeDate))
//		                .map(MainRequisitionStatusHistory::getStatus)
//		                .orElse(null);
//		    }
//		}
		
//		@PutMapping("/requisition/{id}")
//		public ResponseEntity<MainRequisition> updateRequisition(@PathVariable Long id, @RequestBody MainRequestDto requisitionDTO) {
//		    MainRequisition updatedRequisition = requisitionService.updateRequisition(id, requisitionDTO);
//		    return ResponseEntity.ok(updatedRequisition);
//		}

//		@Override
//	    @Transactional
//	    public void deleteRequisition(Long id) {
//	        // Check if the requisition exists
//	        if (!mainRequisitionRepo.existsById(id)) {
//	            throw new ResourceNotFoundException("Requisition not found for this id :: " + id);
//	        }
//
//	        // Optionally delete related status history records
//	        statusHistoryRepo.deleteByRequisitionId(id);
//
//	        // Delete the requisition
//	        mainRequisitionRepo.deleteById(id);
//	    }

}