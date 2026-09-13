package com.unasp.projeto_spring.dtos;

public record ProfessorSimplesDTO(
        Long idProfessor,
        String nome,
        String email,
        String departamento
) {}