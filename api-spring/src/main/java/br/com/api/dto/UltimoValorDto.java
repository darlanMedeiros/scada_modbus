package br.com.api.dto;

import java.time.OffsetDateTime;

public record UltimoValorDto(
        Integer tagId,
        String nome,
        Integer valorInt,
        Boolean valorBool,
        OffsetDateTime ts) {
}
