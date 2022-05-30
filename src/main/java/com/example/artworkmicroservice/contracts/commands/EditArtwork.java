package com.example.artworkmicroservice.contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class EditArtwork {
    @TargetAggregateIdentifier
    private String id;
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
    public String getId() {
        return id;
    }
    public EditArtwork(String id, String artistId, String description, String title, Double price, String link, String image){
        this.id = id;
        this.artistId = artistId;
        this.description = description;
        this.title = title;
        this.price = price;
        this.link = link;
        this.image = image;
    }
}
