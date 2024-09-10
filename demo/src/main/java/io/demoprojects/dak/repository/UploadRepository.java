package io.demoprojects.dak.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.demoprojects.dak.model.Upload;

public interface UploadRepository extends JpaRepository<Upload, Long> {
}
