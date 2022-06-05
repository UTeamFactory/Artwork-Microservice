package com.example.artworkmicroservice.contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class EditArtwork {
    @TargetAggregateIdentifier
    private String artworkId;
    private String artistId;
    private String description;
    private String title;
    private Double price;
    private String link;
    private String image;
    public String getArtistId() {return artistId; }
    public String getDescription() {return description; }
    public String getTitle() {return title; }
    public Double getPrice() {return price; }
    public String getLink() {return link; }
    public String getImage() {return image; }
    public String getArtworkId() {
        return artworkId;
    }
    public EditArtwork(String artworkId, String artistId, String description, String title, Double price, String link, String image){
        this.artworkId = artworkId;
        this.artistId = artistId;
        this.description = description;
        this.title = title;
        this.price = price;
        this.link = link;
        this.image = image;
    }
}
