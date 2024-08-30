package io.demoprojects.dak.util;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RequisitionDetailDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Timestamp  requestdate;
    private String username;
    private String directorate;
    private String itemtype;
    private String itemspec;
    private int reqqty;
    private int issuedqty;
    private int raisedqty;
    private String status;
    private Timestamp  statusChangeDate;
//    private Long demandno;

    public RequisitionDetailDto() {
	}



	public RequisitionDetailDto(Timestamp requestdate, String username, String directorate, String itemtype,
			String itemspec, int reqqty, int issuedqty, int raisedqty, String status) {
		super();
		this.requestdate = requestdate;
		this.username = username;
		this.directorate = directorate;
		this.itemtype = itemtype;
		this.itemspec = itemspec;
		this.reqqty = reqqty;
		this.issuedqty = issuedqty;
		this.raisedqty = raisedqty;
		this.status = status;
	}



	public RequisitionDetailDto( String username, String directorate, String itemtype, String itemspec,
			int reqqty, int issuedqty, int raisedqty, String status) {
		super();

		this.username = username;
		this.directorate = directorate;
		this.itemtype = itemtype;
		this.itemspec = itemspec;
		this.reqqty = reqqty;
		this.issuedqty = issuedqty;
		this.raisedqty = raisedqty;
		this.status = status;

//		this.demandno = demandno;
	}



	public Timestamp  getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(Timestamp  requestdate) {
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

	public Timestamp  getStatusChangeDate() {
		return statusChangeDate;
	}

	public void setStatusChangeDate(Timestamp  statusChangeDate) {
		this.statusChangeDate = statusChangeDate;
	}
//
//	public Long getDemandno() {
//		return demandno;
//	}
//
//	public void setDemandno(Long demandno) {
//		this.demandno = demandno;
//	}

}
