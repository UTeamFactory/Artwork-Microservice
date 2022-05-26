package com.example.artworkmicroservice.command.entities;

import com.example.artworkmicroservice.command.domain.values.*;
import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.*;

import javax.persistence.*;

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

    public Artwork(ArtworkId id, ArtistId artistId, Title title, Description description, Image image, Link link, Price price, AuditTrail auditTrail) {
        setId(id);
        setArtistId(artistId);
        setAuditTrail(auditTrail);
        setTitle(title);
        setDescription(description);
        setImage(image);
        setPrice(price);
        setLink(link);
    }

    protected Artwork(){

    }
}
