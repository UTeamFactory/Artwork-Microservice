package com.perustars.artworkmicroservice.config;

import com.perustars.artworkmicroservice.command.domain.entities.Artwork;
import com.thoughtworks.xstream.XStream;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.axonframework.modelling.command.Repository;

@Configuration
public class AxonConfig {
    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();

        xStream.allowTypesByWildcard(new String[] {
                "com.perustars.artworkcontracts.**"
        });
        return xStream;
    }

    @Bean
    public Repository<Artwork> eventSourcingRepository(EventStore eventStore){
        return EventSourcingRepository.builder(Artwork.class)
                .eventStore(eventStore)
                .build();
    }
}
