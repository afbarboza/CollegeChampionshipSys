/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabalhoBD;

/**
 *
 * @author Alex Barboza
 */
public class Modalidade {
    private String nomeModalidade;
    private char categoriaModalidade;

    public Modalidade() {
        
    }
    
    public String getNomeModalidade() {
        return nomeModalidade;
    }

    public void setNomeModalidade(String nomeModalidade) {
        this.nomeModalidade = nomeModalidade;
    }

    public char getCategoriaModalidade() {
        return categoriaModalidade;
    }

    public void setCategoriaModalidade(char categoriaModalidade) {
        this.categoriaModalidade = categoriaModalidade;
    }
    
    
}
