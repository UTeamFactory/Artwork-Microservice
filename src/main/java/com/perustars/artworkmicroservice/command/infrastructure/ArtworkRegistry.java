package com.perustars.artworkmicroservice.command.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArtworkRegistry {
    @Id
    @Column
    public String artworkId;

    private String artistId;

    private String description;

    private String title;

    private Double price;

    private String link;

    private String image;

    public ArtworkRegistry(){}

    public ArtworkRegistry(String artworkId, String artistId, String description, String title, Double price, String link, String image){
        this.artworkId = artworkId;
        this.artistId = artistId;
        this.description = description;
        this.title = title;
        this.price = price;
        this.link = link;
        this.image = image;
    }

    public String getArtworkId() {return artworkId;}

    public ArtworkRegistry setArtworkId(String artworkId) {
        this.artworkId = artworkId;
        return this;
    }

    public String getArtistId() {return artistId;}

    public ArtworkRegistry setArtistId(String artistId) {
        this.artistId = artistId;
        return this;
    }

    public String getDescription() {return description;}

    public ArtworkRegistry setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTitle() {return title;}

    public ArtworkRegistry setTitle(String title) {
        this.title = title;
        return this;
    }

    public Double getPrice() {return price;}

    public ArtworkRegistry setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getLink() {return link;}

    public ArtworkRegistry setLink(String link) {
        this.link = link;
        return this;
    }

    public String getImage() {return image;}

    public ArtworkRegistry setImage(String image) {
        this.image = image;
        return this;
    }
}
