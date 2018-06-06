package com.app.file.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FileMetadata {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	private String fileName;
	private LocalDateTime lastAccessTime;
	private LocalDateTime creationTime;
	private LocalDateTime lastModifiedTime;
	private LocalDateTime uploadedDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LocalDateTime getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(LocalDateTime lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalDateTime getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public LocalDateTime getUploadedDateTime() {
		return uploadedDateTime;
	}

	public void setUploadedDateTime(LocalDateTime uploadedDateTime) {
		this.uploadedDateTime = uploadedDateTime;
	}

}
