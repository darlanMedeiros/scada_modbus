package br.com.api.dto;

public record TagDto(
        Integer tagId,
        String nome,
        String tipo,
        Integer endereco) {
}
