package com.perustars.artworkmicroservice.command.application.dtos.response;

import lombok.Value;

@Value
public class CreateArtworkResponse {
    private String id;
    private String artistId;
    private String description;
    private String title;
    private Double price;
    private String link;
    private String image;
}
