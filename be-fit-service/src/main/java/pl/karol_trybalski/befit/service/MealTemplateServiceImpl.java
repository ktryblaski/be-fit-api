package pl.karol_trybalski.befit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.karol_trybalski.befit.domain.entity.Ingredient;
import pl.karol_trybalski.befit.domain.entity.Product;
import pl.karol_trybalski.befit.domain.entity.meal_template.MealTemplate;
import pl.karol_trybalski.befit.domain.exception.DomainError;
import pl.karol_trybalski.befit.domain.exception.DomainException;
import pl.karol_trybalski.befit.dto.dto.meal_template.MealTemplateCUDTO;
import pl.karol_trybalski.befit.persistence.repository.*;
import pl.karol_trybalski.befit.service.base.BaseService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class MealTemplateServiceImpl extends BaseService<MealTemplate, MealTemplateRepository, Long> {

    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;

    @Autowired
    public MealTemplateServiceImpl(final MealTemplateRepository repository,
                                   final IngredientRepository ingredientRepository,
                                   final ProductRepository productRepository) {
        super(repository);
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository;
    }

    public List<MealTemplate> findAll(boolean onlyActive) {
        System.out.println(onlyActive);
        return onlyActive
          ? repository.findByActiveTrue()
          : findAll();
    }

    public Long create(MealTemplateCUDTO mealTemplate) {
        List<Product> products = productRepository.findAllById(
          mealTemplate.getIngredients().stream().map(MealTemplateCUDTO.IngredientDTO::getProductId).collect(Collectors.toSet())
        );
        Map<Long, Product> productsMap = products.stream().collect(Collectors.toMap(Product::getId, p -> p));

        Set<Ingredient> ingredients = mealTemplate.getIngredients().stream().map(ingredient -> ingredientRepository.save(
                Ingredient.builder()
                        .product(productsMap.get(ingredient.getProductId()))
                        .weight(ingredient.getWeight())
                        .build()
        )).collect(Collectors.toSet());

        MealTemplate m = new MealTemplate();
        m.setName(mealTemplate.getName());
        m.setDescription(mealTemplate.getDescription());
        m.setIngredients(ingredients);
        m.setActive(true);
        m = repository.save(m);

        return m.getId();
    }

    public MealTemplate update(Long id, MealTemplateCUDTO meal) {
        // TODO handle id != meal.getId()
        MealTemplate m = repository.getOne(id);
        m.setName(meal.getName());
        m.setDescription(meal.getDescription());
        m.setActive(meal.isActive());

        Set<Ingredient> ingredientsToSave = new HashSet<>();
        Map<Long, Integer> ingredientsMap = meal.getIngredients().stream().collect(
          Collectors.toMap(MealTemplateCUDTO.IngredientDTO::getProductId, MealTemplateCUDTO.IngredientDTO::getWeight)
        );

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

        return m;
    }

    public MealTemplate activate(Long id) {
        MealTemplate mealTemplate = repository.getOne(id);

        if(mealTemplate.isActive()) {
            throw new DomainException(DomainError.MEAL_TEMPLATE_ALREADY_ACTIVE);
        }

        mealTemplate.setActive(true);

        return mealTemplate;
    }

    public MealTemplate deactivate(Long id) {
        MealTemplate mealTemplate = repository.getOne(id);

        if(!mealTemplate.isActive()) {
            throw new DomainException(DomainError.MEAL_TEMPLATE_ALREADY_INACTIVE);
        }

        mealTemplate.setActive(false);

        return mealTemplate;
    }

}
