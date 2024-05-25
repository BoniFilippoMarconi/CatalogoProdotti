package it.marconi.catalogoprodotti.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdottiForm {
    
    private String codice;
    private String nome;
    private String categoria;
    private String anno;
    private int quantità;
}
