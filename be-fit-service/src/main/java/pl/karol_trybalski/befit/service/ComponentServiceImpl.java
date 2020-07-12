package pl.karol_trybalski.befit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karol_trybalski.befit.service.base.BaseService;
import pl.karol_trybalski.befit.domain.entity.Component;
import pl.karol_trybalski.befit.persistence.repository.ComponentRepository;

@Service
public class ComponentServiceImpl extends BaseService<Component, Long> {

    @Autowired
    public ComponentServiceImpl(ComponentRepository repository) {
        super(repository);
    }

}
