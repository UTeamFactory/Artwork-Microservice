package com.example.artworkmicroservice.contracts.events;

import lombok.Value;

@Value
public class ArtworkRegistered {

    private String id;
    private String artistId;
    private String title;
    private String description;
    private String image;
    private String link;
    private String price;
    private String auditTrail;

}
