package elysonvnc.projeto.academia.repository;

import elysonvnc.projeto.academia.domain.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    @Query("SELECT a FROM Aluno a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Aluno> findByNomeContainingIgnoreCase(@Param("nome") String nome);

    @Query(value = "SELECT * FROM tb_aluno a WHERE statusAluno = 'INATIVO;'", nativeQuery = true)
    List<Aluno> findAlunoWhereStatusInativo();



}
