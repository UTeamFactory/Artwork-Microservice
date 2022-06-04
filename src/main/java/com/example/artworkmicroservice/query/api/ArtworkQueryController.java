package com.example.artworkmicroservice.query.api;

import com.example.artworkmicroservice.command.domain.entities.Artwork;
import com.example.artworkmicroservice.query.projections.ArtworkHistoryView;
import com.example.artworkmicroservice.query.projections.ArtworkHistoryViewRepository;
import com.example.artworkmicroservice.query.projections.ArtworkView;
import com.example.artworkmicroservice.query.projections.ArtworkViewRepository;
import com.google.api.Http;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artworks")
@Tag(name="Artworks")
public class ArtworkQueryController {
    private final ArtworkViewRepository artworkViewRepository;
    private final ArtworkHistoryViewRepository artworkHistoryViewRepository;

    public ArtworkQueryController(ArtworkViewRepository artworkViewRepository, ArtworkHistoryViewRepository artworkHistoryViewRepository){
        this.artworkViewRepository = artworkViewRepository;
        this.artworkHistoryViewRepository = artworkHistoryViewRepository;
    }

    @GetMapping("")
    @Operation(summary = "Get all Artworks")
    public ResponseEntity<List<ArtworkView>> getAllArtworks(){
        try {
            return new ResponseEntity<List<ArtworkView>>(artworkViewRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/id/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Artwork by id")
    public ResponseEntity<ArtworkView> getById(@PathVariable("id") String id){
        try {
            Optional<ArtworkView> artworkViewOptional = artworkViewRepository.findById(id);
            if (artworkViewOptional.isPresent()){
                return new ResponseEntity<ArtworkView>(artworkViewOptional.get(),HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ArtworkHistoryView>> getHistoryById(@PathVariable("id") String id){
        try {
            List<ArtworkHistoryView> artworks = artworkHistoryViewRepository.getArtworkHistoryById(id);
            return new ResponseEntity<List<ArtworkHistoryView>>(artworks,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
