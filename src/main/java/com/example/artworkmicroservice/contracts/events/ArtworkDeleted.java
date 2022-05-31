package com.example.artworkmicroservice.contracts.events;

import lombok.Value;

import java.time.Instant;

@Value
public class ArtworkDeleted {
    private String id;
    private Instant occurredOn;
}
