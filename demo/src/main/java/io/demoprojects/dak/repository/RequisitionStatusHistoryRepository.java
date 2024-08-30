package io.demoprojects.dak.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.demoprojects.dak.model.Requisition;
import io.demoprojects.dak.model.RequisitionStatusHistory;

@Repository
public interface RequisitionStatusHistoryRepository extends JpaRepository<RequisitionStatusHistory, Long> {
	List<RequisitionStatusHistory> findByRequisition(Requisition requisition);
}
