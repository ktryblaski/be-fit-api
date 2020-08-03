package pl.karol_trybalski.befit.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.dto.dto.MealCUDTO;
import pl.karol_trybalski.befit.dto.dto.MealDTO;
import pl.karol_trybalski.befit.dto.dto.MealViewDTO;
import pl.karol_trybalski.befit.dto.mapper.MealMapper;
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

    @PostMapping
    public Long create(final @RequestBody MealCUDTO meal) {
        return mealService.create(meal);
    }

    @PutMapping("/{id}")
    public MealDTO create(final @PathVariable Long id,
                          final @RequestBody MealCUDTO meal) {
        return MealMapper.INSTANCE.map(mealService.update(id, meal));
    }
}
