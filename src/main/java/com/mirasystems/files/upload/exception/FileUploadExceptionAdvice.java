package com.mirasystems.files.upload.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mirasystems.files.upload.message.ResponseMessage;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMaxUploadSizeExceededException(
			MaxUploadSizeExceededException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request)
	{
		
		return new ResponseEntity<Object>(new ResponseMessage("File too long. Maximum size: 2MB", HttpStatus.PAYLOAD_TOO_LARGE.value()), HttpStatus.EXPECTATION_FAILED);
	}
}