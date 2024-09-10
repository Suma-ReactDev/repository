package io.demoprojects.dak.dto;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MainRequestDto {
	
	 	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	 	private Long requestid;
	    private Date  requestdate;
	    private String receivedfrom;
	    private String directorate;
	    private String subject;
	    private String remarks;
	    private String status;
	    private Date statusChangeDate;
	    private String fileName;
	    private String fileUrl;
	    
		public Long getRequestid() {
			return requestid;
		}
		public void setRequestid(Long requestid) {
			this.requestid = requestid;
		}
		public Date getRequestdate() {
			return requestdate;
		}
		public String getReceivedfrom() {
			return receivedfrom;
		}
		public void setReceivedfrom(String receivedfrom) {
			this.receivedfrom = receivedfrom;
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
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
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
		public void setRequestdate(Date requestdate) {
			this.requestdate = requestdate;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFileUrl() {
			return fileUrl;
		}
		public void setFileUrl(String fileUrl) {
			this.fileUrl = fileUrl;
		}
		
		
}
