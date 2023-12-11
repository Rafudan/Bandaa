package br.com.rafudan.bandaa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rafudan.bandaa.modelo.Album;

public interface AlbumRepositorio extends JpaRepository<Album, Long> {
}
