package com.example.artworkmicroservice.command.domain.values;

import com.example.artworkmicroservice.common.application.Notification;
import com.example.artworkmicroservice.common.application.Result;
import lombok.Value;
import javax.persistence.Embeddable;

@Embeddable
@Value
public class Link {

    private String value;

    private Link(String link) { value = link; }

    protected Link() {
        this.value = "";
    }

    public static Result<Link, Notification> create(String link) {
        Notification notification = new Notification();
        if(!link.contains("http")){
            notification.addError("link is invalid, ", null);
            return Result.failure(notification);
        }
        if(notification.hasErrors()) {
            return Result.failure(notification);
        }

        return Result.success(new Link(link));
    }

}