package dantas.diego.projetospring.service;

import dantas.diego.projetospring.model.Comodo;
import dantas.diego.projetospring.repository.ComodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComodoService {

    private final ComodoRepository comodoRepository;
    public Comodo criar(Comodo comodo){
        if(comodo.getId() != null){
            throw new IllegalArgumentException();
        }
        comodo.setId((UUID.randomUUID().toString()));
        comodo.setDataGravacao(LocalDateTime.now());
        return comodoRepository.save(comodo);
    }

    public Comodo obter(String id){
        return comodoRepository.findById(id).orElse(null);
    }

    public List<Comodo> obterTodos(){
        List<Comodo> lista = new ArrayList<>();
        comodoRepository.findAll().forEach(comodo -> lista.add(comodo));
        return lista;
    }

    public Comodo atualizar(Comodo comodo){
        comodo.setDataGravacao(LocalDateTime.now());
        return comodoRepository.save(comodo);
    }

    public Comodo delete(String id){
        Comodo comodo = comodoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        comodoRepository.delete(comodo);
        return comodo;
    }
}
