package pl.karol_trybalski.befit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karol_trybalski.befit.domain.day_of_eating.DayOfEatingBegin;
import pl.karol_trybalski.befit.domain.day_of_eating.DayOfEatingBeginOrigin;
import pl.karol_trybalski.befit.domain.entity.Ingredient;
import pl.karol_trybalski.befit.domain.entity.day_of_eating.DayOfEating;
import pl.karol_trybalski.befit.domain.entity.day_of_eating.DayOfEatingLite;
import pl.karol_trybalski.befit.domain.entity.meal.Meal;
import pl.karol_trybalski.befit.domain.exception.DomainError;
import pl.karol_trybalski.befit.domain.exception.DomainException;
import pl.karol_trybalski.befit.persistence.repository.DayOfEatingLiteRepository;
import pl.karol_trybalski.befit.persistence.repository.DayOfEatingRepository;
import pl.karol_trybalski.befit.persistence.repository.IngredientRepository;
import pl.karol_trybalski.befit.persistence.repository.MealRepository;
import pl.karol_trybalski.befit.service.base.BaseService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DayOfEatingServiceImpl extends BaseService<DayOfEating, DayOfEatingRepository, Long> {

    private final DayOfEatingLiteRepository liteRepository;
    private final MealRepository mealRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public DayOfEatingServiceImpl(final DayOfEatingRepository repository,
                                  final DayOfEatingLiteRepository liteRepository,
                                  final IngredientRepository ingredientRepository,
                                  final MealRepository mealRepository) {
        super(repository);
        this.liteRepository = liteRepository;
        this.ingredientRepository = ingredientRepository;
        this.mealRepository = mealRepository;
    }

    public List<DayOfEatingLite> findAllLites() {
        return liteRepository.findAll();
    }

    public Long create(final DayOfEatingBegin begin) {
        if (!canBeginDayOfEating()) {
            throw new DomainException(DomainError.DAY_OF_EATING_DAY_ALREADY_BEGAN);
        }

        if(begin.getOrigin() == null ||
          (DayOfEatingBeginOrigin.AS_COPY.equals(begin.getOrigin()) && begin.getOriginDayId() == null) ||
          (DayOfEatingBeginOrigin.NEW.equals(begin.getOrigin()) && begin.getOriginDayId() != null)) {
            throw new DomainException(DomainError.DAY_OF_EATING_INVALID_CREATION_DATA);
        }

        return DayOfEatingBeginOrigin.AS_COPY.equals(begin.getOrigin())
          ? createAsCopy(begin.getOriginDayId())
          : createNew();
    }

    public boolean canBeginDayOfEating() {
        return !repository.existsDayOfEatingByDayDate(LocalDate.now());
    }

    private Long createNew() {
        DayOfEating dayOfEating = new DayOfEating();
        dayOfEating.setDayDate(LocalDate.now());

        dayOfEating = repository.save(dayOfEating);

        return dayOfEating.getId();
    }

    private Long createAsCopy(Long originDayId) {
        Optional<DayOfEating> originOptional = findById(originDayId);
        if(!originOptional.isPresent()) {
            throw new DomainException(DomainError.DAY_OF_EATING_ORIGIN_DAY_NOT_EXIST);
        }

        DayOfEating origin = originOptional.get();
        Set<Meal> meals = new HashSet<>();
        for (Meal originMeal : origin.getMeals()) {
            Meal meal = new Meal();
            meal.setName(originMeal.getName());
            meal.setDescription(originMeal.getDescription());
            meal.setIngredients(new HashSet<>());

            Set<Ingredient> ingredients = new HashSet<>();
            for (Ingredient originIngredient : originMeal.getIngredients()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setWeight(originIngredient.getWeight());
                ingredient.setProduct(originIngredient.getProduct());

                ingredients.add(ingredient);
            }

            meal.setIngredients(new HashSet<>(ingredientRepository.saveAll(ingredients)));
            meals.add(meal);
        }

        DayOfEating dayOfEating = new DayOfEating();
        dayOfEating.setDayDate(LocalDate.now());
        dayOfEating.setMeals(new HashSet<>(mealRepository.saveAll(meals)));

        dayOfEating = repository.save(dayOfEating);

        return dayOfEating.getId();
    }

}
