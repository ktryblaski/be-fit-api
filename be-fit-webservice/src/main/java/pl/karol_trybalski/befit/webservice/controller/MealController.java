package pl.karol_trybalski.befit.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.karol_trybalski.befit.dto.dto.MealDTO;
import pl.karol_trybalski.befit.dto.dto.MealViewDTO;
import pl.karol_trybalski.befit.dto.dto.ProductDTO;
import pl.karol_trybalski.befit.dto.mapper.MealMapper;
import pl.karol_trybalski.befit.dto.mapper.ProductMapper;
import pl.karol_trybalski.befit.service.MealServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/meals")
public class MealController {

    private final MealServiceImpl mealService;

    @Autowired
    public MealController(final MealServiceImpl mealService) {
        this.mealService = mealService;
    }

    @GetMapping(path = "/lite")
    public List<MealViewDTO> listLite() {
        return mealService.findAllView()
                .stream()
                .map(MealMapper.INSTANCE::map)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MealDTO get(final @PathVariable Long id) {
        return mealService.findById(id)
                .map(MealMapper.INSTANCE::map)
                .orElse(null);
    }
}
