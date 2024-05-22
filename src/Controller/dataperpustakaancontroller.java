/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.List;
import DAOdataperpustakaan.dataperpustakaanDAO;
import DAOImplement.dataperpustakaanimplemnt;
import javax.swing.JOptionPane;
import Model.*;
import View.MainView;

public class dataperpustakaancontroller {

    private boolean inputgagal = false;

    MainView frame;
    dataperpustakaanimplemnt impldataperpustakaan;
    List<dataperpustakaan> dp;

    private boolean cekInput() {
        return !frame.getInjudul().getText().isEmpty()
                && !frame.getInpenulis().getText().isEmpty()
                && !frame.getInrating().getText().isEmpty()
                && !frame.getInharga().getText().isEmpty();
    }

    public dataperpustakaancontroller(MainView frame) {
        this.frame = frame;
        impldataperpustakaan = new dataperpustakaanDAO();
        dp = impldataperpustakaan.getAll();
    }

    public void showTabel() {
        dp = impldataperpustakaan.getAll();
        modeltabeldataperpustakaan p = new modeltabeldataperpustakaan(dp);
        frame.getTabelData().setModel(p);

    }

    public void insert() {
        if (cekInput()) {
            try {
                dataperpustakaan dp = new dataperpustakaan();
                dp.setJudul(frame.getInjudul().getText());
                dp.setPenulis(frame.getInpenulis().getText());
                dp.setRating(Float.parseFloat(frame.getInrating().getText()));
                dp.setHarga(Integer.parseInt(frame.getInharga().getText()));
                inputgagal = false;
                impldataperpustakaan.insert(dp);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ulangi pengisian data", "error", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                inputgagal = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ulangi pengisian data", "error", JOptionPane.WARNING_MESSAGE);
            inputgagal = true;
        }
    }
    
    public void update() {
        if (cekInput()) {
            try {
                dataperpustakaan dp = new dataperpustakaan();
                dp.setJudul(frame.getInjudul().getText());
                dp.setPenulis(frame.getInpenulis().getText());
                dp.setRating(Float.parseFloat(frame.getInrating().getText()));
                dp.setHarga(Integer.parseInt(frame.getInharga().getText()));
                inputgagal = false;
                impldataperpustakaan.insert(dp);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ulangi pengisian data", "error", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                inputgagal = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ulangi pengisian data", "error", JOptionPane.WARNING_MESSAGE);
            inputgagal = true;
        }
    }
    
    public void delete() {
        
                String judul = frame.getInjudul().getText();
                impldataperpustakaan.delete(judul);
            
    }

}
