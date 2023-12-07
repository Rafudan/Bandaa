package br.com.rafudan.bandaa.servico;

import br.com.rafudan.bandaa.excessao.BandaNotFoundException;
import br.com.rafudan.bandaa.modelo.Banda;
import br.com.rafudan.bandaa.repositorio.BandaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BandaServico {
    @Autowired
    private BandaRepositorio bandaRepositorio;

    public Banda novaBanda(Banda banda){
        return bandaRepositorio.save(banda);
    }

    public List<Banda> buscarBandas(){
        return bandaRepositorio.findAll();
    }

    public Banda buscaBandaId(Long id)throws BandaNotFoundException {
        Optional<Banda>optional = bandaRepositorio.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        else {
            throw new BandaNotFoundException("Banda não encontrada");
        }
    }


    public Banda editaBanda(Banda banda){
        return bandaRepositorio.save(banda);
    }

    public List<Banda>buscaBandaNome(String nomeBanda){
        return bandaRepositorio.findByNomeBandaContainingIgnoreCase(nomeBanda);
    }


    public void deletaBanda(Long id) throws BandaNotFoundException{
        Banda banda = buscaBandaId(id);
        bandaRepositorio.delete(banda);
    }
}
