package com.unasp.projeto_spring.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoRequestDTO(

        @NotBlank(message = "O nome do curso é obrigatório")
        String nome,

        @NotNull(message = "O ID do professor é obrigatório")
        Long idProfessor

) {}