/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabalhoBD;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Barboza
 */
public class DisputaModalidade {
    private int universidadeDisputa;
    private String universidadeName;
    private String categoria;
    private String modalidade;
    private int ouro;
    private int prata;
    private int bronze;
    
    
    public DisputaModalidade() {
    }

    public int getUniversidadeDisputa() {
        return universidadeDisputa;
    }

    public void setUniversidadeDisputa(int universidadeDisputa) {
        this.universidadeDisputa = universidadeDisputa;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public int getOuro() {
        return ouro;
    }

    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public int getPrata() {
        return prata;
    }

    public void setPrata(int prata) {
        this.prata = prata;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }
    
    public String getUniversidadeName() {
        return universidadeName;
    }

    public void setUniversidadeName(String universidade) {
        this.universidadeName = universidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
