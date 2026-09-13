package com.unasp.projeto_spring.controllers;

import com.unasp.projeto_spring.dtos.AlunoRequestDTO;
import com.unasp.projeto_spring.dtos.AlunoResponseDTO;
import com.unasp.projeto_spring.services.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("aluno")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<AlunoResponseDTO> listarAlunos() {
        return alunoService.listarAlunos();
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> cadastrarAluno(
            @RequestBody AlunoRequestDTO dto) {

        AlunoResponseDTO alunoSalvo =
                alunoService.cadastrarAluno(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(alunoSalvo);
    }
}