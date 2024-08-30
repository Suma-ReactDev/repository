package io.demoprojects.dak.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.demoprojects.dak.model.Requisition;
import io.demoprojects.dak.model.RequisitionUser;
import io.demoprojects.dak.repository.RequisitionRepository;
import io.demoprojects.dak.repository.RequisitionUserRepository;
import io.demoprojects.dak.util.RequisitionDetailDto;

@Service
public class RequisitionService {

    @Autowired
    private RequisitionRepository requisitionRepository;

    @Autowired
    private RequisitionUserRepository requisitionUserRepository;

    public Requisition saveRequisition(Requisition requisition) {
        return requisitionRepository.save(requisition);
    }

    public Optional<Requisition> getRequisition(Long requestId) {
        return requisitionRepository.findById(requestId);
    }

    public void deleteRequisition(Long requestId) {
        requisitionRepository.deleteById(requestId);
    }

    public Requisition updateRequisition(Long requestId, RequisitionDetailDto requisitionDetailDto) {
        Optional<Requisition> requisitionOpt = requisitionRepository.findById(requestId);
        if (requisitionOpt.isPresent()) {
            Requisition requisition = requisitionOpt.get();
            requisition.setRequestdate(requisitionDetailDto.getRequestdate());
            // Update other fields
            return requisitionRepository.save(requisition);
        } else {
            throw new RuntimeException("Requisition not found");
        }
    }
    
    

//    public Optional<RequisitionUser> findUserByDirectorate(String directorate) {
//        return requisitionUserRepository.findByDirectorate(directorate);
//    }
}
