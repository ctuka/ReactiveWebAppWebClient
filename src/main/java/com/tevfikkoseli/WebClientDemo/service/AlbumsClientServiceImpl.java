package com.tevfikkoseli.WebClientDemo.service;

import com.tevfikkoseli.WebClientDemo.data.AlbumsClientRepository;
import com.tevfikkoseli.WebClientDemo.presentation.AlbumRest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.naming.OperationNotSupportedException;
import java.util.UUID;

@Service
public class AlbumsClientServiceImpl implements AlbumsClientService{

    private final WebClient webClient;

    private final AlbumsClientRepository albumsClientRepository;

    public AlbumsClientServiceImpl(WebClient webClient, AlbumsClientRepository albumsClientRepository) {
        this.webClient = webClient;
        this.albumsClientRepository = albumsClientRepository;
    }

    @Override
    public Mono<AlbumRest> createAlbum(Mono<AlbumRest> album, String jwt) {
        return webClient.post()
                .uri("/albums")
                .body(album, AlbumRest.class)
                .header("Authorization", jwt)
                .retrieve()
                .bodyToMono(AlbumRest.class);
    }

    @Override
    public Mono<AlbumRest> updateAlbum(UUID id, Mono<AlbumRest> albumRestMono, String jwt) {
        return albumRestMono.flatMap(albumRest -> {
            albumRest.setId(id);
                return webClient.put()
                        .uri("/albums/{id}", id)
                        .body(Mono.just(albumRest), AlbumRest.class)
                        .header("Authorization", jwt)
                        .retrieve().bodyToMono(AlbumRest.class);
        });


    }

    @Override
    public Mono<AlbumRest> getAlbum(String albumId, String jwt) {
        return null;
    }

    @Override
    public Mono<Void> deleteAlbum(UUID albumId, String jwt) {
        return webClient.delete()
                .uri("/albums/{id}", albumId)
                .header("Authorization", jwt)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Flux<AlbumRest> getAlbums(String jwt) {
        return null;
    }

    @Override
    public Flux<AlbumRest> getAlbumsByUserId(String userId, String jwt) {
        return null;
    }
}
