package dantas.diego.projetospring.dto;

import lombok.Data;

@Data
public class ComodoRequest {
    private String nome;
    private Float largura;
    private Float comprimento;
    private Float altura;

}
