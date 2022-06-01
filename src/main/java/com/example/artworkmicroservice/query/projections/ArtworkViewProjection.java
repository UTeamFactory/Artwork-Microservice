package com.example.artworkmicroservice.query.projections;

import com.example.artworkmicroservice.contracts.events.ArtworkDeleted;
import com.example.artworkmicroservice.contracts.events.ArtworkEdited;
import com.example.artworkmicroservice.contracts.events.ArtworkRegistered;
import jdk.jfr.Timestamp;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class ArtworkViewProjection {
    private final ArtworkViewRepository artworkViewRepository;

    public ArtworkViewProjection(ArtworkViewRepository artworkViewRepository){
        this.artworkViewRepository = artworkViewRepository;
    }

    @EventHandler
    public void on(ArtworkRegistered event){
        ArtworkView artworkView = new ArtworkView(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getLink(),
                event.getImage(),
                event.getPrice(),
                event.getOccurredOn()
        );
        artworkViewRepository.save(artworkView);
    }

    @EventHandler
    public void on(ArtworkEdited event){
        Optional<ArtworkView> artworkViewOptional = artworkViewRepository.findById(event.getId().toString());
        if (artworkViewOptional.isPresent()){
            ArtworkView artworkView = artworkViewOptional.get();
            artworkView.setTitle(event.getTitle());
            artworkView.setDescription(event.getDescription());
            artworkView.setLink(event.getLink());
            artworkView.setImage(event.getImage());
            artworkView.setPrice(event.getPrice());
            artworkView.setUpdatedAt(event.getOccurredOn());
            artworkViewRepository.save(artworkView);
        }
    }

    @EventHandler
    public void on(ArtworkDeleted event){
        Optional<ArtworkView> artworkViewOptional = artworkViewRepository.findById(event.getId().toString());
        if (artworkViewOptional.isPresent()){
            ArtworkView artworkView = artworkViewOptional.get();
            artworkView.setId(event.getId());
            artworkView.setUpdatedAt(event.getOccurredOn());
            artworkViewRepository.save(artworkView);
        }
    }
}
