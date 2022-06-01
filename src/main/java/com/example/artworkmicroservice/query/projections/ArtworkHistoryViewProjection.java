package com.example.artworkmicroservice.query.projections;

import com.example.artworkmicroservice.contracts.events.ArtworkDeleted;
import com.example.artworkmicroservice.contracts.events.ArtworkEdited;
import com.example.artworkmicroservice.contracts.events.ArtworkRegistered;
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
                event.getId(),
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
        Optional<ArtworkHistoryView> artworkHistoryViewOptional = artworkHistoryViewRepository.getLastById(event.getId().toString());
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
        Optional<ArtworkHistoryView> artworkHistoryViewOptional = artworkHistoryViewRepository.getLastById(event.getId().toString());
        if (artworkHistoryViewOptional.isPresent()){
            ArtworkHistoryView artworkHistoryView = artworkHistoryViewOptional.get();
            artworkHistoryView = new ArtworkHistoryView(artworkHistoryView);

            artworkHistoryView.setId(event.getId());
            artworkHistoryView.setCreatedAt(event.getOccurredOn());

            artworkHistoryViewRepository.save(artworkHistoryView);
        }
    }

}
