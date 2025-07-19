package com.jrhub.api.controller;

import com.jrhub.api.dto.MovieSpaceShipsDto;
import com.jrhub.api.usecase.moviespaceship.MovieSpaceShipUseCase;
import com.jrhub.api.controller.mapper.SpaceShipPresentationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import static com.jrhub.api.utils.Constants.API_VERSION_PATH;

@RestController
@RequestMapping(API_VERSION_PATH)
@RequiredArgsConstructor
public class MovieSpaceShipsController {

    private final MovieSpaceShipUseCase movieSpaceShipUseCase;
    private final SpaceShipPresentationMapper mapper;

    @GetMapping(path = "/space-ships")
    public ResponseEntity<List<MovieSpaceShipsDto>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                            @RequestParam(value = "size", defaultValue = "10") int size,
                                                            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                                            @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy) {

        var spaceShips = movieSpaceShipUseCase.getSpaceShips(page, size, sortBy, orderBy);
        var dtoList = mapper.toDtoList(spaceShips);
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(path = "/space-ships/{id}")
    public ResponseEntity<MovieSpaceShipsDto> findById(@PathVariable Long id) {
        var spaceShip = movieSpaceShipUseCase.getSpaceShipById(id);
        var dto = mapper.toDto(spaceShip);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(path = "/space-ships/search")
    public ResponseEntity<Set<MovieSpaceShipsDto>> findByName(@RequestParam String name) {
        var spaceShips = movieSpaceShipUseCase.getSpaceShipByName(name);
        var dtoSet = mapper.toDtoSet(spaceShips);
        return ResponseEntity.ok().body(dtoSet);
    }

    @PostMapping(path = "/space-ships")
    public ResponseEntity<MovieSpaceShipsDto> save(@RequestBody MovieSpaceShipsDto movieSpaceShipsDto) {
        var movieSpaceShip = mapper.toDomain(movieSpaceShipsDto);
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        var savedSpaceShip = movieSpaceShipUseCase.saveSpaceShip(movieSpaceShip, userName);
        var savedDto = mapper.toDto(savedSpaceShip);
        return ResponseEntity.ok().body(savedDto);
    }

    @PutMapping(path = "/space-ships/{id}")
    public ResponseEntity<MovieSpaceShipsDto> update(@PathVariable Long id, @RequestBody MovieSpaceShipsDto movieSpaceShipsDto) {
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        var movieSpaceShip = mapper.toDomain(movieSpaceShipsDto);
        var updatedSpaceShip = movieSpaceShipUseCase.updateSpaceShip(id, userName, movieSpaceShip);
        var updatedDto = mapper.toDto(updatedSpaceShip);
        return ResponseEntity.ok().body(updatedDto);
    }

    @DeleteMapping(path = "/space-ships/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        movieSpaceShipUseCase.deleteSpaceShip(id, userName);
        return ResponseEntity.ok().build();
    }
}
