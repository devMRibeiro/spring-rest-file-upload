package com.mirasystems.files.upload.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mirasystems.files.upload.message.ResponseFile;
import com.mirasystems.files.upload.message.ResponseMessage;
import com.mirasystems.files.upload.model.FilesInfo;
import com.mirasystems.files.upload.service.FilesInfoService;

@Controller
@CrossOrigin("http://localhost:4200")
public class FilesInfoController {

	@Autowired
	private FilesInfoService service;

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		try {

			service.store(file);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseMessage("Uploaded the file successfully: " + fileName, HttpStatus.OK.value()));

		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseMessage("Could not upload the file: " + fileName + " !", HttpStatus.EXPECTATION_FAILED.value()));
		}
	}

	@GetMapping("/files")
	public ResponseEntity<List<ResponseFile>> getListFiles() {

		List<ResponseFile> files = service.getAllFiles().map(filesInfo -> {
			String fileDownloadUri = ServletUriComponentsBuilder
					.fromCurrentContextPath()
					.path("/file/")
					.path(filesInfo.getId())
					.toUriString();

			return new ResponseFile(
					filesInfo.getName(),
					fileDownloadUri,
					filesInfo.getType(),
					Long.valueOf(filesInfo.getData().length));
		 }).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/file/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable String id) {

		FilesInfo filesInfo = service.getFile(id);
		return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filesInfo.getName() + "\"")
        .body(filesInfo.getData());
	}
}