package com.unasp.projeto_spring.dtos;

import java.util.List;

public record ProfessorResponseDTO(
        Long idProfessor,
        String nome,
        String email,
        String departamento,
        List<CursoSimplesDTO> cursos
) {}