package io.demoprojects.dak.model;


import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
  
	@Entity
	@Table(name = "REQUISITIONS")
	public class Requisition {
//	    @Id
//	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requisitions_seq")
//	    @SequenceGenerator(name = "requisitions_seq", sequenceName = "REQUISITIONS_SEQ", allocationSize = 1)
//	    private Long requestid;
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long requestid;

	    @ManyToOne
	    @JoinColumn(name = "userid")
	    private RequisitionUser user;

	    @ManyToOne
	    @JoinColumn(name = "demandno")
	    private Demand demand;

	    @OneToMany(mappedBy = "requisition", fetch = FetchType.LAZY)
	    private List<RequisitionItem> items;

	    @OneToMany(mappedBy = "requisition", fetch = FetchType.LAZY)
	    private List<RequisitionStatusHistory> statusHistories;
	    
	    private Date requestdate;

		public RequisitionUser getUser() {
			return user;
		}

		public void setUser(RequisitionUser user) {
			this.user = user;
		}

		public Demand getDemand() {
			return demand;
		}

		public void setDemand(Demand demand) {
			this.demand = demand;
		}

		public Date getRequestdate() {
			return requestdate;
		}

		public void setRequestdate(Date requestDate) {
			this.requestdate = requestDate;
		}

		public Long getRequestid() {
			return requestid;
		}

		public void setRequestid(Long requestid) {
			this.requestid = requestid;
		}

		public List<RequisitionItem> getItems() {
			return items;
		}

		public void setItems(List<RequisitionItem> items) {
			this.items = items;
		}

		public List<RequisitionStatusHistory> getStatusHistories() {
			return statusHistories;
		}

		public void setStatusHistories(List<RequisitionStatusHistory> statusHistories) {
			this.statusHistories = statusHistories;
		}

	    
	}
