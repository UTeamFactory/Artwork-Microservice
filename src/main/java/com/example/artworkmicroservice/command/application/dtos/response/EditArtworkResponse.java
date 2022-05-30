package com.example.artworkmicroservice.command.application.dtos.response;

import lombok.Value;

@Value
public class EditArtworkResponse {
    private String id;
    private String artistId;
    private String description;
    private String title;
    private Double price;
    private String link;
    private String image;
}
