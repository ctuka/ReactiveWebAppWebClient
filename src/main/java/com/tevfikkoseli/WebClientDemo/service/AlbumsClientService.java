package com.tevfikkoseli.WebClientDemo.service;

import com.tevfikkoseli.WebClientDemo.presentation.AlbumRest;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AlbumsClientService {

    Mono<AlbumRest> createAlbum(Mono<AlbumRest> albumRestMono, String jwt);
    Mono<AlbumRest> updateAlbum(UUID id, Mono<AlbumRest> albumRestMono, String jwt);
    Mono<AlbumRest> getAlbum(String albumId, String jwt);
    Mono<Void> deleteAlbum(UUID albumId, String jwt);
    Flux<AlbumRest> getAlbums(String jwt);
    Flux<AlbumRest> getAlbumsByUserId(String userId, String jwt);


}
