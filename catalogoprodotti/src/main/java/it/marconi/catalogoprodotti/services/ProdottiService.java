package it.marconi.catalogoprodotti.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.marconi.catalogoprodotti.domains.ProdottiForm;

@Service
public class ProdottiService {
    
    private ArrayList<ProdottiForm> prodotti = new ArrayList<ProdottiForm>();

    public ArrayList<ProdottiForm> getProdotti(){
        return prodotti;
    }

    public Optional<ProdottiForm> getProdotto(String codice){
        for(ProdottiForm p : prodotti){
            if(p.getCodice().equals(codice)){
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public void aggiungiProdotto(ProdottiForm prodotto){
        if(prodotto.getCodice() != "")
            prodotti.add(prodotto);
    }

    public void svuota(){
        prodotti = new ArrayList<ProdottiForm>();
    }

    public void modificaProdotto(ProdottiForm prodotto){

        String codice = prodotto.getCodice();
        for(int i = 0; i < prodotti.size(); i++){
            if(prodotti.get(i).getCodice().equals(codice)){
                prodotti.set(i, prodotto);
            }
        }
    }

    public void eliminaProdotto(String codice){
        System.out.println("Elimina Prodotto!!!!!!!!!!!!");
        for(int i = 0; i < prodotti.size(); i++){
            if(prodotti.get(i).getCodice().equals(codice)){
                System.out.println("Rimosso " + prodotti.get(i));
                prodotti.remove(i);
            }
        }
    }
}
