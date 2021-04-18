package pl.karol_trybalski.befit.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.dto.dto.recipe.RecipeCUDTO;
import pl.karol_trybalski.befit.dto.dto.recipe.RecipeDTO;
import pl.karol_trybalski.befit.dto.dto.recipe.RecipeViewDTO;
import pl.karol_trybalski.befit.dto.mapper.RecipeMapper;
import pl.karol_trybalski.befit.service.recipe.RecipeServiceImpl;
import pl.karol_trybalski.befit.service.util.pagination.Pagination;

@RequiredArgsConstructor
@RequestMapping(path = "/recipes")
@RestController
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    @GetMapping
    public Page<RecipeViewDTO> findAll(final Pagination pagination) {
        return recipeService.findAll(pagination).map(RecipeMapper.INSTANCE::map);
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
