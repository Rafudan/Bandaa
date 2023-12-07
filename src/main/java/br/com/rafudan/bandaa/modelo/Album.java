package br.com.rafudan.bandaa.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O titulo nao pode ser em branco")
    private String titulo;
    @Min(value = 1, message = "O Disco deve ter pelo menos uma faixa")
    private int faixas;
    @OneToOne
    private Banda banda;
}
