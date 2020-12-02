package pl.karol_trybalski.befit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karol_trybalski.befit.domain.entity.Ingredient;
import pl.karol_trybalski.befit.domain.entity.meal.Meal;
import pl.karol_trybalski.befit.domain.module.product.Product;
import pl.karol_trybalski.befit.dto.dto.meal.MealCUDTO;
import pl.karol_trybalski.befit.persistence.repository.IngredientRepository;
import pl.karol_trybalski.befit.persistence.repository.MealRepository;
import pl.karol_trybalski.befit.persistence.repository.product.ProductRepository;
import pl.karol_trybalski.befit.service.base.BaseService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl extends BaseService<Meal, MealRepository, Long> {

    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;

    @Autowired
    public MealServiceImpl(final MealRepository repository,
                           final IngredientRepository ingredientRepository,
                           final ProductRepository productRepository) {
        super(repository);
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository;
    }

    public Long create(MealCUDTO meal) {
        List<Product> products = productRepository.findAllById(
                meal.getIngredients().stream().map(MealCUDTO.IngredientDTO::getProductId).collect(Collectors.toSet())
        );
        Map<Long, Product> productsMap = products.stream().collect(Collectors.toMap(Product::getId, p -> p));

        Set<Ingredient> ingredients = meal.getIngredients().stream().map(ingredient -> ingredientRepository.save(
                Ingredient.builder()
                        .product(productsMap.get(ingredient.getProductId()))
                        .weight(ingredient.getWeight())
                        .build()
        )).collect(Collectors.toSet());

        Meal m = new Meal();
        m.setName(meal.getName());
        m.setDescription(meal.getDescription());
        m.setIngredients(ingredients);
        m = repository.save(m);

        return m.getId();
    }

    public Meal update(Long id, MealCUDTO meal) {
        // TODO handle id != meal.getId()
        Meal m = repository.getOne(id);
        m.setName(meal.getName());
        m.setDescription(meal.getDescription());

        Set<Ingredient> ingredientsToSave = new HashSet<>();
        Map<Long, Integer> ingredientsMap = meal.getIngredients().stream().collect(Collectors.toMap(MealCUDTO.IngredientDTO::getProductId, MealCUDTO.IngredientDTO::getWeight));

        for (Ingredient ingredient : m.getIngredients()) {
            if(ingredientsMap.containsKey(ingredient.getId())) {
                ingredient.setWeight(ingredientsMap.get(ingredient.getId()));
                ingredientsToSave.add(ingredient);
                ingredientsMap.remove(ingredient.getId());
            }
        }

        List<Product> products = productRepository.findAllById(ingredientsMap.keySet());
        Map<Long, Product> productsMap = products.stream().collect(Collectors.toMap(Product::getId, p -> p));

        Set<Ingredient> newIngredients = ingredientsMap.entrySet().stream().map(entry -> ingredientRepository.save(
                Ingredient.builder().
                        product(productsMap.get(entry.getKey()))
                        .weight(entry.getValue())
                        .build()
        )).collect(Collectors.toSet());
        ingredientsToSave.addAll(newIngredients);

        m.setIngredients(ingredientsToSave);

        return repository.save(m);
    }

}
