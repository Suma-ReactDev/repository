package io.demoprojects.dak.service;

import java.util.List;
import java.util.Optional;

import io.demoprojects.dak.dto.MainRequestDto;
import io.demoprojects.dak.model.MainRequisition;
import io.demoprojects.dak.model.MainRequisitionStatusHistory;
import io.demoprojects.dak.model.Requisition;
import io.demoprojects.dak.util.RequisitionDetailDto;
import io.demoprojects.dak.util.RequisitionUpdateDto;

public interface IRequisitionService {

    public void createRequisition(RequisitionDetailDto createDto);
	Requisition saveRequisition(Requisition requisition);
    Optional<Requisition> getRequisition(Long requestId);
//    void deleteRequisition(Long requestId);
//    Requisition updateRequisition(Long requestId, RequisitionDetailDto requisitionDetailDto);
    
    public Requisition updateRequisition(Long id, RequisitionUpdateDto updateDto);

//    Optional<RequisitionUser> findUserByDirectorate(String directorate);
    
    public List<RequisitionDetailDto> getAllRequisitions();
    
    public RequisitionDetailDto getRequisitionDetails(Long requestid);
    
    public MainRequisition createMainRequisition(MainRequisition mainRequisition);
    
    public List<MainRequisition> getAllRequests();
    
	List<MainRequisitionStatusHistory> getStatusHistoriesByRequisitionId(Long requisitionId);
	
    List<MainRequisitionStatusHistory> getLatestStatusByRequisitionId(Long requisitionId);
	
    List<MainRequisition> getAllRequestsByLatestStatus();
    
    public List<MainRequestDto> getAllRequisitionsWithLatestStatus();
    
    public MainRequisition updateRequisition(Long id, MainRequestDto requisitionDTO);

    public void deleteRequisition(Long id);

    
}
