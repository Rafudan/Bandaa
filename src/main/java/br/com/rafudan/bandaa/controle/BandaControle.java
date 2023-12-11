package br.com.rafudan.bandaa.controle;

import br.com.rafudan.bandaa.excessao.BandaNotFoundException;
import br.com.rafudan.bandaa.modelo.Banda;
import br.com.rafudan.bandaa.servico.BandaServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BandaControle {

    @Autowired
    private BandaServico bandaServico;

    @GetMapping("listaBandas")
    public String ListaBandas(Model model){
        List<Banda> bandas = bandaServico.buscarBandas();
        model.addAttribute("bandas", bandas);
        return "/listaBandas";
    }


    @GetMapping("/")
    public String novaBanda(Model model){
        Banda banda = new Banda();
        model.addAttribute("novaBanda", banda);
        return "/novaBanda";
    }


    @PostMapping("/salvar")
    public String salvarBanda(@ModelAttribute("novaBanda")@Valid Banda banda, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            return  "/novaBanda";
        }
        bandaServico.novaBanda(banda);
//        attributes.addFlashAttribute("mensagem", "Banda Cadastrada");
        return "redirect:/listaBandas"; // manda para o metodo lista banda
    }

    @GetMapping("/editar/{id}")
    public String editarBanda(@PathVariable("id") Long id, RedirectAttributes attributes, Model model){
        try{
            Banda banda = bandaServico.buscaBandaId(id);
            model.addAttribute("banda", banda);
            return "/editaBanda";// manda para o html

        }catch(BandaNotFoundException e ){
            attributes.addFlashAttribute("mensagem erro", e.getMessage());
        }
        return "redirect:/"; // manda para pagina principal
    }


    @PostMapping("/editar/{id}")
    public String salvarEditarBanda(@PathVariable("id") Long id, @ModelAttribute("banda")@Valid Banda banda, BindingResult result){
        if(result.hasErrors()){
            return "/editaBanda";
        }
        bandaServico.editaBanda(banda);
        return "redirect:/";
    }

//    @PostMapping("/buscaBanda")
//    public String buscaBanda(@Param("nomeBanda")String nomeBanda, Model model){
//        if (nomeBanda == null){
//            return  "redirect:/";
//        }
//        List<Banda> bandas = bandaServico.buscaBandaNome(nomeBanda);
//        model.addAttribute("bandas", bandas);
//        return "/listaBandas";
//    }

    @GetMapping("/deletar/{id}")
    public String deletarBanda(@PathVariable("id") Long id, RedirectAttributes attributes){
        try{
            bandaServico.deletaBanda(id);
        }catch(BandaNotFoundException e){
            attributes.addFlashAttribute("mensagem erro", e.getMessage());
        }
        return "redirect:/listaBandas";
    }
}
