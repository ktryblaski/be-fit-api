package pl.karol_trybalski.befit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karol_trybalski.befit.domain.entity.Meal;
import pl.karol_trybalski.befit.domain.entity.MealView;
import pl.karol_trybalski.befit.persistence.repository.MealRepository;
import pl.karol_trybalski.befit.persistence.repository.MealViewRepository;
import pl.karol_trybalski.befit.service.base.BaseService;

import java.util.List;

@Service
public class MealServiceImpl extends BaseService<Meal, Long> {

    private final MealViewRepository viewRepository;

    @Autowired
    public MealServiceImpl(final MealRepository repository,
                           final MealViewRepository viewRepository) {
        super(repository);
        this.viewRepository = viewRepository;
    }

    public List<MealView> findAllView() {
        return viewRepository.findAll();
    }

}
