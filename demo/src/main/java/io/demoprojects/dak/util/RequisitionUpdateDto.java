package io.demoprojects.dak.util;

import java.util.Date;

public class RequisitionUpdateDto {
	
	private Date requestdate;
    private String username;
    private String directorate;
    private String itemtype;
    private String itemspec;
    private Integer reqqty;
    private Integer issuedqty;
    private Integer raisedqty;
    private String status;
    private Date statusChangeDate;
    private Long demandno;
    
    
//	public RequisitionUpdateDto() {
//		super();
//		this.reqqty=0;
//		this.issuedqty=0;
//		this.raisedqty=0;
//	}
	public Date getRequestdate() {
		return requestdate;
	}
	public void setRequestdate(Date requestdate) {
		this.requestdate = requestdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDirectorate() {
		return directorate;
	}
	public void setDirectorate(String directorate) {
		this.directorate = directorate;
	}
	public String getItemtype() {
		return itemtype;
	}
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
	public String getItemspec() {
		return itemspec;
	}
	public void setItemspec(String itemspec) {
		this.itemspec = itemspec;
	}
	public int getReqqty() {
		return reqqty;
	}
	public void setReqqty(int reqqty) {
		this.reqqty = reqqty;
	}
	public int getIssuedqty() {
		return issuedqty;
	}
	public void setIssuedqty(int issuedqty) {
		this.issuedqty = issuedqty;
	}
	public int getRaisedqty() {
		return raisedqty;
	}
	public void setRaisedqty(int raisedqty) {
		this.raisedqty = raisedqty;
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
	public Long getDemandno() {
		return demandno;
	}
	public void setDemandno(Long demandno) {
		this.demandno = demandno;
	}

}
