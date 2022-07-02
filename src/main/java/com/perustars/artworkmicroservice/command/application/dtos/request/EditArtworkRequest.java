package com.perustars.artworkmicroservice.command.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

public class EditArtworkRequest {
    private @Getter @Setter String artworkId;
    private @Getter String artistId;
    private @Getter String description;
    private @Getter String title;
    private @Getter Double price;
    private @Getter String link;
    private @Getter String image;
}
