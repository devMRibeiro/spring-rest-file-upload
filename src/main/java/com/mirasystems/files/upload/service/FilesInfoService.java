package com.mirasystems.files.upload.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mirasystems.files.upload.model.FilesInfo;
import com.mirasystems.files.upload.repository.FilesInfoRepository;

@Service
public class FilesInfoService {

	@Autowired
	private FilesInfoRepository repository;

	public FilesInfo store(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FilesInfo fileInfo = new FilesInfo(fileName, file.getContentType(), file.getBytes());

		return repository.save(fileInfo);
	}

	public FilesInfo getFile(String id) {
		return repository.findById(id).get();
	}

	public Stream<FilesInfo> getAllFiles() {
		return repository.findAll().stream();
	}
}