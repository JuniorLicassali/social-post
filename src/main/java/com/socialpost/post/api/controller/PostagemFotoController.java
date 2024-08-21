package com.socialpost.post.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.socialpost.post.api.assembler.FotoPostagemDTOAssembler;
import com.socialpost.post.api.dto.FotoPostagemDTO;
import com.socialpost.post.api.dto.input.FotoPostagemInput;
import com.socialpost.post.domain.exception.EntidadeNaoEncontradaException;
import com.socialpost.post.domain.model.FotoPostagem;
import com.socialpost.post.domain.model.Postagem;
import com.socialpost.post.domain.service.CatalogoFotoPostagemService;
import com.socialpost.post.domain.service.FotoStorageService;
import com.socialpost.post.domain.service.FotoStorageService.FotoRecuperada;
import com.socialpost.post.domain.service.PostagemService;

@RestController
@RequestMapping(path = "/postagem/{codigoPostagem}/foto")
public class PostagemFotoController {
	
	@Autowired
	private PostagemService postagemService;
	
	@Autowired
	private CatalogoFotoPostagemService catalogoFotoPostagem;
	
	@Autowired
	private FotoPostagemDTOAssembler fotoPostagemDTOAssembler;
	
	@Autowired
	private FotoStorageService fotoStorage;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoPostagemDTO atualizarFoto(@PathVariable String codigoPostagem, 
			@Valid FotoPostagemInput fotoPostagemInput) throws IOException {
		
		Postagem postagem = postagemService.buscarOuFalhar(codigoPostagem);
		
		MultipartFile arquivo = fotoPostagemInput.getArquivo();
		
		FotoPostagem foto = new FotoPostagem();
		foto.setPostagem(postagem);
//		foto.setDescricao(fotoPostagemInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		FotoPostagem fotoSalva = catalogoFotoPostagem.salvar(foto, arquivo.getInputStream());
		
		return fotoPostagemDTOAssembler.toDTO(fotoSalva);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoPostagemDTO buscar(@PathVariable String codigoPostagem) {
		FotoPostagem fotoPostagem = catalogoFotoPostagem.buscarOuFalhar(codigoPostagem);

		return fotoPostagemDTOAssembler.toDTO(fotoPostagem);
	}
	
	@GetMapping
	public ResponseEntity<?> servirFoto(@PathVariable String codigoPostagem,
			@RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		try {
			FotoPostagem fotoPostagem = catalogoFotoPostagem.buscarOuFalhar(codigoPostagem);
			
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoPostagem.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
			
			FotoRecuperada fotoRecuperada = fotoStorage.recuperar(fotoPostagem.getNomeArquivo());
			
			if (fotoRecuperada.temUrl()) {
				return ResponseEntity
						.status(HttpStatus.FOUND)
						.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
						.build();
			} else {
				return ResponseEntity.ok()
						.contentType(mediaTypeFoto)
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));
			}
			
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {
		
		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypesAceita -> mediaTypesAceita.isCompatibleWith(mediaTypeFoto));
		
		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
		
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable String codigoPostagem) {
		catalogoFotoPostagem.excluir(codigoPostagem);
	}
	
}
