package com.test.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileHandleController {

	@Value("${file.upload-dir}")
	private String folderPath;

	@PostMapping("/uploadFile")
	public String getFile(@RequestParam("file") MultipartFile file) throws Exception {
		Path fileStorageLocation = Paths.get(folderPath).toAbsolutePath().normalize();

		Files.createDirectories(fileStorageLocation);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		Path targetLocation = fileStorageLocation.resolve(System.currentTimeMillis()+"_"+fileName);
		Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

		return fileName + " :: File Received";

	}
}
