/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOdataperpustakaan;

import java.sql.*;
import java.util.*;
import Model.*;
import Koneksi.connector;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAOImplement.dataperpustakaanimplemnt;
import Model.dataperpustakaan;
import javax.swing.JOptionPane;

public class dataperpustakaanDAO implements dataperpustakaanimplemnt {

    Connection connection;
    final String select = "select *from buku";
    final String insert = "insert into buku (judul, penulis,rating, harga) VALUES (?, ?, ?, ?);";
    final String update = "update buku set penulis=?,rating=?,harga=?  where judul=?";
    final String delete = "delete from buku where judul=?;";

    public dataperpustakaanDAO() {
        connection = connector.connection();
    }

    @Override
    public void insert(dataperpustakaan p) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, p.getJudul());
            statement.setString(2, p.getPenulis());
            statement.setFloat(3, p.getRating());
            int total = (int)p.getHarga() + 500 + (int)(p.getRating()*100);
            statement.setInt(4,total);
            statement.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan", "sukses", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Ulangi", "error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(dataperpustakaan p) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);

            
            statement.setString(1, p.getPenulis());
            statement.setFloat(2, p.getRating());
            int total = (int)p.getHarga() + 500 + (int)(p.getRating()*100);
            statement.setInt(3,total);
            statement.setString(4, p.getJudul());
            statement.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data berhasil diupdate", "sukses", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Ulangi", "error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(String judul) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, judul);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil Terhapus", "sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        }
        try {
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<dataperpustakaan> getAll() {
        List<dataperpustakaan> dp = null;

        try {
            dp = new ArrayList<dataperpustakaan>();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(select);

            while (result.next()) {

                dataperpustakaan perpus = new dataperpustakaan();
                perpus.setJudul(result.getString("judul"));
                perpus.setPenulis(result.getString("penulis"));
                perpus.setRating(result.getFloat("rating"));
                perpus.setHarga(result.getInt("harga"));

                dp.add(perpus);
            }
            result.close();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(dataperpustakaanDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return dp;
    }
    

}
