package com.example.artworkmicroservice.command.domain.entities;

import com.example.artworkmicroservice.command.domain.values.*;
import com.example.artworkmicroservice.contracts.commands.DeleteArtwork;
import com.example.artworkmicroservice.contracts.commands.EditArtwork;
import com.example.artworkmicroservice.contracts.commands.RegisterArtwork;
import com.example.artworkmicroservice.contracts.events.ArtworkDeleted;
import com.example.artworkmicroservice.contracts.events.ArtworkEdited;
import com.example.artworkmicroservice.contracts.events.ArtworkRegistered;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.*;

import javax.persistence.*;
import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Entity(name = "Artwork")
@Table(name = "artworks")
@Data
@Aggregate
public class Artwork {

    @AggregateIdentifier
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "BINARY(16)"))
    })
    private ArtworkId id;

    @AggregateIdentifier
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "artist_id", length = 20, nullable = false))
    })
    private ArtistId artistId;

    @AggregateIdentifier
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "title", length = 75, nullable = false))
    })
    private Title title;

    @AggregateIdentifier
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "description", length = 200, nullable = false))
    })
    private Description description;

    @AggregateIdentifier
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "image", nullable = false))
    })
    private Image image ;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "link", nullable = true))
    })
    private Link link;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "price", nullable = false))
    })
    private Price price;

    @Embedded
    private AuditTrail auditTrail;

    /*public Artwork(ArtworkId id, ArtistId artistId, Title title, Description description, Image image, Link link, Price price, AuditTrail auditTrail) {
        setId(id);
        setArtistId(artistId);
        setAuditTrail(auditTrail);
        setTitle(title);
        setDescription(description);
        setImage(image);
        setPrice(price);
        setLink(link);
    }*/

    public Artwork(){}

     @CommandHandler
    public Artwork(RegisterArtwork command){
        Instant now = Instant.now();
        apply(
                new ArtworkRegistered(
                        command.getId(),
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
                        command.getId(),
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
        id = new ArtworkId();
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
        id = new ArtworkId();
    }

}
