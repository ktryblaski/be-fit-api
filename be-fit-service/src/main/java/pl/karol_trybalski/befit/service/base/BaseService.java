package pl.karol_trybalski.befit.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, ID> {

    protected final BaseRepository<T, ID> repository;

    public BaseService(final BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return this.repository.findAll();
    }

    public List<T> findAll(final Sort sort) {
        return this.repository.findAll(sort);
    }

    public Page<T> findAll(final Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Optional<T> findById(final ID id) {
        return this.repository.findById(id);
    }

    public T getOne(final ID id) {
        return this.repository.getOne(id);
    }
}
