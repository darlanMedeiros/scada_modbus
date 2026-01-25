package br.com.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import br.com.api.dto.*;
import br.com.api.service.TagService;

@RestController
@RequestMapping("/api/tags")
@CrossOrigin
public class TagController {

    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @GetMapping
    public List<TagDto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}/ultimo")
    public UltimoValorDto ultimo(@PathVariable Integer id) {
        return service.ultimo(id);
    }

    @GetMapping("/{id}/historico")
    public List<HistoricoDto> historico(@PathVariable Integer id) {
        return service.historico(id);
    }

    // ENDPOINT GENÉRICO
    @GetMapping("/{nome}")
    public UltimoValorDto buscar(@PathVariable String nome) {
        return service.buscarUltimoValor(nome.toUpperCase());
    }
}
