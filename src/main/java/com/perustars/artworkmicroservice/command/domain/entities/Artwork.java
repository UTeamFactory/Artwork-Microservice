package com.perustars.artworkmicroservice.command.domain.entities;

import com.perustars.artworkmicroservice.command.domain.values.*;
import com.perustars.artwork.contracts.commands.DeleteArtwork;
import com.perustars.artwork.contracts.commands.EditArtwork;
import com.perustars.artwork.contracts.commands.RegisterArtwork;
import com.perustars.artwork.contracts.events.ArtworkDeleted;
import com.perustars.artwork.contracts.events.ArtworkEdited;
import com.perustars.artwork.contracts.events.ArtworkRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.*;
import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Artwork {
    @AggregateIdentifier
    private String artworkId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "description", length = 200, nullable = false))
    })
    private Description description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "title", length = 100, nullable = false))
    })
    private Title title;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "price", nullable = false))
    })
    private Price price;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "link", length = 200, nullable = false))
    })
    private Link link;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "image", nullable = false))
    })
    private Image image;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "artist_id", nullable = false))
    })
    private ArtistId artistId;

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
        artistId = new ArtistId(event.getArtistId());
        title = new Title(event.getTitle());
        description = new Description(event.getDescription());
        price = new Price(event.getPrice());
        image = new Image(event.getImage());
        link = new Link(event.getLink());
    }

    @EventSourcingHandler
    protected void on (ArtworkEdited event){
        artistId = new ArtistId(event.getArtistId());
        title = new Title(event.getTitle());
        description = new Description(event.getDescription());
        price = new Price(event.getPrice());
        image = new Image(event.getImage());
        link = new Link(event.getLink());
    }

    @EventSourcingHandler
    protected void on(ArtworkDeleted event){
        artworkId = event.getId();
    }
}
