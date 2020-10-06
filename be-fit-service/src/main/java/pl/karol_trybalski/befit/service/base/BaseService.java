package pl.karol_trybalski.befit.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<E, R extends BaseRepository<E, ID>, ID> {

    protected final R repository;

    public BaseService(final R repository) {
        this.repository = repository;
    }

    public List<E> findAll() {
        return this.repository.findAll();
    }

    public List<E> findAll(final Sort sort) {
        return this.repository.findAll(sort);
    }

    public Page<E> findAll(final Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Optional<E> findById(final ID id) {
        return this.repository.findById(id);
    }

    public E getOne(final ID id) {
        return this.repository.getOne(id);
    }
}
