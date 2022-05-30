package com.example.artworkmicroservice.contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class RegisterArtwork {

    @TargetAggregateIdentifier
    private String id;
    private String artistId;
    private String title;
    private String description;
    private String image;
    private String link;
    private String price;
    private String auditTrail;

    public String getId() {
        return id;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getPrice() {
        return price;
    }

    public String getAuditTrail() {
        return auditTrail;
    }

    public RegisterArtwork(String id, String artistId, String title, String description, String image, String link, String price, String auditTrail) {
        this.id = id;
        this.artistId = artistId;
        this.title = title;
        this.description = description;
        this.image = image;
        this.link = link;
        this.price = price;
        this.auditTrail = auditTrail;
    }
}
