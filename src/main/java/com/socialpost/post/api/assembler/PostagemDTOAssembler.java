package com.socialpost.post.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.controller.ComentarioPostController;
import com.socialpost.post.api.controller.PostagemController;
import com.socialpost.post.api.dto.PostagemDTO;
import com.socialpost.post.domain.model.Postagem;

@Component
public class PostagemDTOAssembler extends RepresentationModelAssemblerSupport<Postagem, PostagemDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	public PostagemDTOAssembler() {
		super(PostagemController.class, PostagemDTO.class);
	}
	
	@Override
	public PostagemDTO toModel(Postagem postagem) {
		PostagemDTO postagemDTO = createModelWithId(postagem.getCodigo(), postagem);
		
		modelMapper.map(postagem, postagemDTO);
		
//		postagemDTO.add(postLinks.linkToPostagens());
		
		postagemDTO.add(WebMvcLinkBuilder.linkTo(PostagemController.class)
				.withRel("postagens"));		
		
		postagemDTO.getComentarios().forEach(comentarioDTO -> {
			comentarioDTO.add(WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(ComentarioPostController.class).listar(postagemDTO.getCodigo(), Pageable.unpaged()))
					.withRel("comentarios"));
		});
		
		return postagemDTO;
	}
//	
//	public List<PostagemDTO> toColletionDTO(List<Postagem> postagens) {
//		return postagens.stream()
//							.map(postagem -> toModel(postagem))
//							.collect(Collectors.toList());
//	}


}
