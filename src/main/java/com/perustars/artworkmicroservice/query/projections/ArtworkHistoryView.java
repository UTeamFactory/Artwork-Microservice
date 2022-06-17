package com.perustars.artworkmicroservice.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class ArtworkHistoryView {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long artworkHistoryId;

    @Column(length = 36) @Getter @Setter
    private String artworkId;

    @Column(length = 75) @Getter @Setter
    private String title;

    @Column(length = 200) @Getter @Setter
    private String description;

    @Column(nullable = true) @Getter @Setter
    private String link;

    @Column() @Getter @Setter
    private String image;

    @Column() @Getter @Setter
    private Double price;

    @Getter @Setter
    private Instant createdAt;

    public ArtworkHistoryView() {}

    public ArtworkHistoryView(String artworkId, String title, String description, String link, String image, Double price, Instant createdAt) {
        this.artworkId = artworkId;
        this.title = title;
        this.description = description;
        this.link = link;
        this.image = image;
        this.price = price;
        this.createdAt = createdAt;
    }

    public ArtworkHistoryView(ArtworkHistoryView artworkHistoryView) {
        this.artworkId = artworkHistoryView.getArtworkId();
        this.title = artworkHistoryView.getTitle();
        this.description = artworkHistoryView.getDescription();
        this.link = artworkHistoryView.getLink();
        this.image = artworkHistoryView.getImage();
        this.price = artworkHistoryView.getPrice();
        this.createdAt = artworkHistoryView.getCreatedAt();
    }
}