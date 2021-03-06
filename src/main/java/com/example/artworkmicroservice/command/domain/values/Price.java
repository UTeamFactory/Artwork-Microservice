package com.example.artworkmicroservice.command.domain.values;

import com.example.artworkmicroservice.common.application.Notification;
import com.example.artworkmicroservice.common.application.Result;
import lombok.Value;
import javax.persistence.Embeddable;

@Embeddable
@Value
public class Price {

    private Double value;

    private Price(Double price) {value = price;}

    protected Price(){
        this.value = 0.0;
    }

    public static Result<Price, Notification> create(Double price) {
        Notification notification = new Notification();

        if(price < 0.0){
            notification.addError("The price never can be lower than zero", null);
            return Result.failure(notification);
        }

        if(notification.hasErrors()) {
            return Result.failure(notification);
        }
        return  Result.success(new Price(price));

    }
}