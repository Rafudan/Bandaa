package br.com.rafudan.bandaa.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Banda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Banda nao pode ser em branco")
    private String nomeBanda;
    @NotBlank(message = "Ano de Criação nao pode ser em branco")

    private String anoCriacao;
    @NotBlank(message = "Genero Musical nao pode ser em branco")
    private String generoMusical;
    @OneToMany(mappedBy = "banda",  cascade = CascadeType.ALL)
    private List<Album> albuns;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeBanda() {
		return nomeBanda;
	}
	public void setNomeBanda(String nomeBanda) {
		this.nomeBanda = nomeBanda;
	}
	public String getAnoCriacao() {
		return anoCriacao;
	}
	public void setAnoCriacao(String anoCriacao) {
		this.anoCriacao = anoCriacao;
	}
	public String getGeneroMusical() {
		return generoMusical;
	}
	public void setGeneroMusical(String generoMusical) {
		this.generoMusical = generoMusical;
	}
	public List<Album> getAlbuns() {
		return albuns;
	}
	public void setAlbuns(List<Album> albuns) {
		this.albuns = albuns;
	}

  

}



