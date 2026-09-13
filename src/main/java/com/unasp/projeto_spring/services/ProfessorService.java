package com.unasp.projeto_spring.services;

import com.unasp.projeto_spring.dtos.CursoSimplesDTO;
import com.unasp.projeto_spring.dtos.ProfessorRequestDTO;
import com.unasp.projeto_spring.dtos.ProfessorResponseDTO;
import com.unasp.projeto_spring.entitys.Curso;
import com.unasp.projeto_spring.entitys.Professor;
import com.unasp.projeto_spring.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<ProfessorResponseDTO> listarProfessores() {

        return professorRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public ProfessorResponseDTO cadastrarProfessor(
            ProfessorRequestDTO dto) {

        Professor professor = new Professor();

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setDepartamento(dto.departamento());

        Professor professorSalvo =
                professorRepository.save(professor);

        return converterParaDTO(professorSalvo);
    }

    private ProfessorResponseDTO converterParaDTO(
            Professor professor) {

        List<CursoSimplesDTO> cursos = professor.getCursos()
                .stream()
                .map(this::converterCursoParaDTO)
                .toList();

        return new ProfessorResponseDTO(
                professor.getIdProfessor(),
                professor.getNome(),
                professor.getEmail(),
                professor.getDepartamento(),
                cursos
        );
    }

    private CursoSimplesDTO converterCursoParaDTO(
            Curso curso) {

        return new CursoSimplesDTO(
                curso.getIdCurso(),
                curso.getNome()
        );
    }
}