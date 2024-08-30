package io.demoprojects.dak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.demoprojects.dak.model.Demand;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Long> {
}
