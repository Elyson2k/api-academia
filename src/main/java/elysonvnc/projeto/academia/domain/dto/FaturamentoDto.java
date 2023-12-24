package elysonvnc.projeto.academia.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaturamentoDto {

    private Integer qtdAluno;
    private Double valorMensal;

    public FaturamentoDto(Integer qtdAluno, Double valorMensal) {
        this.qtdAluno = qtdAluno;
        this.valorMensal = valorMensal;
    }
}
