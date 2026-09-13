package com.unasp.projeto_spring.controllers;

import com.unasp.projeto_spring.dtos.ProfessorRequestDTO;
import com.unasp.projeto_spring.dtos.ProfessorResponseDTO;
import com.unasp.projeto_spring.services.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("professor")
@RestController
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public List<ProfessorResponseDTO> listarProfessores() {
        return professorService.listarProfessores();
    }

    @PostMapping
    public ProfessorResponseDTO cadastrarProfessor(
            @RequestBody ProfessorRequestDTO dto) {

        return professorService.cadastrarProfessor(dto);
    }
}