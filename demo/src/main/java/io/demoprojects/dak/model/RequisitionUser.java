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
@Table(name = "REQUISITIONUSERS")
public class RequisitionUser {
	
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requisition_user_seq")
//    @SequenceGenerator(name = "requisition_user_seq", sequenceName = "REQUISITION_USER_SEQ", allocationSize = 1)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;

    private String username;
    private String designation;
    private String sfid;

    @ManyToOne
    @JoinColumn(name = "slno")
    private Directorate directorate;

    private Long phone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public Directorate getDirectorate() {
		return directorate;
	}

	public void setDirectorate(Directorate directorate) {
		this.directorate = directorate;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

    
}

