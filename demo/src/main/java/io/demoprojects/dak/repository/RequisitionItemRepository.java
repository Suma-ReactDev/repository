package io.demoprojects.dak.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.demoprojects.dak.model.RequisitionItem;
import io.demoprojects.dak.util.RequisitionDetailDto;

@Repository
public interface RequisitionItemRepository extends JpaRepository<RequisitionItem, Long> {
	
//	@Query("SELECT new io.demoprojects.dak.util.RequisitionDetailDto(ru.username, ru.directorate, ri.itemType, ri.itemSpec, ri.reqQty, ri.issuedQty, ri.raisedQty, rsh.status, rsh.statusChangeDate, r.demandNo) " +
//	           "FROM RequisitionItem ri " +
//	           "JOIN Requisition r ON r.requestId = ri.requestId " +
//	           "JOIN RequisitionStatusHistory rsh ON rsh.requestId = r.requestId " +
//	           "JOIN RequisitionUser ru ON ru.userId = r.userId " +
//	           "WHERE r.demandNo = :demandNo")
//	    List<RequisitionDetailDto> findRequisitionItemDetailsByDemandNo(@Param("demandNo") Long demandNo);\
	
//	@Query("SELECT new io.demoprojects.dak.util.RequisitionDetailDto(r.requestdate, u.username, d.directorate, ri.itemtype, ri.itemspec, ri.reqqty, ri.issuedqty, ri.raisedqty, rs.status, rs.statusChangeDate, r.demand.demandno) " +
//		       "FROM RequisitionItem ri " +
//		       "JOIN ri.requisition r " +
//		       "JOIN r.user u " +
//		       "JOIN u.directorate d " +
//		       "JOIN RequisitionStatusHistory rs ON rs.requisition = r " +
//		       "WHERE r.requestid = :requestid")
//		List<RequisitionDetailDto> findRequisitionDetailsByRequestid(@Param("requestid") Long requestid);
}
