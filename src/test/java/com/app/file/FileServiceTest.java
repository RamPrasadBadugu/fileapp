package com.app.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.file.model.FileMetadata;
import com.app.file.repository.FileMetadataRepository;
import com.app.file.service.FileService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {
	
	private static String UPLOADED_FOLDER = System.getProperty("java.io.tmpdir");

	@Autowired
	@InjectMocks
	private FileService fileSerivce;
	
	@Mock
	private FileMetadataRepository fileMetadataRepository;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetFilesMetaData() {
		List<FileMetadata>fileMetadatas = new ArrayList<>();
		
		FileMetadata metadata1 = new FileMetadata();
		metadata1.setFileName("test.txt");
		FileMetadata metadata2 = new FileMetadata();
		metadata2.setFileName("test1.txt");
		fileMetadatas.add(metadata1);
		fileMetadatas.add(metadata2);
		Mockito.when(fileMetadataRepository.findAll()).thenReturn(fileMetadatas);
		List<FileMetadata>files = fileSerivce.getFilesMetaData();
		assertEquals(2,files.size());
	}
	
	@Test
	public void testSaveFile() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "test.txt","application/txt","some content".getBytes());
		FileMetadata metadata = new FileMetadata();
		metadata.setFileName("test.txt");
		Mockito.when(fileMetadataRepository.save(Mockito.any(FileMetadata.class))).thenReturn(metadata);
		fileSerivce.save(file);
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		assertTrue(Files.exists(path));
	}
	
}
