package com.jrhub.api.domain.repository;

import com.jrhub.api.domain.model.MovieSpaceShip;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Domain repository interface - defines contract for persistence operations
 * This is part of the domain layer in Clean Architecture
 */
public interface MovieSpaceShipRepository {

    List<MovieSpaceShip> getSpaceShips(int page, int size, String sortBy, String sortOrder);

    Set<MovieSpaceShip> findByNameContaining(String name);

    Optional<MovieSpaceShip> findById(Long id);

    MovieSpaceShip save(MovieSpaceShip movieSpaceShip);

    void deleteById(Long id);

    boolean existsById(Long id);

    long count();
}
