package com.app.file.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.file.model.FileMetadata;
import com.app.file.repository.FileMetadataRepository;

@Service
public class FileService {

	private static String UPLOADED_FOLDER = System.getProperty("java.io.tmpdir");

	@Autowired
	private FileMetadataRepository fileMetadataRepository;

	
	public FileMetadata save(MultipartFile multiPartFile) throws Exception {
		byte[] bytes = multiPartFile.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + multiPartFile.getOriginalFilename());
		Files.write(path, bytes);
		FileMetadata metadata = new FileMetadata();
		BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
		metadata.setFileName(multiPartFile.getOriginalFilename());
		metadata.setCreationTime(LocalDateTime.ofInstant(attributes.creationTime().toInstant(), ZoneOffset.UTC));
		metadata.setLastAccessTime(LocalDateTime.ofInstant(attributes.lastAccessTime().toInstant(), ZoneOffset.UTC));
		metadata.setLastModifiedTime(
				LocalDateTime.ofInstant(attributes.lastModifiedTime().toInstant(), ZoneOffset.UTC));
		metadata.setUploadedDateTime(LocalDateTime.now());
		fileMetadataRepository.save(metadata);
		return metadata;
	}


	public List<FileMetadata> getFilesMetaData() {
		return fileMetadataRepository.findAll();
	}
	
	

}
