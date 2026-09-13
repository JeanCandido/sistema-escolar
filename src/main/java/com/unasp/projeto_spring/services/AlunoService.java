package com.unasp.projeto_spring.services;

import com.unasp.projeto_spring.dtos.AlunoRequestDTO;
import com.unasp.projeto_spring.dtos.AlunoResponseDTO;
import com.unasp.projeto_spring.dtos.CursoSimplesDTO;
import com.unasp.projeto_spring.entitys.Aluno;
import com.unasp.projeto_spring.entitys.Curso;
import com.unasp.projeto_spring.repositories.AlunoRepository;
import com.unasp.projeto_spring.repositories.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public AlunoService(
            AlunoRepository alunoRepository,
            CursoRepository cursoRepository) {

        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<AlunoResponseDTO> listarAlunos() {

        return alunoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public AlunoResponseDTO cadastrarAluno(AlunoRequestDTO dto) {

        Curso curso = cursoRepository.findById(dto.idCurso())
                .orElseThrow(() ->
                        new RuntimeException("Curso não encontrado"));

        Aluno aluno = new Aluno();

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setCurso(curso);

        Aluno alunoSalvo = alunoRepository.save(aluno);

        return converterParaDTO(alunoSalvo);
    }

    private AlunoResponseDTO converterParaDTO(Aluno aluno) {

        Curso curso = aluno.getCurso();

        CursoSimplesDTO cursoDTO = null;

        if (curso != null) {
            cursoDTO = new CursoSimplesDTO(
                    curso.getIdCurso(),
                    curso.getNome()
            );
        }

        return new AlunoResponseDTO(
                aluno.getIdAluno(),
                aluno.getNome(),
                aluno.getEmail(),
                cursoDTO
        );
    }
}