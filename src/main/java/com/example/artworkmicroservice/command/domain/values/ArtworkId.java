package com.example.artworkmicroservice.command.domain.values;

import lombok.Value;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Value(staticConstructor = "of")
public class ArtworkId implements Serializable {
    private UUID value;

    private ArtworkId(UUID value){
        this.value = value;
    }

    protected ArtworkId(){
        this.value = UUID.randomUUID();
    }

}