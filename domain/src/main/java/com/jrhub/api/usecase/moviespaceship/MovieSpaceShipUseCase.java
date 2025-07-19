package com.jrhub.api.usecase.moviespaceship;

import com.jrhub.api.model.MovieSpaceShip;

import java.util.List;
import java.util.Set;

public interface MovieSpaceShipUseCase {
    
    List<MovieSpaceShip> getSpaceShips(int page, int size, String sortBy, String orderBy);
    
    Set<MovieSpaceShip> getSpaceShipByName(String name);
    
    MovieSpaceShip getSpaceShipById(Long id);
    
    MovieSpaceShip saveSpaceShip(MovieSpaceShip movieSpaceShip, String user);
    
    void deleteSpaceShip(Long id, String user);
    
    MovieSpaceShip updateSpaceShip(Long id, String user, MovieSpaceShip movieSpaceShip);
} 