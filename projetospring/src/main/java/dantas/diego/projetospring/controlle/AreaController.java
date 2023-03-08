package dantas.diego.projetospring.controlle;

import dantas.diego.projetospring.dto.Area;
import dantas.diego.projetospring.dto.ComodoRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("area")
public class AreaController {


    @GetMapping
    public Area getArea(ComodoRequest comodoRequest) {
        Area area = new Area();
        area.setComodo(comodoRequest);
        return area;
    }
}
