package com.example.artworkmicroservice.command.domain.entities;

import com.example.artworkmicroservice.command.domain.enums.ArtType;
import com.example.artworkmicroservice.contracts.commands.DeleteArtwork;
import com.example.artworkmicroservice.contracts.commands.EditArtwork;
import com.example.artworkmicroservice.contracts.commands.RegisterArtwork;
import com.example.artworkmicroservice.contracts.events.ArtworkDeleted;
import com.example.artworkmicroservice.contracts.events.ArtworkEdited;
import com.example.artworkmicroservice.contracts.events.ArtworkRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Artwork {
    @AggregateIdentifier
    private String artworkId;
    private String description;
    private String title;
    private Double price;
    private String link;
    private String image;
    private String artistId;

    public Artwork(){}

     @CommandHandler
    public Artwork(RegisterArtwork command){
        Instant now = Instant.now();
        apply(
                new ArtworkRegistered(
                        command.getArtworkId(),
                        command.getArtistId(),
                        command.getTitle(),
                        command.getDescription(),
                        command.getPrice(),
                        command.getImage(),
                        command.getLink(),
                        now
                )
        );
    }

    @CommandHandler
    public void handle(EditArtwork command){
        Instant now = Instant.now();
        apply(
                new ArtworkEdited(
                        command.getArtworkId(),
                        command.getArtistId(),
                        command.getTitle(),
                        command.getDescription(),
                        command.getPrice(),
                        command.getImage(),
                        command.getLink(),
                        now
                )
        );
    }

    @CommandHandler
    public void handle(DeleteArtwork command){
        Instant now = Instant.now();
        apply(
                new ArtworkDeleted(
                        command.getId(),
                        now
                )
        );
    }

    @EventSourcingHandler
    protected void on (ArtworkRegistered event){
        artworkId = event.getId();
        artistId = event.getArtistId();
        title = event.getTitle();
        description = event.getDescription();
        price = event.getPrice();
        image = event.getImage();
        link = event.getLink();
    }

    @EventSourcingHandler
    protected void on (ArtworkEdited event){
        artistId = event.getArtistId();
        title = event.getTitle();
        description = event.getDescription();
        price = event.getPrice();
        image = event.getImage();
        link = event.getLink();
    }

    @EventSourcingHandler
    protected void on(ArtworkDeleted event){
        artworkId = event.getId();
    }
}
