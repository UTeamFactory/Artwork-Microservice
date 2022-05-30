package com.example.artworkmicroservice.contracts.events;

import lombok.Value;
import java.time.Instant;

@Value
public class ArtworkRegistered {
    private String id;
    private String artistId;
    private String description;
    private String title;
    private Double price;
    private String link;
    private String image;
    private Instant occurredOn;
}
