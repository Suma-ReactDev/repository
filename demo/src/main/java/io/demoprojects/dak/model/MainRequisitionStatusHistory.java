package io.demoprojects.dak.model;

import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.demoprojects.dak.config.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="MAINREQUISITIONSTATUSHISTORY")
@EntityListeners(AuditingEntityListener.class)
public class MainRequisitionStatusHistory extends Auditable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
	private Long slno;

	    @ManyToOne
	    @JoinColumn(name = "requestid", nullable = false)
	    @JsonIgnore
	    private MainRequisition requisition;

	    private String status;
	    private Date statusChangeDate;
	    
		public Long getSlno() {
			return slno;
		}
		public void setSlno(Long slno) {
			this.slno = slno;
		}
		public MainRequisition getRequisition() {
			return requisition;
		}
		public void setRequisition(MainRequisition requisition) {
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
