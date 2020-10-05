package pl.karol_trybalski.befit.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.dto.dto.meal_template.MealTemplateCUDTO;
import pl.karol_trybalski.befit.dto.dto.meal_template.MealTemplateDTO;
import pl.karol_trybalski.befit.dto.mapper.MealTemplateMapper;
import pl.karol_trybalski.befit.service.MealTemplateServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/meal-templates")
public class MealTemplateController {

    private final MealTemplateServiceImpl mealTemplateService;

    @Autowired
    public MealTemplateController(final MealTemplateServiceImpl mealTemplateService) {
        this.mealTemplateService = mealTemplateService;
    }

    @GetMapping
    public List<MealTemplateDTO> findAllLites() {
        return mealTemplateService.findAll().stream().map(MealTemplateMapper.INSTANCE::map).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MealTemplateDTO get(final @PathVariable Long id) {
        return MealTemplateMapper.INSTANCE.map(mealTemplateService.getOne(id));
    }

    @PostMapping
    public Long create(final @RequestBody MealTemplateCUDTO meal) {
        return mealTemplateService.create(meal);
    }

    @PutMapping("/{id}")
    public MealTemplateDTO update(final @PathVariable Long id, final @RequestBody MealTemplateCUDTO meal) {
        return MealTemplateMapper.INSTANCE.map(mealTemplateService.update(id, meal));
    }
}
