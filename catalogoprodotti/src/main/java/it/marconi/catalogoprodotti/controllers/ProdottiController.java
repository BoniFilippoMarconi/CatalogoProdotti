package it.marconi.catalogoprodotti.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import it.marconi.catalogoprodotti.domains.ProdottiForm;
import it.marconi.catalogoprodotti.services.ProdottiService;

@Controller
@RequestMapping("/")
public class ProdottiController {

    @Autowired
    ProdottiService prodottiService;

    @GetMapping("/catalogo")
    public ModelAndView mostraCatalogo() {
        
        ArrayList<ProdottiForm> prodotti = prodottiService.getProdotti();
        return new ModelAndView("catalogo").addObject("prodotti", prodotti);

    }
    
    @GetMapping("/prodotto/{codice}")
    public ModelAndView visualizzaProdotto(@PathVariable("codice") String codice) {

        Optional<ProdottiForm> prodotto = prodottiService.getProdotto(codice);

        if(prodotto.isPresent())
            return new ModelAndView("dettagliprodotto").addObject("prodotto", prodotto.get());
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/prodotto/nuovo")
    public ModelAndView aggiungiProdotto() {
        
        return new ModelAndView("nuovoprodotto").addObject("prodottiForm", new ProdottiForm());
    }

    @PostMapping("/prodotto/nuovo")
    public ModelAndView aggiornamentoCatalogo(@ModelAttribute ProdottiForm prodottiForm) {

        prodottiService.aggiungiProdotto(prodottiForm);
        return new ModelAndView("redirect:/catalogo");
    }

    @GetMapping("/prodotto/modifica/{codice}")
    public ModelAndView modificaProdotto(@PathVariable("codice") String codice){

        Optional<ProdottiForm> prodotto = prodottiService.getProdotto(codice);

        if(prodotto.isPresent())
            return new ModelAndView("modificaprodotto").addObject("prodotto", prodotto.get());
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/prodotto/modifica")
    public ModelAndView modificaProdotto(@ModelAttribute ProdottiForm prodottiForm){

        String codice = prodottiForm.getCodice();
        prodottiService.modificaProdotto(prodottiForm);
        
        return new ModelAndView("redirect:/prodotto/" + codice);
    }

    @GetMapping("/prodotto/elimina/{codice}")
    public ModelAndView eliminaProdotto(@PathVariable("codice") String codice){

        prodottiService.eliminaProdotto(codice);
        return new ModelAndView("redirect:/catalogo"); 
    }

    @GetMapping("/svuotaCatalogo")
    public ModelAndView svuotaCatalogo(){
        
        prodottiService.svuota();
        return new ModelAndView("redirect:/catalogo");
    }
}
