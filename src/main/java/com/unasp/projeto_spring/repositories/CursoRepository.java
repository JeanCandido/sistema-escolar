package com.unasp.projeto_spring.repositories;

import com.unasp.projeto_spring.entitys.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
