package com.example.artworkmicroservice.command.api;

import com.example.artworkmicroservice.command.application.dtos.request.CreateArtworkRequest;
import com.example.artworkmicroservice.command.application.dtos.request.DeleteArtworkRequest;
import com.example.artworkmicroservice.command.application.dtos.request.EditArtworkRequest;
import com.example.artworkmicroservice.command.application.dtos.response.CreateArtworkResponse;
import com.example.artworkmicroservice.command.application.dtos.response.DeleteArtworkResponse;
import com.example.artworkmicroservice.command.application.dtos.response.EditArtworkResponse;
import com.example.artworkmicroservice.command.application.services.ArtworkApplicationService;
import com.example.artworkmicroservice.command.infrastructure.ArtworkRegistryRepository;
import com.example.artworkmicroservice.common.api.ApiController;
import com.example.artworkmicroservice.common.application.Notification;
import com.example.artworkmicroservice.common.application.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artworks")
@Tag(name = "Artworks")
public class ArtworkCommandController {
    private final ArtworkApplicationService artworkApplicationService;
    private final CommandGateway commandGateway;
    private final ArtworkRegistryRepository artworkRegistryRepository;

    public ArtworkCommandController(ArtworkApplicationService artworkApplicationService, CommandGateway commandGateway, ArtworkRegistryRepository artworkRegistryRepository) {
        this.artworkApplicationService = artworkApplicationService;
        this.commandGateway = commandGateway;
        this.artworkRegistryRepository = artworkRegistryRepository;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody CreateArtworkRequest createArtworkRequest) {
        try {
            Result<CreateArtworkResponse, Notification> result = artworkApplicationService.create(createArtworkRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/{artworkId}")
    public ResponseEntity<Object> edit(@PathVariable("artworkId") String artworkId, @RequestBody EditArtworkRequest editArtworkRequest) {
        try {
            editArtworkRequest.setId(artworkId);
            Result<EditArtworkResponse, Notification> result = artworkApplicationService.edit(editArtworkRequest);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @DeleteMapping("/{artworkId}")
    public ResponseEntity<Object> delete(@PathVariable("artworkId") String artworkId, @RequestBody DeleteArtworkRequest deleteArtworkRequest) {
        try {
            deleteArtworkRequest.setId(artworkId);
            Result<DeleteArtworkResponse, Notification> result = artworkApplicationService.delete(deleteArtworkRequest);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }
}
