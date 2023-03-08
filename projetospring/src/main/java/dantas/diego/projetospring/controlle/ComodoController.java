package dantas.diego.projetospring.controlle;

import dantas.diego.projetospring.dto.ComodoRequest;
import dantas.diego.projetospring.dto.ComodoResponse;
import dantas.diego.projetospring.model.Comodo;
import dantas.diego.projetospring.service.ComodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("comodo")
@RequiredArgsConstructor
public class ComodoController {

    private final ComodoService comodoService;
    @PostMapping
    public String create(@RequestBody ComodoRequest request){
        Comodo comodo = new Comodo();
        BeanUtils.copyProperties(request, comodo);
//        comodo.setNome(request.getNome());
//        comodo.setLargura(request.getLargura());
//        comodo.setComprimento(request.getComprimento());
//        comodo.setAltura(request.getAltura());
        comodo = comodoService.criar(comodo);
        return comodo.getId();
    }

    @GetMapping
    public List<ComodoResponse> readAll() {
        return comodoService.obterTodos().stream().map(comodo -> {
            ComodoResponse comodoResponse = new ComodoResponse();
            BeanUtils.copyProperties(comodo, comodoResponse);
            return comodoResponse;
        }).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ComodoResponse read(@PathVariable String id){
        Comodo comodo = comodoService.obter(id);
        ComodoResponse response = new ComodoResponse();
        BeanUtils.copyProperties(comodo,response);
//        response.setNome(comodo.getNome());
//        response.setComprimento(comodo.getComprimento());
//        response.setAltura(comodo.getAltura());
//        response.setLargura(comodo.getLargura());
        return response;
    }

    @PutMapping("{id}")
    public ComodoResponse update(@PathVariable String id, @RequestBody ComodoRequest comodoRequest){
        Comodo comodo = comodoService.obter(id);
        BeanUtils.copyProperties(comodoRequest, comodo);
        comodo = comodoService.atualizar(comodo);
        ComodoResponse response = new ComodoResponse();
        BeanUtils.copyProperties(comodo, response);
        return response;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        comodoService.delete(id);
    }
}