package com.socialpost.post.domain.service;

import java.io.InputStream;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialpost.post.domain.exception.FotoPostagemNaoEncontradoException;
import com.socialpost.post.domain.model.FotoPostagem;
import com.socialpost.post.domain.repository.PostagemRepository;
import com.socialpost.post.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoPostagemService {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@Transactional
	public FotoPostagem salvar(FotoPostagem foto, InputStream dadosArquivo) {
		String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoPostagem> fotoExistente = postagemRepository.findFotoById(foto.getPostagemCodigo());
		
		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			postagemRepository.delete(fotoExistente.get());
		}
		
		foto.setNomeArquivo(nomeNovoArquivo);
		foto = postagemRepository.save(foto);
		postagemRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(foto.getNomeArquivo())
				.contentType(foto.getContentType())
				.inputStream(dadosArquivo)
				.build();
		
		
		fotoStorageService.substituir(nomeArquivoExistente, novaFoto);
		
		return foto;
	}
	
	public FotoPostagem buscarOuFalhar(String codigoPostagem) {
		return postagemRepository.findFotoById(codigoPostagem)
				.orElseThrow( () -> new FotoPostagemNaoEncontradoException(codigoPostagem) );
	}
	
	public void excluir(String codigoPostagem) {
		FotoPostagem foto = buscarOuFalhar(codigoPostagem);
		
		postagemRepository.delete(foto);
		postagemRepository.flush();
		
		fotoStorageService.remover(foto.getNomeArquivo());
	}
	
}
