/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabalhoBD;

import javax.swing.JOptionPane;

/**
 *
 * @author Alex Barboza
 */
public class ControllerConsulta {
    private static ControllerConsulta instance = null;
    private ArrayDisputaModalidade navigation;
    
    private ControllerConsulta() {
        navigation = ArrayDisputaModalidade.getInstance();
    }
    
    public static ControllerConsulta getInstance() {
        if (instance == null) {
            instance = new ControllerConsulta();
        }
        return instance;
    }
    
    public DisputaModalidade next() {
        return navigation.getNextIndexedElement();
    }
    
    public DisputaModalidade previous() {
        return navigation.getPreviousIndexedElement();
    }
    
    public DisputaModalidade first() {
        return navigation.getFirstIndexedElement();
    }
    
    public DisputaModalidade last() {
        return navigation.getLastIndexedElement();
    }
    
    public void delete(int universidade, String modalidade) {
        if (modalidade == null)
            return;
        DisputaModalidade dm = new DisputaModalidade();
        dm.setUniversidadeDisputa(universidade);
        dm.setModalidade(modalidade);
        int status_delete = navigation.deleteItem(dm);
        if (status_delete == 0) {
       
            JOptionPane.showMessageDialog(null, 
                                          "Exclusão efetuada com sucesso!", 
                                          "Mensagem", 
                                          JOptionPane.INFORMATION_MESSAGE);
            navigation = ArrayDisputaModalidade.getInstance();
        } else {
            JOptionPane.showMessageDialog(null, 
                                          "Exclusão não pode ser efetuada", 
                                          "Mensagem", 
                                          JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void update(int universidade, String modalidade, int nouros,
                       int npratas, int nbronzes) {
        int status_update = navigation.updateItem(universidade, modalidade, nouros, npratas, nbronzes);
        if (status_update == 0) {
            JOptionPane.showMessageDialog(null, 
                                          "Atualização efetuada com sucesso!", 
                                          "Mensagem", 
                                          JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, 
                                          "Atualização não pode ser efetuada", 
                                          "Mensagem", 
                                          JOptionPane.INFORMATION_MESSAGE);
        }
    }   
}
