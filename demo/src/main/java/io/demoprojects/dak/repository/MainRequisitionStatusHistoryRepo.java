package io.demoprojects.dak.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.demoprojects.dak.model.MainRequisition;
import io.demoprojects.dak.model.MainRequisitionStatusHistory;

public interface MainRequisitionStatusHistoryRepo extends JpaRepository<MainRequisitionStatusHistory, Long> {

    List<MainRequisitionStatusHistory> findByRequisition_Requestid(Long requisitionId);

    @Query("SELECT h FROM MainRequisitionStatusHistory h WHERE h.requisition.requestid = :requisitionId ORDER BY h.statusChangeDate DESC")
    List<MainRequisitionStatusHistory> findLatestStatusByRequisitionId(@Param("requisitionId") Long requisitionId);

    MainRequisitionStatusHistory findTopByRequisitionOrderByStatusChangeDateDesc(MainRequisition requisition);

//    void deleteByRequisitionId(Long requisitionId);

}
