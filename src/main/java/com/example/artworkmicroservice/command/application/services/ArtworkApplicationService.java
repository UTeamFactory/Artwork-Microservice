package com.example.artworkmicroservice.command.application.services;

import com.example.artworkmicroservice.command.application.dtos.request.CreateArtworkRequest;
import com.example.artworkmicroservice.command.application.dtos.request.EditArtworkRequest;
import com.example.artworkmicroservice.command.application.dtos.response.CreateArtworkResponse;
import com.example.artworkmicroservice.command.application.dtos.response.EditArtworkResponse;
import com.example.artworkmicroservice.command.application.validators.CreateArtworkValidator;
import com.example.artworkmicroservice.command.application.validators.DeleteArtworkValidator;
import com.example.artworkmicroservice.command.application.validators.EditArtworkValidator;
import com.example.artworkmicroservice.command.infrastructure.ArtworkRegistryRepository;
import com.example.artworkmicroservice.common.application.Notification;
import com.example.artworkmicroservice.common.application.Result;
import com.example.artworkmicroservice.common.application.ResultType;
import com.example.artworkmicroservice.contracts.commands.EditArtwork;
import com.example.artworkmicroservice.contracts.commands.RegisterArtwork;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class ArtworkApplicationService {
    private final CreateArtworkValidator createArtworkValidator;
    private final EditArtworkValidator editArtworkValidator;
    private final DeleteArtworkValidator deleteArtworkValidator;
    protected final CommandGateway commandGateway;
    private final ArtworkRegistryRepository artworkRegistryRepository;

    public ArtworkApplicationService(CreateArtworkValidator createArtworkValidator, EditArtworkValidator editArtworkValidator, DeleteArtworkValidator deleteArtworkValidator, CommandGateway commandGateway, ArtworkRegistryRepository artworkRegistryRepository) {
        this.createArtworkValidator = createArtworkValidator;
        this.editArtworkValidator = editArtworkValidator;
        this.deleteArtworkValidator = deleteArtworkValidator;
        this.commandGateway = commandGateway;
        this.artworkRegistryRepository = artworkRegistryRepository;
    }

    public Result<CreateArtworkResponse, Notification> create(CreateArtworkRequest createArtworkRequest) throws Exception {
        Notification notification = this.createArtworkValidator.validate(createArtworkRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String artworkId = UUID.randomUUID().toString();
        RegisterArtwork createArtwork = new RegisterArtwork(
                artworkId,
                createArtworkRequest.getArtistId().trim(),
                createArtworkRequest.getDescription().trim(),
                createArtworkRequest.getTitle().trim(),
                createArtworkRequest.getPrice(),
                createArtworkRequest.getLink().trim(),
                createArtworkRequest.getImage().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(createArtwork);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        CreateArtworkResponse createArtworkResponseDto = new CreateArtworkResponse(
                createArtwork.getId(),
                createArtwork.getArtistId(),
                createArtwork.getDescription(),
                createArtwork.getTitle(),
                createArtwork.getPrice(),
                createArtwork.getLink(),
                createArtwork.getImage()
        );
        return Result.success(createArtworkResponseDto);
    }

    public Result<EditArtworkResponse, Notification> edit(EditArtworkRequest editArtworkRequest) throws Exception {
        Notification notification = this.editArtworkValidator.validate(editArtworkRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        EditArtwork editArtwork = new EditArtwork(
                editArtworkRequest.getId().trim(),
                editArtworkRequest.getArtistId().trim(),
                editArtworkRequest.getDescription().trim(),
                editArtworkRequest.getTitle().trim(),
                editArtworkRequest.getPrice(),
                editArtworkRequest.getLink().trim(),
                editArtworkRequest.getImage().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(editArtwork);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        EditArtworkResponse editArtworkResponse = new EditArtworkResponse(
                editArtwork.getId(),
                editArtwork.getArtistId(),
                editArtwork.getDescription(),
                editArtwork.getTitle(),
                editArtwork.getPrice(),
                editArtwork.getLink(),
                editArtwork.getImage()
        );
        return Result.success(editArtworkResponse);
    }
}
