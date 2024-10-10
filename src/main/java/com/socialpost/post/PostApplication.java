package com.socialpost.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.socialpost.post.core.io.Base64ProtocolResolver;

@SpringBootApplication
public class PostApplication {

	public static void main(String[] args) {
		var app = new SpringApplication(PostApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
	}

}
