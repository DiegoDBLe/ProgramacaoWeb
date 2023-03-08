package dantas.diego.projetospring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comodo {
    @Id
    private String id;

    private String nome;
    private Float largura;
    private Float comprimento;
    private Float altura;
    private LocalDateTime dataGravacao;

}
