package com.unasp.projeto_spring.controllers;

import com.unasp.projeto_spring.dtos.CursoRequestDTO;
import com.unasp.projeto_spring.dtos.CursoResponseDTO;
import com.unasp.projeto_spring.services.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("curso")
@RestController
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<CursoResponseDTO> listarCursos() {
        return cursoService.listarCursos();
    }

    @PostMapping
    public ResponseEntity<CursoResponseDTO> cadastrarCurso(
            @RequestBody CursoRequestDTO dto) {

        CursoResponseDTO cursoSalvo = cursoService.adicionarCurso(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cursoSalvo);
    }
}