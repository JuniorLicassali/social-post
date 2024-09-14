package com.socialpost.post.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialpost.post.api.PostLinks;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {
	
	@Autowired
	private PostLinks postLinks;
	
	@GetMapping
	public RootEntryPointDTO root() {
		var rootEntryPointDTO = new RootEntryPointDTO();
		
		rootEntryPointDTO.add(postLinks.linkToPostagens());
		rootEntryPointDTO.add(postLinks.linkToUsuarios("usuarios"));
		rootEntryPointDTO.add(postLinks.linkToGrupos("grupos"));
		
		return rootEntryPointDTO;
	}

	private static class RootEntryPointDTO extends RepresentationModel<RootEntryPointDTO> {
	}
	
}
