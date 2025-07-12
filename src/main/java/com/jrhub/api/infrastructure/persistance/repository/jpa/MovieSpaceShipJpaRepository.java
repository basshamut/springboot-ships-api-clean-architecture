package com.jrhub.api.infrastructure.persistance.repository.jpa;

import com.jrhub.api.infrastructure.persistance.entities.MovieSpaceShipEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MovieSpaceShipJpaRepository extends CrudRepository<MovieSpaceShipEntity, Long> {
    Page<MovieSpaceShipEntity> findAll(Pageable pageable);
    Set<MovieSpaceShipEntity> findByNameContaining(String name);
    Optional<MovieSpaceShipEntity> findById(Long id);
    boolean existsById(Long id);
}
