package pl.karol_trybalski.befit.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.dto.dto.recipe.RecipeCUDTO;
import pl.karol_trybalski.befit.dto.dto.recipe.RecipeDTO;
import pl.karol_trybalski.befit.dto.mapper.RecipeMapper;
import pl.karol_trybalski.befit.service.recipe.RecipeServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/recipes")
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    @Autowired
    public RecipeController(final RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<RecipeDTO> findAll(@RequestParam(defaultValue = "false") boolean onlyActive) {
        return recipeService.findAll(onlyActive).stream().map(RecipeMapper.INSTANCE::map).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RecipeDTO get(final @PathVariable Long id) {
        return RecipeMapper.INSTANCE.map(recipeService.getOne(id));
    }

    @PostMapping
    public Long create(final @RequestBody RecipeCUDTO meal) {
        return recipeService.create(meal);
    }

    @PutMapping("/{id}")
    public RecipeDTO update(final @PathVariable Long id, final @RequestBody RecipeCUDTO meal) {
        return RecipeMapper.INSTANCE.map(recipeService.update(id, meal));
    }

    @PostMapping("/{id}/activate")
    public RecipeDTO activate(final @PathVariable Long id) {
        return RecipeMapper.INSTANCE.map(recipeService.activate(id));
    }

    @PostMapping("/{id}/deactivate")
    public RecipeDTO deactivate(final @PathVariable Long id) {
        return RecipeMapper.INSTANCE.map(recipeService.deactivate(id));
    }
}
