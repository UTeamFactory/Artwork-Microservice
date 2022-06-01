package com.example.artworkmicroservice.query.projections;

import com.example.artworkmicroservice.command.domain.entities.Artwork;
import org.hibernate.query.spi.StreamDecorator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtworkHistoryViewRepository extends JpaRepository<ArtworkHistoryView,String> {

    @Query(value = "SELECT *" +
            "       FROM artwork_history_view" +
            "       WHERE artwork_history_id" +
            "           =(SELECT MAX (artwork_history_id)" +
            "               FROM artwork_history_id WHERE artwork_id = :artwork_id)",nativeQuery = true)
    Optional<ArtworkHistoryView> getLastById(String id);

    @Query(value = "SELECT * FROM artwork_history_view WHERE artwork_id = :artworkId ORDER BY created_at",nativeQuery = true)
    List<ArtworkHistoryView> getArtworkHistoryById(String id);
}
