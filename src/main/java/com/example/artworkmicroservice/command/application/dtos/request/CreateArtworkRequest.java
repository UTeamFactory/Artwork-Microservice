package com.example.artworkmicroservice.command.application.dtos.request;

import lombok.Value;

@Value
public class CreateArtworkRequest {
    private String artistId;
    private String description;
    private String title;
    private Double price;
    private String link;
    private String image;
}
