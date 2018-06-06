package com.app.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.file.model.FileMetadata;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long>{

}
