package br.com.rafudan.bandaa.repositorio;

import br.com.rafudan.bandaa.modelo.Banda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BandaRepositorio extends JpaRepository<Banda, Long> {

    List<Banda> findByNomeBandaContainingIgnoreCase(String nomeBanda);
}
