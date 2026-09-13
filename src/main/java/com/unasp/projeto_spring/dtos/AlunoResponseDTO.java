package com.unasp.projeto_spring.dtos;

public record AlunoResponseDTO(
        Long idAluno,
        String nome,
        String email,
        CursoSimplesDTO curso
) {}