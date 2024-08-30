package io.demoprojects.dak.model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;



@Entity
@Table(name = "REQUISITIONSTATUSHISTORY")
public class RequisitionStatusHistory {
    
//	    @Id
//	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requisition_status_history_seq")
//	    @SequenceGenerator(name = "requisition_status_history_seq", sequenceName = "REQUISITION_STATUS_HISTORY_SEQ", allocationSize = 1)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
	private Long slno;

	    @ManyToOne
	    @JoinColumn(name = "requestid")
	    private Requisition requisition;

	    private String status;
	    private Date statusChangeDate;
	    
		public Requisition getRequisition() {
			return requisition;
		}
		public void setRequisition(Requisition requisition) {
			this.requisition = requisition;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Date getStatusChangeDate() {
			return statusChangeDate;
		}
		public void setStatusChangeDate(Date statusChangeDate) {
			this.statusChangeDate = statusChangeDate;
		}

	    
	}