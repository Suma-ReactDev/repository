package io.demoprojects.dak.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "DIRECTORATE")
public class Directorate {
	
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "directorate_seq")
//    @SequenceGenerator(name = "directorate_seq", sequenceName = "DIRECTORATE_SEQ", allocationSize = 1)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slno;

    private String directorate;
    private String division;
	public String getDirectorate() {
//		System.out.println("DIRECTORATE : "+directorate);
		return directorate;
	}
	public void setDirectorate(String directorate) {
		this.directorate = directorate;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
}
