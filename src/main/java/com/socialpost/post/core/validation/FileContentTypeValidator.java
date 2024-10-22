package com.socialpost.post.core.validation;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {
	
	private List<String> allowedContentTypes;
	
	@Override
	public void initialize(FileContentType constraint) {
		this.allowedContentTypes = Arrays.asList(constraint.allowed());
	}

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		return value == null || this.allowedContentTypes.contains(value.getContentType());
	}

}
