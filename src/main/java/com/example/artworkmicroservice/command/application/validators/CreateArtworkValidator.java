package com.example.artworkmicroservice.command.application.validators;

import com.example.artworkmicroservice.command.application.dtos.request.CreateArtworkRequest;
import com.example.artworkmicroservice.command.infrastructure.ArtworkRegistryRepository;
import com.example.artworkmicroservice.common.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class CreateArtworkValidator {
    private final ArtworkRegistryRepository artworkRegistryRepository;
    public CreateArtworkValidator(ArtworkRegistryRepository artworkRegistryRepository) {
        this.artworkRegistryRepository = artworkRegistryRepository;
    }

    public Notification validate(CreateArtworkRequest createArtworkRequest) {
        Notification notification = new Notification();

        String description = createArtworkRequest.getDescription().trim();
        if (description.isEmpty()) {
            notification.addError("Artwork description is required");
        }
        String title = createArtworkRequest.getTitle().trim();
        if (title.isEmpty()) {
            notification.addError("Artwork title is required");
        }
        String price = createArtworkRequest.getPrice().toString().trim();
        if (price.toString().isEmpty()) {
            notification.addError("Artwork price is required");
        }

        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
