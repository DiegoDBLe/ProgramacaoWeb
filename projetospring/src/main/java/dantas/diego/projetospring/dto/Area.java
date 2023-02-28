package dantas.diego.projetospring.dto;

import lombok.Data;

@Data
public class Area {

    private Comodo comodo;

    public Float getAreaPiso(){
        return comodo.getLargura() * comodo.getComprimento();
    }

    public Float getParede(){
        return (comodo.getLargura() * comodo.getAltura() + comodo.getComprimento() * comodo.getAltura()) * 2;
    }
}
