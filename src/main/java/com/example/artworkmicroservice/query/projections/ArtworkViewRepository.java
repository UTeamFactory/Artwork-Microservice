package com.example.artworkmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtworkViewRepository extends JpaRepository<ArtworkView,String> {
}
