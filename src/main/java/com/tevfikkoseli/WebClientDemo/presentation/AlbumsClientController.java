package com.tevfikkoseli.WebClientDemo.presentation;


import com.tevfikkoseli.WebClientDemo.service.AlbumsClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping("/albums-client/albums")
public class AlbumsClientController {


    private final AlbumsClientService albumsClientService;

    public AlbumsClientController(AlbumsClientService albumsClientService) {
        this.albumsClientService = albumsClientService;
    }

    @PostMapping
    public Mono<ResponseEntity<AlbumRest>> createAlbum(@Valid @RequestBody Mono<AlbumRest> album,
                                                       @RequestHeader(name = "Authorization") String jwt) {
        return albumsClientService.createAlbum(album, jwt)
                .map(albumRest ->  ResponseEntity
                        .status(HttpStatus.CREATED)
                        .location(URI.create("/albums-client/" + albumRest.getId()))
                        .body(albumRest));
    }

    @PutMapping(path = "/{id}")
    public Mono<ResponseEntity<AlbumRest>> updateAlbum(@PathVariable UUID id,
                                                       @Valid @RequestBody Mono<AlbumRest> album,
                                                       @RequestHeader(name = "Authorization") String jwt) {
        return albumsClientService.updateAlbum(id, album, jwt)
                .map(albumRest -> ResponseEntity
                        .status(HttpStatus.OK)
                        .location(URI.create("/albums-client/" + albumRest.getId()))
                        .body(albumRest)).defaultIfEmpty(ResponseEntity
                        .notFound()
                        .build());

    }

    @DeleteMapping(path = "/{albumId}")
    public Mono<ResponseEntity<Void>> deleteAlbum(@PathVariable UUID albumId,
                                                  @RequestHeader(name = "Authorization") String jwt) {
        return albumsClientService.deleteAlbum(albumId, jwt)
                .then(Mono.just(ResponseEntity.noContent().<Void>build())
                .defaultIfEmpty(ResponseEntity
                        .notFound()
                        .build()));
    }



}
