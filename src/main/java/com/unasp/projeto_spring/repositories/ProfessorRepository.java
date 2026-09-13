package com.unasp.projeto_spring.repositories;

import com.unasp.projeto_spring.entitys.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
