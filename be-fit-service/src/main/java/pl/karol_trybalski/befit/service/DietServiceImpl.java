package pl.karol_trybalski.befit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karol_trybalski.befit.domain.entity.Diet;
import pl.karol_trybalski.befit.domain.entity.DietView;
import pl.karol_trybalski.befit.persistence.repository.DietRepository;
import pl.karol_trybalski.befit.persistence.repository.DietViewRepository;
import pl.karol_trybalski.befit.service.base.BaseService;

import java.util.List;

@Service
public class DietServiceImpl extends BaseService<Diet, Long> {

    private final DietViewRepository viewRepository;

    @Autowired
    public DietServiceImpl(final DietRepository repository,
                           final DietViewRepository viewRepository) {
        super(repository);
        this.viewRepository = viewRepository;
    }

    public List<DietView> findAllView() {
        return viewRepository.findAll();
    }

}
