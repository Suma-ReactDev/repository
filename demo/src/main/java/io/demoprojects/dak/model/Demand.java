package io.demoprojects.dak.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;



@Entity
@Table(name = "DEMAND")
public class Demand {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "demand_seq")
//    @SequenceGenerator(name = "demand_seq", sequenceName = "DEMAND_SEQ", allocationSize = 1)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demandno;

    private Date demandDate;
    private double amount;
    
	public Demand() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getDemandno() {
		return demandno;
	}
	public void setDemandno(Long demandno) {
		this.demandno = demandno;
	}
	public Date getDemandDate() {
		return demandDate;
	}
	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

    
}