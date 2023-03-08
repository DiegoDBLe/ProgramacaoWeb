package dantas.diego.projetospring.view;

import dantas.diego.projetospring.controlle.ComodoController;
import dantas.diego.projetospring.dto.ComodoRequest;
import dantas.diego.projetospring.dto.ComodoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.CacheResponse;

@Controller
@RequiredArgsConstructor
public class ComodoControllerView {
    private final ComodoController comodoController;

    @RequestMapping(value = {"/comodos"})
    public String showComodoList(Model model){
        model.addAttribute("comodos", comodoController.readAll());
        return "comodo-list";
    }

    @GetMapping("/comodo-new")
    public String showNovoComodo(Model model, ComodoResponse comodoResponse){
        model.addAttribute("comodo", comodoResponse);
        return "comodo-create";
    }

    @GetMapping("/comodo-edit/{id}")
    public String showEditComodo(@PathVariable("id") String id, Model model){
        ComodoResponse comodoResponse = comodoController.read(id);
        model.addAttribute("comodo", comodoResponse);
        return "comodo-update";
    }

    @GetMapping("/comodo-delete/{id}")
    public String showDeleteComodo(@PathVariable("id") String id, Model model){
        ComodoResponse comodoResponse = comodoController.read(id);
        model.addAttribute("comodo", comodoResponse);
        return "comodo-excluir";
    }

    @PostMapping("/excluir/{id}")
    public String deleteComodo(@PathVariable("id") String id){
        comodoController.delete(id);
        return "redirect:/comodos";
    }

    @PostMapping("/comodo-add")
    public String addComodo(ComodoResponse response, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "comodo-create";
        }

        ComodoRequest comodoRequest = new ComodoRequest();
        BeanUtils.copyProperties(response, comodoRequest);
        comodoController.create(comodoRequest);
        return "redirect:/comodos";
    }

    @PostMapping("/comodo-save/{id}")
    public String updateComodo(@PathVariable("id") String id, ComodoResponse comodoResponse,
                               BindingResult result, Model model){
        if (result.hasErrors()){
            comodoResponse.setId(id);
            return "comodo-update";
        }

        ComodoRequest comodoRequest = new ComodoRequest();
        BeanUtils.copyProperties(comodoResponse, comodoRequest);

        comodoController.update(comodoResponse.getId(), comodoRequest);
        return "redirect:/comodos";
    }
}
