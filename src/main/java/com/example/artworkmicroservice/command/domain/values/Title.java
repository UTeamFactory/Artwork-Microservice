package com.example.artworkmicroservice.command.domain.values;

import com.example.artworkmicroservice.common.application.Notification;
import com.example.artworkmicroservice.common.application.Result;
import lombok.Value;
import javax.persistence.Embeddable;

@Embeddable
@Value
public class Title {

    private String value;
    private final static int MAX_LENGTH = 75;

    public Title(String value) {
        this.value = value;
    }

    protected Title(){
        value = "";
    }

    public static Result<Title, Notification> create(String title){
        Notification notification = new Notification();
        title = title == null ? "" : title.trim();

        if(title.isEmpty()){
            notification.addError("title is required", null);
            return Result.failure(notification);
        }
        if(title.length() > MAX_LENGTH){
            notification.addError("The maximum length of a title " + MAX_LENGTH + " characters including spaces", null);
        }

        if(notification.hasErrors()) {
            return Result.failure(notification);
        }

        return Result.success(new Title(title));
    }
}