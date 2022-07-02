package com.perustars.artworkmicroservice.command.application.services;

import com.perustars.artworkmicroservice.command.application.dtos.request.CreateArtworkRequest;
import com.perustars.artworkmicroservice.command.application.dtos.request.DeleteArtworkRequest;
import com.perustars.artworkmicroservice.command.application.dtos.request.EditArtworkRequest;
import com.perustars.artworkmicroservice.command.application.dtos.response.CreateArtworkResponse;
import com.perustars.artworkmicroservice.command.application.dtos.response.DeleteArtworkResponse;
import com.perustars.artworkmicroservice.command.application.dtos.response.EditArtworkResponse;
import com.perustars.artworkmicroservice.command.application.validators.CreateArtworkValidator;
import com.perustars.artworkmicroservice.command.application.validators.DeleteArtworkValidator;
import com.perustars.artworkmicroservice.command.application.validators.EditArtworkValidator;
import com.perustars.artworkmicroservice.command.infrastructure.ArtworkRegistryRepository;
import com.perustars.common.application.Notification;
import com.perustars.common.application.Result;
import com.perustars.common.application.ResultType;
import com.perustars.artworkcontracts.commands.DeleteArtwork;
import com.perustars.artworkcontracts.commands.EditArtwork;
import com.perustars.artworkcontracts.commands.RegisterArtwork;
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
                createArtwork.getArtworkId(),
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
                editArtworkRequest.getArtworkId().trim(),
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
                editArtwork.getArtworkId(),
                editArtwork.getArtistId(),
                editArtwork.getDescription(),
                editArtwork.getTitle(),
                editArtwork.getPrice(),
                editArtwork.getLink(),
                editArtwork.getImage()
        );
        return Result.success(editArtworkResponse);
    }

    public Result<DeleteArtworkResponse, Notification> delete(DeleteArtworkRequest deleteArtworkRequest) throws Exception {
        Notification notification = this.deleteArtworkValidator.validate(deleteArtworkRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        DeleteArtwork deleteArtwork = new DeleteArtwork(
                deleteArtworkRequest.getId().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(deleteArtwork);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) ->  (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        DeleteArtworkResponse deleteArtworkResponse = new DeleteArtworkResponse(
                deleteArtwork.getArtworkId()
        );
        return Result.success(deleteArtworkResponse);
    }
}
