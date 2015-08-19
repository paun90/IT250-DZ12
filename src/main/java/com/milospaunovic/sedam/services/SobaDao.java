/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milospaunovic.sedam.services;

import com.milospaunovic.sedam.entities.Soba;
import java.util.List;

/**
 *
 * @author Paun
 */
public interface SobaDao {
    public List<Soba> getListaSvihSoba();
    public Soba getSobaById(Integer id);
    public void dodajSobu(Soba soba);
    public void obrisiSobu(Integer id);
    public void dodajIliUpdatujSoba(Soba soba);
}
