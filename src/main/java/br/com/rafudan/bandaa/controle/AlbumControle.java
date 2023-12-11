package br.com.rafudan.bandaa.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.rafudan.bandaa.excessao.BandaNotFoundException;
import br.com.rafudan.bandaa.modelo.Album;
import br.com.rafudan.bandaa.modelo.Banda;
import br.com.rafudan.bandaa.servico.AlbumServico;
import br.com.rafudan.bandaa.servico.BandaServico;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/album")
public class AlbumControle {
	@Autowired
	private BandaServico bandaServico;

	@Autowired
	private AlbumServico albumServico;

	@GetMapping("/listaAlbum/{id}")
	public String listaAlbum(@PathVariable("id") long id, Model model) throws BandaNotFoundException {
		Banda banda = bandaServico.buscaBandaId(id);
		List<Album> albuns = albumServico.buscarAlbunsPorBanda(id);
		model.addAttribute("albuns", albuns);
		model.addAttribute("banda", banda);
		return "listaAlbuns";
	}

	@GetMapping("/novoAlbum/{id}")
	public String novoAlbum(@PathVariable("id") long id, Model model) throws BandaNotFoundException {
		Album album = new Album();
		Banda banda = bandaServico.buscaBandaId(id);
		album.setBanda(banda);
		model.addAttribute("album", album);
		return "novoAlbum";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Album album, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			List<Banda> bandas = bandaServico.buscarBandas();
			model.addAttribute("bandas", bandas);
			return "novoAlbum";
		}
		Long id = album.getBanda().getId();
		albumServico.salvar(album);
		attributes.addFlashAttribute("mensagem", "Gravado com sucesso.");
		return "redirect:/album/listaAlbum/" + id;
	}


	@GetMapping("/alteraAlbum/{id}")
	public String alteraAlbum(@PathVariable("id") long id, RedirectAttributes attributes, Model model) {

		model.addAttribute("album", albumServico.buscarAlbumPorId(id));
		return "/alteraAlbum";
	}

	@PostMapping("/alteraAlbum/{id}")
	public String alterarAlbum(@PathVariable("id") long id,
							   @Valid Album album, BindingResult result, Model model,
							   RedirectAttributes attributes) {
		if (result.hasErrors()) {
			album.setId(id);
			return "/alteraAlbum/";
		}
		album.setId(id);
		albumServico.salvar(album);
		attributes.addFlashAttribute("mensagem", "Album atualizado.");
		return "redirect:/album/listaAlbum/" + album.getBanda().getId();
	}

	@GetMapping("/deletar/{id}")
	public String apagar(@PathVariable("id")Long id, RedirectAttributes atributos) {
		try {
			albumServico.deletarAlbum(id);
		} catch (BandaNotFoundException e) {
			atributos.addFlashAttribute("mensageErro", e.getMessage());
		}
		return "redirect:/listaBandas";
	}


}

