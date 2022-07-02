package com.perustars.artworkmicroservice.query.projections;

import com.perustars.artworkcontracts.events.ArtworkDeleted;
import com.perustars.artworkcontracts.events.ArtworkEdited;
import com.perustars.artworkcontracts.events.ArtworkRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

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
                event.getArtworkId(),
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
        Optional<ArtworkView> artworkViewOptional = artworkViewRepository.findById(event.getArtworkId().toString());
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
        Optional<ArtworkView> artworkViewOptional = artworkViewRepository.findById(event.getArtworkId().toString());
        if (artworkViewOptional.isPresent()){
            ArtworkView artworkView = artworkViewOptional.get();
            artworkView.setArtworkId(event.getArtworkId());
            artworkView.setUpdatedAt(event.getOccurredOn());
            artworkViewRepository.save(artworkView);
        }
    }
}
