/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabalhoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alex Barboza
 */
public class ArrayDisputaModalidade {
    private static ArrayDisputaModalidade instance = null;
    private static ArrayList<DisputaModalidade> adm;
    private static int indexElement;
    
    private ArrayDisputaModalidade() {
        adm = new ArrayList<>();
    }
    
    public static ArrayDisputaModalidade getInstance() {
        if (instance == null) {
            instance = new ArrayDisputaModalidade();
            indexElement = -1;
        }
        adm.clear();
        retrieveData();
        return instance;
    }

    private static void retrieveData() {
        ConnectionClass conn = ConnectionClass.getInstance();
        if (conn == null) {
            System.err.println("error at:"
                               + " retrieveData in ArrayDisputaModalidade.java"
                               + " null pointer for connection");
            return;
        }
        
        try {
            Statement stmt = conn.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DISPUTAMODALIDADE");
            
            Statement stmt_university = conn.getConnection().createStatement();
            ResultSet rs_university;
            
            Statement stmt_category = conn.getConnection().createStatement();
            ResultSet rs_category;
            
            while (rs.next()) {
               int universidade = Integer.parseInt(rs.getString("UNIVERSIDADE"));
               String modalidade = rs.getString("MODALIDADE");
               DisputaModalidade dm = new DisputaModalidade();
               dm.setUniversidadeDisputa(universidade);
               dm.setModalidade(modalidade);
               dm.setOuro(Integer.parseInt(rs.getString("OURO")));
               dm.setPrata(Integer.parseInt(rs.getString("PRATA")));
               dm.setBronze(Integer.parseInt(rs.getString("BRONZE")));
               String sql1, sql2;
               sql1 = "SELECT * FROM UNIVERSIDADE WHERE COD_UNIVERSIDADE = " + universidade;
               sql2 = "SELECT * FROM MODALIDADE WHERE MODALIDADE = '"  + modalidade + "'";
               rs_university = stmt_university.executeQuery(sql1);
               rs_category = stmt_category.executeQuery(sql2);
               while(rs_university.next())
                    dm.setUniversidadeName(rs_university.getString("NOME"));
               while (rs_category.next())
                    dm.setCategoria(rs_category.getString("CATEGORIA"));
               adm.add(dm);
            }
        } catch (SQLException ex) {
            String error = "error at retrieveData() in ArrayDisputaModalidade " 
                           + "could not execute the query.";
            System.err.println(error);
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            System.err.println("error at retrieveData() in ArrayDisputaModalidade.java: "
                    + "Could not solve number handling.\n");
        }
    }
    
    public int deleteItem(DisputaModalidade dm) {
        if (dm == null) {
            System.err.println("error ath deleteItem() at ArrayDisputaModalidade.java: "
                            + "trying to remove a null pointer from array.");
             return 1;
        }
        
        DisputaModalidade toDelete = searchItem(dm);
        if (toDelete == null) {
            return 2;
        }

        Statement stmt;
        ResultSet rs;
        Connection connection;
        PreparedStatement pstmt;
        
        String sql = "DELETE FROM DISPUTAMODALIDADE WHERE UNIVERSIDADE = " +   
                     toDelete.getUniversidadeDisputa() + 
                    " AND MODALIDADE = '" + toDelete.getModalidade() +"'";
        Connection conn = ConnectionClass.getInstance().getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            return 0;
        } catch (SQLException ex) {
            System.err.println("Error at deleteItem() at ArrayDisputaModalidade.java: "
                               + "could not remove data from datase");
            return 3;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 4;
        }
    }
    
    public int updateItem(int univ, String modalidade, int nouros, int npratas, int nbronzes) {
        if (nouros < 0 || npratas < 0 || nbronzes < 0) {
            return 1;
        }
        String sql = "UPDATE DISPUTAMODALIDADE SET "
                     + "OURO = " + nouros + ", "
                     + "PRATA = " + npratas + ", " 
                     + "BRONZE = " + nbronzes + " " +
                     "WHERE UNIVERSIDADE = " + univ +
                     " AND MODALIDADE = '" +  modalidade + "'";
        Statement stmt;
        ResultSet rs;
        Connection connection;
        PreparedStatement pstmt;
        Connection conn = ConnectionClass.getInstance().getConnection();
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            adm.clear();
            retrieveData();
            return 0;
        } catch (SQLException ex) {
            System.err.println("Error at updateItem() at ArrayDisputaModalidade.java: "
                               + "could not update data from datase");
            System.out.println("Error at: " + sql);
            ex.printStackTrace();
            return 1;
        }
    }
    
    public DisputaModalidade getNextIndexedElement() {
        indexElement++;
        if (indexElement >= adm.size())
            indexElement = 0;
        return adm.get(indexElement);
    }
    
    public DisputaModalidade getPreviousIndexedElement() {
        indexElement--;
        if (indexElement < 0)
            indexElement = adm.size() - 1;
        return adm.get(indexElement);
    }
    
    public DisputaModalidade getFirstIndexedElement() {
        indexElement = 0;
        return adm.get(indexElement);
    }
    
    public DisputaModalidade getLastIndexedElement() {
        indexElement = adm.size()-1;
        return adm.get(indexElement);
    }

    public static DisputaModalidade searchItem(DisputaModalidade dm) {
        for (DisputaModalidade i : adm) {
            if (dm.getUniversidadeDisputa() == i.getUniversidadeDisputa()) {
                if (dm.getModalidade().compareTo(i.getModalidade()) == 0) {
                    return i;
                }
            }
        }
        return null;
    }
}
