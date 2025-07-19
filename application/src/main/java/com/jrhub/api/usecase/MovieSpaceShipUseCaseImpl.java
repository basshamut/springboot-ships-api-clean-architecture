package com.jrhub.api.usecase;

import com.jrhub.api.exception.ServiceException;
import com.jrhub.api.model.AuditEvent;
import com.jrhub.api.model.MovieSpaceShip;
import com.jrhub.api.repository.MovieSpaceShipRepository;
import com.jrhub.api.usecase.audit.AuditMessageSenderUseCase;
import com.jrhub.api.usecase.moviespaceship.MovieSpaceShipUseCase;

import java.util.List;
import java.util.Set;

public class MovieSpaceShipUseCaseImpl implements MovieSpaceShipUseCase {

    private final MovieSpaceShipRepository repository;
    private final AuditMessageSenderUseCase<AuditEvent> auditEventPublisher;

    public MovieSpaceShipUseCaseImpl(MovieSpaceShipRepository repository, AuditMessageSenderUseCase<AuditEvent> auditEventPublisher) {
        this.repository = repository;
        this.auditEventPublisher = auditEventPublisher;
    }

    @Override
    public List<MovieSpaceShip> getSpaceShips(int page, int size, String sortBy, String orderBy) {
        return repository.getSpaceShips(page, size, sortBy, orderBy);
    }

    @Override
    public Set<MovieSpaceShip> getSpaceShipByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ServiceException("Name cannot be null or empty", 400);
        }
        return repository.findByNameContaining(name.trim());
    }

    @Override
    public MovieSpaceShip getSpaceShipById(Long id) {
        if (id == null || id <= 0) {
            throw new ServiceException("SpaceShip id must be greater than 0", 400);
        }
        return repository.findById(id).orElseThrow(() -> new ServiceException("SpaceShip not found with id: " + id, 404));
    }

    @Override
    public MovieSpaceShip saveSpaceShip(MovieSpaceShip movieSpaceShip, String user) {
        if (movieSpaceShip == null) {
            throw new ServiceException("MovieSpaceShip cannot be null", 400);
        }

        MovieSpaceShip savedShip = repository.save(movieSpaceShip);

        AuditEvent auditEvent = AuditEvent.create(user, "CREATE", savedShip);
        auditEventPublisher.sendAuditMessage(auditEvent);

        return savedShip;
    }

    @Override
    public void deleteSpaceShip(Long id, String user) {
        if (id == null || id <= 0) {
            throw new ServiceException("SpaceShip id must be greater than 0", 400);
        }

        MovieSpaceShip ship = repository.findById(id)
                .orElseThrow(() -> new ServiceException("SpaceShip not found with id: " + id, 404));

        repository.deleteById(id);

        AuditEvent auditEvent = AuditEvent.create(user, "DELETE", ship);
        auditEventPublisher.sendAuditMessage(auditEvent);
    }

    @Override
    public MovieSpaceShip updateSpaceShip(Long id, String user, MovieSpaceShip movieSpaceShip) {
        if (movieSpaceShip == null) {
            throw new ServiceException("MovieSpaceShip cannot be null", 400);
        }

        if (id == null) {
            throw new ServiceException("SpaceShip id cannot be null for update", 400);
        }

        if (!repository.existsById(id)) {
            throw new ServiceException("SpaceShip not found with id: " + id, 404);
        }

        MovieSpaceShip updatedShip = repository.save(movieSpaceShip);

        AuditEvent auditEvent = AuditEvent.create(user, "UPDATE", updatedShip);
        auditEventPublisher.sendAuditMessage(auditEvent);

        return updatedShip;
    }
}
