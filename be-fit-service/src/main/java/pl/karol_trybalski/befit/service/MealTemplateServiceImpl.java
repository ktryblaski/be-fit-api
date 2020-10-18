package pl.karol_trybalski.befit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pl.karol_trybalski.befit.domain.entity.Ingredient;
import pl.karol_trybalski.befit.domain.entity.Product;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;
import pl.karol_trybalski.befit.domain.entity.meal_template.MealTemplate;
import pl.karol_trybalski.befit.domain.exception.DomainError;
import pl.karol_trybalski.befit.domain.exception.DomainException;
import pl.karol_trybalski.befit.dto.dto.meal_template.MealTemplateCUDTO;
import pl.karol_trybalski.befit.persistence.repository.*;
import pl.karol_trybalski.befit.service.base.BaseService;

import java.util.*;
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

    public MealTemplate update(Long id, MealTemplateCUDTO mealTemplate) {
        MealTemplate mt = repository.getOne(id);

        checkUpdate(id, mealTemplate, mt);

        mt.setName(mealTemplate.getName());
        mt.setDescription(mealTemplate.getDescription());
        mt.setIngredients(new HashSet<>());

        // update existing ingredients
        Map<Long, MealTemplateCUDTO.IngredientDTO> existingIngredientsMap = mealTemplate.getIngredients().stream()
          .filter(i -> i.getId() != null)
          .collect(Collectors.toMap(MealTemplateCUDTO.IngredientDTO::getId, i -> i));

        List<Ingredient> existingIngredients = ingredientRepository.findAllById(existingIngredientsMap.keySet());
        for (Ingredient existingIngredient : existingIngredients) {
            existingIngredient.setWeight(
              existingIngredientsMap.get(existingIngredient.getId()).getWeight()
            );
        }
        mt.getIngredients().addAll(existingIngredients);

        // save new ingredients
        Set<Long> productIdsForNewIngredients = mealTemplate.getIngredients().stream()
          .filter(i -> i.getId() == null)
          .map(MealTemplateCUDTO.IngredientDTO::getProductId)
          .collect(Collectors.toSet());

        List<Product> products = productRepository.findAllById(productIdsForNewIngredients);
        Map<Long, Product> productsMap = products.stream().collect(Collectors.toMap(Product::getId, p -> p));
        mealTemplate.getIngredients().forEach(i -> {
            if(i.getId() == null) {
                Ingredient ingredient = ingredientRepository.save(
                  Ingredient.builder().product(productsMap.get(i.getProductId())).weight(i.getWeight()).build()
                );
                mt.getIngredients().add(ingredient);
            }
        });

        return mt;
    }

    private void checkUpdate(Long id, MealTemplateCUDTO mealTemplate, MealTemplate mt) {
        if(!id.equals(mealTemplate.getId())) {
            throw new DomainException(DomainError.IDENTIFIERS_NOT_MATCH);
        }

        // validate existing ingredients
        if(CollectionUtils.isEmpty(mealTemplate.getIngredients())) {
            throw new DomainException(DomainError.MEAL_TEMPLATE_NO_INGREDIENTS);
        }
        Set<Long> oldIngredientIds = mt.getIngredients().stream().map(BaseEntity::getId).collect(Collectors.toSet());
        for (MealTemplateCUDTO.IngredientDTO ingredient : mealTemplate.getIngredients()) {
            if (ingredient.getId() != null && !oldIngredientIds.contains(ingredient.getId())) {
                throw new DomainException(DomainError.MEAL_TEMPLATE_GIVEN_INGREDIENT_NOT_EXIST);
            }
        }
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
