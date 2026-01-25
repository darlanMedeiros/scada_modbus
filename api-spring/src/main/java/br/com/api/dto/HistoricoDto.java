package br.com.api.dto;

import java.time.OffsetDateTime;

public record HistoricoDto(
        OffsetDateTime ts,
        Integer valorInt,
        Boolean valorBool) {
}
