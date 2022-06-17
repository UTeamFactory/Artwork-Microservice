package com.perustars.artworkmicroservice.command.domain.values;

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

    public ArtworkId(){
        this.value = UUID.randomUUID();
    }

}