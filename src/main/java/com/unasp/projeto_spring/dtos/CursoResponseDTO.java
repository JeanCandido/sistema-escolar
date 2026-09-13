package com.unasp.projeto_spring.dtos;

public record CursoResponseDTO(
        Long idCurso,
        String nome,
        ProfessorSimplesDTO professor
) {}