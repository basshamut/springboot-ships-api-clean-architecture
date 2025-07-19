package com.jrhub.api.persistance.repository;

import com.jrhub.api.mapper.MovieSpaceShipEntityMapper;
import com.jrhub.api.model.MovieSpaceShip;
import com.jrhub.api.repository.MovieSpaceShipRepository;
import com.jrhub.api.persistance.entities.MovieSpaceShipEntity;
import com.jrhub.api.persistance.repository.jpa.MovieSpaceShipJpaRepository;
import lombok.RequiredArgsConstructor;
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
 * Now uses a dedicated mapper for entity conversion, following SRP
 */
@Repository
@RequiredArgsConstructor
public class MovieSpaceShipSQLRepository implements MovieSpaceShipRepository {

    private final MovieSpaceShipJpaRepository jpaRepository;

    @Override
    public List<MovieSpaceShip> getSpaceShips(int page, int size, String sortBy, String sortOrder) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<MovieSpaceShipEntity> shipEntities = jpaRepository.findAll(pageRequest);

        return shipEntities.getContent().stream()
                .map(MovieSpaceShipEntityMapper.INSTANCE::toDomain)
                .toList();
    }

    @Override
    public Set<MovieSpaceShip> findByNameContaining(String name) {
        return jpaRepository.findByNameContaining(name)
                .stream()
                .map(MovieSpaceShipEntityMapper.INSTANCE::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<MovieSpaceShip> findById(Long id) {
        return jpaRepository.findById(id)
                .map(MovieSpaceShipEntityMapper.INSTANCE::toDomain);
    }

    @Override
    public MovieSpaceShip save(MovieSpaceShip movieSpaceShip) {
        MovieSpaceShipEntity entity = MovieSpaceShipEntityMapper.INSTANCE.toEntity(movieSpaceShip);
        MovieSpaceShipEntity savedEntity = jpaRepository.save(entity);
        return MovieSpaceShipEntityMapper.INSTANCE.toDomain(savedEntity);
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
}
