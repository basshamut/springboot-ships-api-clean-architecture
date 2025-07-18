package com.jrhub.api.domain.usecase.moviespaceship;

import com.jrhub.api.domain.model.MovieSpaceShip;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Pure domain use case interface
 * No dependencies on frameworks or infrastructure
 */
public interface MovieSpaceShipUseCase {
    
    List<MovieSpaceShip> getSpaceShips(int page, int size, String sortBy, String orderBy);
    
    Set<MovieSpaceShip> getSpaceShipByName(String name);
    
    MovieSpaceShip getSpaceShipById(Long id);
    
    MovieSpaceShip saveSpaceShip(MovieSpaceShip movieSpaceShip, String user);
    
    void deleteSpaceShip(Long id, String user);
    
    MovieSpaceShip updateSpaceShip(MovieSpaceShip movieSpaceShip, String user);
} 