package com.jrhub.api.repository;

import com.jrhub.api.model.MovieSpaceShip;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MovieSpaceShipRepository {

    List<MovieSpaceShip> getSpaceShips(int page, int size, String sortBy, String sortOrder);

    Set<MovieSpaceShip> findByNameContaining(String name);

    Optional<MovieSpaceShip> findById(Long id);

    MovieSpaceShip save(MovieSpaceShip movieSpaceShip);

    void deleteById(Long id);

    boolean existsById(Long id);

    long count();
}
