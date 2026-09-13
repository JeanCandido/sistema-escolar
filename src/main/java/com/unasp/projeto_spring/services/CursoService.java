package com.unasp.projeto_spring.services;

import com.unasp.projeto_spring.dtos.CursoRequestDTO;
import com.unasp.projeto_spring.dtos.CursoResponseDTO;
import com.unasp.projeto_spring.dtos.ProfessorSimplesDTO;
import com.unasp.projeto_spring.entitys.Curso;
import com.unasp.projeto_spring.entitys.Professor;
import com.unasp.projeto_spring.repositories.CursoRepository;
import com.unasp.projeto_spring.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final ProfessorRepository professorRepository;

    public CursoService(
            CursoRepository cursoRepository,
            ProfessorRepository professorRepository) {

        this.cursoRepository = cursoRepository;
        this.professorRepository = professorRepository;
    }

    public List<CursoResponseDTO> listarCursos() {

        return cursoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public CursoResponseDTO adicionarCurso(CursoRequestDTO dto) {

        if (dto.idProfessor() == null) {
            throw new IllegalArgumentException(
                    "O ID do professor não pode ser nulo."
            );
        }

        Professor professor = professorRepository
                .findById(dto.idProfessor())
                .orElseThrow(() ->
                        new RuntimeException("Professor não encontrado")
                );

        Curso curso = new Curso();

        curso.setNome(dto.nome());
        curso.setProfessor(professor);

        Curso cursoSalvo = cursoRepository.save(curso);

        return converterParaDTO(cursoSalvo);
    }

    private CursoResponseDTO converterParaDTO(Curso curso) {

        Professor professor = curso.getProfessor();

        ProfessorSimplesDTO professorDTO = new ProfessorSimplesDTO(
                professor.getIdProfessor(),
                professor.getNome(),
                professor.getEmail(),
                professor.getDepartamento()
        );

        return new CursoResponseDTO(
                curso.getIdCurso(),
                curso.getNome(),
                professorDTO
        );
    }
}