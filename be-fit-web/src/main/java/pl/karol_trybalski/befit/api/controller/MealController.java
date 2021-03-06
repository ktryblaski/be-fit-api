package pl.karol_trybalski.befit.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.dto.dto.meal.MealCUDTO;
import pl.karol_trybalski.befit.dto.dto.meal.MealDTO;
import pl.karol_trybalski.befit.dto.mapper.MealMapper;
import pl.karol_trybalski.befit.service.MealServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping(path = "/meals")
@RestController
public class MealController {

    private final MealServiceImpl mealService;

    @GetMapping
    public List<MealDTO> findAll() {
        return mealService.findAll().stream().map(MealMapper.INSTANCE::map).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MealDTO get(final @PathVariable Long id) {
        return MealMapper.INSTANCE.map(mealService.getOne(id));
    }

    @PostMapping
    public Long create(final @RequestBody MealCUDTO meal) {
        return mealService.create(meal);
    }

    @PutMapping("/{id}")
    public MealDTO create(final @PathVariable Long id, final @RequestBody MealCUDTO meal) {
        return MealMapper.INSTANCE.map(mealService.update(id, meal));
    }
}
