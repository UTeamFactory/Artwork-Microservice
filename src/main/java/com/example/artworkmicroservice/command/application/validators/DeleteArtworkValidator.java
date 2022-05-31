package com.example.artworkmicroservice.command.application.validators;

import com.example.artworkmicroservice.command.application.dtos.request.DeleteArtworkRequest;
import com.example.artworkmicroservice.command.infrastructure.ArtworkRegistryRepository;
import com.example.artworkmicroservice.common.application.Notification;
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
            notification.addError("Artist is deleted or no exist");
        }

        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
