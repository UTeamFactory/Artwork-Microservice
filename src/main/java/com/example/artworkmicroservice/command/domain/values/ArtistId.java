package com.example.artworkmicroservice.command.domain.values;

import com.example.artworkmicroservice.common.application.Notification;
import com.example.artworkmicroservice.common.application.Result;
import lombok.Value;
import javax.persistence.Embeddable;

@Embeddable
@Value
public class ArtistId {
    String value;

    public ArtistId(String value) {
        this.value = value;
    }

    protected ArtistId(){
        value = "";
    }

    public static Result<ArtistId, Notification> create(String artistId) {
        Notification notification = new Notification();
        artistId = artistId == null ? "" : artistId.trim();

        if(artistId.isEmpty()){
            notification.addError("ArtistId is requerid", null);
            return Result.failure(notification);
        }

        if(notification.hasErrors()) {
            return Result.failure(notification);
        }

        return Result.success(new ArtistId(artistId));

    }
}