package io.demoprojects.dak.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.demoprojects.dak.config.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="MAINREQUISITION")
@EntityListeners(AuditingEntityListener.class)
public class MainRequisition extends Auditable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestid;
	
	@OneToMany(mappedBy = "requisition", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MainRequisitionStatusHistory> statusHistories = new ArrayList<>();;
	
	private Date requestdate;
	
	private String receivedfrom;
	
	private String directorate;
	
	private String subject;
	
	private String remarks;

	@OneToOne
    @JoinColumn(name = "file_id")
    private Upload file;
	
	public Long getRequestid() {
		return requestid;
	}

	public void setRequestid(Long requestid) {
		this.requestid = requestid;
	}

	public List<MainRequisitionStatusHistory> getStatusHistories() {
		return statusHistories;
	}

	public void setStatusHistories(List<MainRequisitionStatusHistory> statusHistories) {
		this.statusHistories = statusHistories;
	}

	public Date getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(Date requestdate) {
		this.requestdate = requestdate;
	}

	public String getReceivedfrom() {
		return receivedfrom;
	}

	public void setReceivedfrom(String receivedfrom) {
		this.receivedfrom = receivedfrom;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDirectorate() {
		return directorate;
	}

	public void setDirectorate(String directorate) {
		this.directorate = directorate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Upload getFile() {
		return file;
	}

	public void setFile(Upload file) {
		this.file = file;
	}
	
	
	
	
}
