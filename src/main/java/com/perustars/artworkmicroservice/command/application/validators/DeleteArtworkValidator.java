package com.perustars.artworkmicroservice.command.application.validators;

import com.perustars.artworkmicroservice.command.application.dtos.request.DeleteArtworkRequest;
import com.perustars.artworkmicroservice.command.infrastructure.ArtworkRegistryRepository;
import com.perustars.common.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class DeleteArtworkValidator {
    private final ArtworkRegistryRepository artworkRegistryRepository;
    public DeleteArtworkValidator(ArtworkRegistryRepository artistRegistryRepository) {
        this.artworkRegistryRepository = artistRegistryRepository;
    }

    public Notification validate(DeleteArtworkRequest deleteArtworkRequest) {
        Notification notification = new Notification();

        String id = deleteArtworkRequest.getId().trim();
        if (id.isEmpty()) {
            notification.addError("Artist does not exist");
        }

        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
