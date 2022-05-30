package com.example.artworkmicroservice.command.application.validators;

import com.example.artworkmicroservice.command.application.dtos.request.EditArtworkRequest;
import com.example.artworkmicroservice.command.domain.entities.Artwork;
import com.example.artworkmicroservice.command.infrastructure.ArtworkRegistryRepository;
import com.example.artworkmicroservice.common.application.Notification;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.stereotype.Component;
import org.axonframework.modelling.command.Repository;

@Component
public class EditArtworkValidator {
    private final ArtworkRegistryRepository artworkRegistryRepository;
    private final Repository<Artwork> artworkRepository;

    public EditArtworkValidator(ArtworkRegistryRepository artworkRegistryRepository, Repository<Artwork> artworkRepository) {
        this.artworkRegistryRepository = artworkRegistryRepository;
        this.artworkRepository = artworkRepository;
    }

    public Notification validate(EditArtworkRequest editArtworkRequest) {
        Notification notification = new Notification();
        String artworkId = editArtworkRequest.getId().trim();
        if(artworkId.isEmpty()) {
            notification.addError("Artwork id is required");
        }
        loadArtworkAggregate(artworkId);

        String description = editArtworkRequest.getDescription().trim();
        if (description.isEmpty()) {
            notification.addError("Artwork description is required");
        }
        String title = editArtworkRequest.getTitle().trim();
        if (title.isEmpty()) {
            notification.addError("Artwork title is required");
        }
        String price = editArtworkRequest.getPrice().toString().trim();
        if (price.toString().isEmpty()) {
            notification.addError("Artwork price is required");
        }

        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }

    private void loadArtworkAggregate(String artworkId) {
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            artworkRepository.load(artworkId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex) {
            unitOfWork.commit();
            throw ex;
        } catch (Exception ex) {
            if (unitOfWork != null) unitOfWork.rollback();
        }
    }
}
