package io.demoprojects.dak.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.demoprojects.dak.model.RequisitionUser;

@Repository
public interface RequisitionUserRepository extends JpaRepository<RequisitionUser, Long> {
    
//	@Query("SELECT ru FROM RequisitionUser ru JOIN ru.directorate d WHERE d.directorate = :directorate")
//	Optional<RequisitionUser> findByDirectorate(String directorate);
    Optional<RequisitionUser> findByUsername(String username);

}
