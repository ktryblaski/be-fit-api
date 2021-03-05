package pl.karol_trybalski.befit.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.api.util.PaginationUtils;
import pl.karol_trybalski.befit.domain.module.recipe.RecipeSortBy;
import pl.karol_trybalski.befit.dto.dto.recipe.RecipeCUDTO;
import pl.karol_trybalski.befit.dto.dto.recipe.RecipeDTO;
import pl.karol_trybalski.befit.dto.dto.recipe.RecipeViewDTO;
import pl.karol_trybalski.befit.dto.mapper.RecipeMapper;
import pl.karol_trybalski.befit.service.recipe.RecipeServiceImpl;
import pl.karol_trybalski.befit.service.util.pagination.Pagination;

@RestController
@RequestMapping(path = "/recipes")
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    @Autowired
    public RecipeController(final RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public Page<RecipeViewDTO> findAll(@RequestParam MultiValueMap<String, String> params) {
        Pagination<RecipeSortBy> pagination = PaginationUtils.buildPagination(params, RecipeSortBy.class);
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
