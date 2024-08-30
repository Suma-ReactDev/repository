package io.demoprojects.dak.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "REQUISITIONITEMS")
public class RequisitionItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requisition_item_seq")
//    @SequenceGenerator(name = "requisition_item_seq", sequenceName = "REQUISITION_ITEM_SEQ", allocationSize = 1)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemid;

    @ManyToOne
    @JoinColumn(name = "requestid")
    private Requisition requisition;

    private String itemtype;
    private String itemspec;
    private Integer reqqty;
    private Integer issuedqty;
    private Integer raisedqty;
	public Long getItemid() {
		return itemid;
	}
	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}
	public Requisition getRequisition() {
		return requisition;
	}
	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
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
	public Integer getReqqty() {
		return reqqty;
	}
	public void setReqqty(Integer reqqty) {
		this.reqqty = reqqty;
	}
	public Integer getIssuedqty() {
		return issuedqty;
	}
	public void setIssuedqty(Integer issuedqty) {
		this.issuedqty = issuedqty;
	}
	public Integer getRaisedqty() {
		return raisedqty;
	}
	public void setRaisedqty(Integer raisedqty) {
		this.raisedqty = raisedqty;
	}
    
	
}