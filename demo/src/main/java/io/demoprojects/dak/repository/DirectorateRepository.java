package io.demoprojects.dak.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.demoprojects.dak.model.Directorate;
import io.demoprojects.dak.model.RequisitionUser;

@Repository
public interface DirectorateRepository extends JpaRepository<Directorate, Long> {
	
	Optional<Directorate> findByDirectorate(String directorate);

}
