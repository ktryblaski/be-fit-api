package pl.karol_trybalski.befit.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.karol_trybalski.befit.dto.dto.DietDTO;
import pl.karol_trybalski.befit.dto.dto.DietViewDTO;
import pl.karol_trybalski.befit.dto.mapper.DietMapper;
import pl.karol_trybalski.befit.service.DietServiceImpl;
import pl.karol_trybalski.befit.webservice.ApiResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/diets")
public class DietController {

    private final DietServiceImpl dietService;

    @Autowired
    public DietController(final DietServiceImpl dietService) {
        this.dietService = dietService;
    }

    @GetMapping(path = "/lite")
    public ApiResponse<List<DietViewDTO>> listLite() {
        return ApiResponse.from(
                dietService.findAllView().stream().map(DietMapper.INSTANCE::map).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<DietDTO> get(final @PathVariable Long id) {
        return ApiResponse.from(
                DietMapper.INSTANCE.map(dietService.getOne(id))
        );
    }
}
