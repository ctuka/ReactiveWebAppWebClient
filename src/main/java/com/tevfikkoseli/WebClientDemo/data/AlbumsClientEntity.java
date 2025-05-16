package com.tevfikkoseli.WebClientDemo.data;

import com.tevfikkoseli.WebClientDemo.presentation.AlbumRest;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Table("albums_client")
public class AlbumsClientEntity {

    @Id
    UUID albumsClientId;

    @Column("album_rest")

    List<AlbumRest> albumRests;




}
