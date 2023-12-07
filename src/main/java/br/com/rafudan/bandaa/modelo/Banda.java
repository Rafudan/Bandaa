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

    public Banda(Long id, String nomeBanda, String anoCriacao, String generoMusical) {
        this.id = id;
        this.nomeBanda = nomeBanda;
        this.anoCriacao = anoCriacao;
        this.generoMusical = generoMusical;
    }

    public Banda() {

    }
}



