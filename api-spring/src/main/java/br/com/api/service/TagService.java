package br.com.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.api.dto.*;
import br.com.api.repository.TagRepository;

@Service
public class TagService {

    private final TagRepository repo;

    public TagService(TagRepository repo) {
        this.repo = repo;
    }

    public List<TagDto> listar() {
        return repo.listarTags();
    }

    public UltimoValorDto ultimo(Integer tagId) {
        return repo.ultimoValor(tagId);
    }

    public List<HistoricoDto> historico(Integer tagId) {
        return repo.historico(tagId);
    }

    public UltimoValorDto buscarUltimoValor(String tag) {
        return repo.buscarUltimoValor(tag);
    }

    public List<TagDto> buscarTagsPorNome(String nome) {
        return repo.buscarTagsPorNome(nome);
    }
}
