package com.example.artworkmicroservice.contracts.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class DeleteArtwork {
    @TargetAggregateIdentifier
    private String id;
}
