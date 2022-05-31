package com.example.artworkmicroservice.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class ArtworkView {

    @Id
    @Column(length = 36) @Getter @Setter
    private String id;

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

    private Instant createdAt;

    @Column(nullable = true) @Getter @Setter
    private Instant updatedAt;

    public ArtworkView() {}

    public ArtworkView(String id, String title, String description, String link, String image, Double price, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
        this.image = image;
        this.price = price;
        this.createdAt = createdAt;
    }
}
