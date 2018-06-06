package com.app.file.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.file.model.FileMetadata;
import com.app.file.service.FileService;

@RestController
@RequestMapping("files")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			return new ResponseEntity<String>("Supplied file is empty", HttpStatus.OK);
		}
		FileMetadata metadata = fileService.save(file);
		return new ResponseEntity<FileMetadata>(metadata, HttpStatus.OK);
	}
	
	@GetMapping
	public List<FileMetadata>getUploadedFilesMetaData(){
		return fileService.getFilesMetaData();
	}

}
