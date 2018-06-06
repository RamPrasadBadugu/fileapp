package com.app.file;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.app.file.controller.FileController;
import com.app.file.model.FileMetadata;
import com.app.file.service.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileappApplicationTests {

	private MockMvc mockMVC;

	@InjectMocks
	private FileController fileController;

	@Mock
	private FileService fileService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMVC = MockMvcBuilders.standaloneSetup(fileController).build();
	}

	@Test
	public void testFileUpload() throws Exception {
		MockMultipartFile file = new MockMultipartFile("file", "some content".getBytes());
		FileMetadata metadata = new FileMetadata();
		metadata.setFileName("test.txt");
		Mockito.when(fileService.save(file)).thenReturn(metadata);
		this.mockMVC.perform(MockMvcRequestBuilders.multipart("/files").file(file)).andExpect(status().isOk());

	}
	
	@Test
	public void testGetFilesMetadata() throws Exception {
		List<FileMetadata>fileMetadatas = new ArrayList<>();
		
		FileMetadata metadata1 = new FileMetadata();
		metadata1.setFileName("test.txt");
		FileMetadata metadata2 = new FileMetadata();
		metadata2.setFileName("test1.txt");
		fileMetadatas.add(metadata1);
		fileMetadatas.add(metadata2);
		
		ObjectMapper mapper = new ObjectMapper();
		String expectedResponse = mapper.writeValueAsString(fileMetadatas);
		Mockito.when(fileService.getFilesMetaData()).thenReturn(fileMetadatas);
		this.mockMVC.perform(get("/files")).andExpect(status().isOk()).andExpect(content().string(expectedResponse));

	}

}
