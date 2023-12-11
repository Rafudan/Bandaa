package br.com.rafudan.bandaa.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafudan.bandaa.excessao.BandaNotFoundException;
import br.com.rafudan.bandaa.modelo.Album;
import br.com.rafudan.bandaa.repositorio.AlbumRepositorio;
@Service
public class AlbumServico {
	@Autowired
	private AlbumRepositorio albumRepositorio;

	public Album salvar(Album album) {
		return albumRepositorio.save(album);
	}

	public List<Album> buscarAlbuns() {
		return albumRepositorio.findAll();
	}

	@SuppressWarnings("null")
	public List<Album> buscarAlbunsPorBanda(Long idBanda) {
		List<Album> albuns = new ArrayList();
		List<Album> todos = albumRepositorio.findAll();
		for (Album album : todos) {
			if(album.getBanda().getId()== idBanda) {
				albuns.add(album);
			}
		}
		return albuns;
	}

	public Album buscarAlbumPorId(Long id) {
		Optional<Album> album = albumRepositorio.findById(id);
		if (album.isPresent()) {
			return album.get();
		} else {
			throw new IllegalArgumentException("Este Album não existe");
		}
	}

	public void deletarAlbum(Long id) throws BandaNotFoundException {
		Album album = buscarAlbumPorId(id);
		albumRepositorio.delete(album);
	}
}


