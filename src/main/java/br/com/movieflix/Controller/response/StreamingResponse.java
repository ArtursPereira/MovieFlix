package br.com.movieflix.Controller.response;

import lombok.Builder;

@Builder
public record StreamingResponse(String name, Long id) {
}
