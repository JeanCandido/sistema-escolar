package com.unasp.projeto_spring.repositories;

import com.unasp.projeto_spring.entitys.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
