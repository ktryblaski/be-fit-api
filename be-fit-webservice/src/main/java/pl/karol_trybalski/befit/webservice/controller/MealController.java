package pl.karol_trybalski.befit.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.dto.dto.MealCUDTO;
import pl.karol_trybalski.befit.dto.dto.MealDTO;
import pl.karol_trybalski.befit.dto.dto.MealViewDTO;
import pl.karol_trybalski.befit.dto.mapper.MealMapper;
import pl.karol_trybalski.befit.service.MealServiceImpl;
import pl.karol_trybalski.befit.webservice.ApiResponse;

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
    public ApiResponse<List<MealViewDTO>> listLite() {
        return ApiResponse.from(
                mealService.findAllView().stream().map(MealMapper.INSTANCE::map).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<MealDTO> get(final @PathVariable Long id) {
        return ApiResponse.from(
                MealMapper.INSTANCE.map(mealService.getOne(id))
        );
    }

    @PostMapping
    public ApiResponse<Long> create(final @RequestBody MealCUDTO meal) {
        return ApiResponse.from(
                mealService.create(meal)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<MealDTO> create(final @PathVariable Long id,
                                       final @RequestBody MealCUDTO meal) {

        return ApiResponse.from(
                MealMapper.INSTANCE.map(mealService.update(id, meal))
        );
    }
}
