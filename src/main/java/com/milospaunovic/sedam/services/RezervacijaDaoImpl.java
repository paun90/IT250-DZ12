/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milospaunovic.sedam.services;

import com.milospaunovic.sedam.entities.Rezervacija;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Paun
 */
public class RezervacijaDaoImpl implements RezervacijaDao{

    @Inject
    private Session session;
    
    @Override
    public List<Rezervacija> getListaSvihRezervacija() {
        return session.createCriteria(Rezervacija.class).list();
    }

    @Override
    public Rezervacija getRezervacijaById(Integer id) {
        System.out.println("integer je " + id);
        return (Rezervacija) session.createCriteria(Rezervacija.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajRezervaciju(Rezervacija rezervacija) {
        session.persist(rezervacija);
    }

    @Override
    public void obrisiRezervaciju(Integer id) {
        Rezervacija rezervacija = (Rezervacija) session.createCriteria(Rezervacija.class).add(Restrictions.eq("rezId", id)).uniqueResult();
        session.delete(rezervacija);
    }

    @Override
    public void dodajiliUpdatujRezervacija(Rezervacija rezervacija) {
        session.merge(rezervacija);
    }
    
}
