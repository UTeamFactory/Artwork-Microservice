package com.perustars.artworkmicroservice.query.projections;

import com.perustars.artworkcontracts.events.ArtworkDeleted;
import com.perustars.artworkcontracts.events.ArtworkEdited;
import com.perustars.artworkcontracts.events.ArtworkRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ArtworkHistoryViewProjection {
    private final ArtworkHistoryViewRepository artworkHistoryViewRepository;

    public ArtworkHistoryViewProjection(ArtworkHistoryViewRepository artworkHistoryViewRepository){
        this.artworkHistoryViewRepository = artworkHistoryViewRepository;
    }

    @EventHandler
    public void on(ArtworkRegistered event){
        ArtworkHistoryView artworkHistoryView = new ArtworkHistoryView(
                event.getArtworkId(),
                event.getTitle(),
                event.getDescription(),
                event.getLink(),
                event.getImage(),
                event.getPrice(),
                event.getOccurredOn()
        );
        artworkHistoryViewRepository.save(artworkHistoryView);
    }

    @EventHandler
    public void on(ArtworkEdited event){
        Optional<ArtworkHistoryView> artworkHistoryViewOptional = artworkHistoryViewRepository.getLastByArtworkId(event.getArtworkId().toString());
        if (artworkHistoryViewOptional.isPresent()){
            ArtworkHistoryView artworkHistoryView = artworkHistoryViewOptional.get();
            artworkHistoryView = new ArtworkHistoryView(artworkHistoryView);

            artworkHistoryView.setTitle(event.getTitle());
            artworkHistoryView.setDescription(event.getDescription());
            artworkHistoryView.setLink(event.getLink());
            artworkHistoryView.setImage(event.getImage());
            artworkHistoryView.setPrice(event.getPrice());
            artworkHistoryView.setCreatedAt(event.getOccurredOn());

            artworkHistoryViewRepository.save(artworkHistoryView);
        }
    }

    @EventHandler
    public void on(ArtworkDeleted event){
        Optional<ArtworkHistoryView> artworkHistoryViewOptional = artworkHistoryViewRepository.getLastByArtworkId(event.getArtworkId().toString());
        if (artworkHistoryViewOptional.isPresent()){
            ArtworkHistoryView artworkHistoryView = artworkHistoryViewOptional.get();
            artworkHistoryView = new ArtworkHistoryView(artworkHistoryView);

            artworkHistoryView.setArtworkId(event.getArtworkId());
            artworkHistoryView.setCreatedAt(event.getOccurredOn());

            artworkHistoryViewRepository.save(artworkHistoryView);
        }
    }

}
