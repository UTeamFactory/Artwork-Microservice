package com.perustars.artworkmicroservice.command.application.handlers;

import com.perustars.artworkmicroservice.command.infrastructure.ArtworkRegistry;
import com.perustars.artworkmicroservice.command.infrastructure.ArtworkRegistryRepository;
import com.perustars.artwork.contracts.events.ArtworkDeleted;
import com.perustars.artwork.contracts.events.ArtworkEdited;
import com.perustars.artwork.contracts.events.ArtworkRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("artworkRegistry")
public class ArtworkEventHandler {
    private final ArtworkRegistryRepository artworkRegistryRepository;
    public ArtworkEventHandler(ArtworkRegistryRepository artworkRegistryRepository){
        this.artworkRegistryRepository = artworkRegistryRepository;
    }

    @EventHandler
    public void on(ArtworkRegistered event){
        artworkRegistryRepository.save(new ArtworkRegistry(
                event.getId(),
                event.getArtistId(),
                event.getDescription(),
                event.getImage(),
                event.getPrice(),
                event.getTitle(),
                event.getLink()
        ));
    }

    @EventHandler
    public void on(ArtworkEdited event){
        Optional<ArtworkRegistry> ArtworkRegistryOptional = artworkRegistryRepository.getByArtworkId(event.getId());
        ArtworkRegistryOptional.ifPresent(artworkRegistryRepository::delete);
        artworkRegistryRepository.save(new ArtworkRegistry(
                event.getId(),
                event.getArtistId(),
                event.getTitle(),
                event.getDescription(),
                event.getPrice(),
                event.getLink(),
                event.getImage()
        ));
    }

    @EventHandler
    public void on(ArtworkDeleted event){
        Optional<ArtworkRegistry> ArtworkRegistryOptional = artworkRegistryRepository.getByArtworkId(event.getId());
        ArtworkRegistryOptional.ifPresent(artworkRegistryRepository::delete);
    }
}
