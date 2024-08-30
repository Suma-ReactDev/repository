package io.demoprojects.dak.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.demoprojects.dak.model.Requisition;
import io.demoprojects.dak.util.RequisitionDetailDto;

@Repository
public interface RequisitionRepository extends JpaRepository<Requisition, Long> {

	@Query("SELECT new io.demoprojects.dak.util.RequisitionDetailDto(r.requestdate, u.username, d.directorate, ri.itemtype, ri.itemspec, ri.reqqty, ri.issuedqty, ri.raisedqty, rs.status, rs.statusChangeDate, r.demand.demandno) " +
	           "FROM RequisitionItem ri " +
	           "JOIN ri.requisition r " +
	           "JOIN r.user u " +
	           "JOIN u.directorate d " +
	           "JOIN RequisitionStatusHistory rs ON rs.requisition = r " +
	           "WHERE r.requestid = :requestid")
	    List<RequisitionDetailDto> findRequisitionDetailsByRequestid(@Param("requestid") Long requestid);
	
	@Query("SELECT new io.demoprojects.dak.util.RequisitionDetailDto( r.requestdate, u.username, d.directorate, ri.itemtype, ri.itemspec, ri.reqqty, ri.issuedqty, ri.raisedqty, rs.status) " +
	           "FROM Requisition r " +
	           "JOIN r.user u " +
	           "JOIN u.directorate d " +
	           "JOIN r.items ri " +
	           "LEFT JOIN r.statusHistories rs " +
	           "WHERE rs.statusChangeDate = (SELECT MAX(subRs.statusChangeDate) FROM RequisitionStatusHistory subRs WHERE subRs.requisition.requestid = r.requestid) " +
	           "ORDER BY r.requestdate DESC")
	List<RequisitionDetailDto> findAllRequisitionDetails();
	
} 
