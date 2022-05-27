package com.example.artworkmicroservice.command.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ArtworkRegistryRepository extends JpaRepository<ArtworkRegistry, String> {
    Optional<ArtworkRegistry> getByArtworkId(String artowrkRegistryId);
}
