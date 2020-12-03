package pl.karol_trybalski.befit.service.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pl.karol_trybalski.befit.domain.entity.Ingredient;
import pl.karol_trybalski.befit.domain.module.product.Product;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;
import pl.karol_trybalski.befit.domain.module.recipe.Recipe;
import pl.karol_trybalski.befit.domain.exception.DomainError;
import pl.karol_trybalski.befit.domain.exception.DomainException;
import pl.karol_trybalski.befit.dto.dto.recipe.RecipeCUDTO;
import pl.karol_trybalski.befit.persistence.repository.*;
import pl.karol_trybalski.befit.persistence.repository.product.ProductRepository;
import pl.karol_trybalski.befit.persistence.repository.recipe.RecipeRepository;
import pl.karol_trybalski.befit.service.base.BaseService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecipeServiceImpl extends BaseService<Recipe, RecipeRepository, Long> {

    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;

    @Autowired
    public RecipeServiceImpl(final RecipeRepository repository,
                             final IngredientRepository ingredientRepository,
                             final ProductRepository productRepository) {
        super(repository);
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository;
    }

    public List<Recipe> findAll(boolean onlyActive) {
        return onlyActive
          ? repository.findByActiveTrue()
          : findAll();
    }

    public Long create(RecipeCUDTO recipe) {
        List<Product> products = productRepository.findAllById(
          recipe.getIngredients().stream().map(RecipeCUDTO.IngredientDTO::getProductId).collect(Collectors.toSet())
        );
        Map<Long, Product> productsMap = products.stream().collect(Collectors.toMap(Product::getId, p -> p));

        Set<Ingredient> ingredients = recipe.getIngredients().stream().map(ingredient -> ingredientRepository.save(
                Ingredient.builder()
                        .product(productsMap.get(ingredient.getProductId()))
                        .weight(ingredient.getWeight())
                        .build()
        )).collect(Collectors.toSet());

        Recipe m = new Recipe();
        m.setName(recipe.getName());
        m.setDescription(recipe.getDescription());
        m.setIngredients(ingredients);
        m.setActive(true);
        m = repository.save(m);

        return m.getId();
    }

    public Recipe update(Long id, RecipeCUDTO recipe) {
        Recipe mt = repository.getOne(id);

        checkUpdate(id, recipe, mt);

        mt.setName(recipe.getName());
        mt.setDescription(recipe.getDescription());
        mt.setIngredients(new HashSet<>());

        // update existing ingredients
        Map<Long, RecipeCUDTO.IngredientDTO> existingIngredientsMap = recipe.getIngredients().stream()
          .filter(i -> i.getId() != null)
          .collect(Collectors.toMap(RecipeCUDTO.IngredientDTO::getId, i -> i));

        List<Ingredient> existingIngredients = ingredientRepository.findAllById(existingIngredientsMap.keySet());
        for (Ingredient existingIngredient : existingIngredients) {
            existingIngredient.setWeight(
              existingIngredientsMap.get(existingIngredient.getId()).getWeight()
            );
        }
        mt.getIngredients().addAll(existingIngredients);

        // save new ingredients
        Set<Long> productIdsForNewIngredients = recipe.getIngredients().stream()
          .filter(i -> i.getId() == null)
          .map(RecipeCUDTO.IngredientDTO::getProductId)
          .collect(Collectors.toSet());

        List<Product> products = productRepository.findAllById(productIdsForNewIngredients);
        Map<Long, Product> productsMap = products.stream().collect(Collectors.toMap(Product::getId, p -> p));
        recipe.getIngredients().forEach(i -> {
            if(i.getId() == null) {
                Ingredient ingredient = ingredientRepository.save(
                  Ingredient.builder().product(productsMap.get(i.getProductId())).weight(i.getWeight()).build()
                );
                mt.getIngredients().add(ingredient);
            }
        });

        return mt;
    }

    private void checkUpdate(Long id, RecipeCUDTO recipe, Recipe mt) {
        if(!id.equals(recipe.getId())) {
            throw new DomainException(DomainError.IDENTIFIERS_NOT_MATCH);
        }

        // validate existing ingredients
        if(CollectionUtils.isEmpty(recipe.getIngredients())) {
            throw new DomainException(DomainError.RECIPE_NO_INGREDIENTS);
        }
        Set<Long> oldIngredientIds = mt.getIngredients().stream().map(BaseEntity::getId).collect(Collectors.toSet());
        for (RecipeCUDTO.IngredientDTO ingredient : recipe.getIngredients()) {
            if (ingredient.getId() != null && !oldIngredientIds.contains(ingredient.getId())) {
                throw new DomainException(DomainError.RECIPE_GIVEN_INGREDIENT_NOT_EXIST);
            }
        }
    }

    public Recipe activate(Long id) {
        Recipe recipe = repository.getOne(id);

        if(recipe.isActive()) {
            throw new DomainException(DomainError.RECIPE_ALREADY_ACTIVE);
        }

        recipe.setActive(true);

        return recipe;
    }

    public Recipe deactivate(Long id) {
        Recipe recipe = repository.getOne(id);

        if(!recipe.isActive()) {
            throw new DomainException(DomainError.RECIPE_ALREADY_INACTIVE);
        }

        recipe.setActive(false);

        return recipe;
    }

}
