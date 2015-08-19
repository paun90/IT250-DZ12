
package com.milospaunovic.sedam.services;

import com.milospaunovic.sedam.entities.Rezervacija;
import java.util.List;


public interface RezervacijaDao {
    public List<Rezervacija> getListaSvihRezervacija();
    public Rezervacija getRezervacijaById(Integer id);
    public void dodajRezervaciju(Rezervacija rezervacija);
    public void obrisiRezervaciju(Integer id);
    public void dodajiliUpdatujRezervacija (Rezervacija rezervacija);
    
}
