package com.jrhub.api.infrastructure.persistance.repository;

import com.jrhub.api.domain.model.MovieSpaceShip;
import com.jrhub.api.domain.repository.MovieSpaceShipRepository;
import com.jrhub.api.infrastructure.persistance.entities.MovieSpaceShipEntity;
import com.jrhub.api.infrastructure.persistance.repository.jpa.MovieSpaceShipJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Adapter that implements the domain port using JPA
 * Converts between domain models and JPA entities
 */
@Repository
public class MovieSpaceShipSQLRepository implements MovieSpaceShipRepository {

    private final MovieSpaceShipJpaRepository jpaRepository;

    public MovieSpaceShipSQLRepository(MovieSpaceShipJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<MovieSpaceShip> getSpaceShips(int page, int size, String sortBy, String sortOrder) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<MovieSpaceShipEntity> shipEntities = jpaRepository.findAll(pageRequest);

        return shipEntities.getContent().stream()
                .map(this::toDomainEntity)
                .toList();
    }

    @Override
    public Set<MovieSpaceShip> findByNameContaining(String name) {
        return jpaRepository.findByNameContaining(name)
                .stream()
                .map(this::toDomainEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<MovieSpaceShip> findById(Long id) {
        return jpaRepository.findById(id)
                .map(this::toDomainEntity);
    }

    @Override
    public MovieSpaceShip save(MovieSpaceShip movieSpaceShip) {
        MovieSpaceShipEntity entity = toJpaEntity(movieSpaceShip);
        MovieSpaceShipEntity savedEntity = jpaRepository.save(entity);
        return toDomainEntity(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    private MovieSpaceShip toDomainEntity(MovieSpaceShipEntity entity) {
        return MovieSpaceShip.fromPersistence(
                entity.getId(),
                entity.getName(),
                entity.getMovie()
        );
    }

    private MovieSpaceShipEntity toJpaEntity(MovieSpaceShip domainEntity) {
        MovieSpaceShipEntity entity = new MovieSpaceShipEntity();
        entity.setId(domainEntity.getId());
        entity.setName(domainEntity.getName());
        entity.setMovie(domainEntity.getMovie());
        return entity;
    }
}
