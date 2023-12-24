package elysonvnc.projeto.academia.domain.dto;

import elysonvnc.projeto.academia.domain.entities.Aluno;
import elysonvnc.projeto.academia.enums.PlanoAcademia;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoDto {

    @NotNull(message = "Preencha o campo nome, ele não pode ser vazio.")
    private String nome;

    @NotNull(message = "Não achamos esse plano, por favor preencha com algum desses: MENSAL, BIMESTRAL, TRIMESTRAL ou ANUAL.")
    private PlanoAcademia planoAcademia;

    public AlunoDto(String nome, PlanoAcademia planoAcademia) {
        this.nome = nome;
        this.planoAcademia = planoAcademia;
    }

}
