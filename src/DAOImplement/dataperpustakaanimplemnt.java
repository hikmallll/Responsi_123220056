/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplement;

import java.util.List;
import Model.*;
public interface dataperpustakaanimplemnt {
    public void insert(dataperpustakaan p);
    public void update(dataperpustakaan p);
    public void delete(String judul);
    public List<dataperpustakaan> getAll();
}
