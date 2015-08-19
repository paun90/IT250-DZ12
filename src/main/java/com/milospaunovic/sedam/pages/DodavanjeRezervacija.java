/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milospaunovic.sedam.pages;

import com.milospaunovic.sedam.entities.Rezervacija;
import com.milospaunovic.sedam.entities.Soba;
import com.milospaunovic.sedam.services.ProtectedPage;
import com.milospaunovic.sedam.services.RezervacijaDao;
import com.milospaunovic.sedam.services.SobaDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;

@ProtectedPage
@RolesAllowed(value = {"Korisnik", "Admin", "Recepcionar"})
public class DodavanjeRezervacija {

    @Property
    @Persist
    private Rezervacija rezervacija;

    @Property
    private Rezervacija onerezervacija;

    @Inject
    private Messages messages;

    @Inject
    private RezervacijaDao rezervacijaDao;

    @Inject
    private SobaDao sobaDao;

    @Property
    private Soba sobid;

    @Property
    @Persist
    private List<Soba> listaSoba;

    public ValueEncoder getEncoder() {
        return new ValueEncoder<Soba>() {

            @Override
            public String toClient(Soba v) {
                return String.valueOf(v.getId());
            }

            @Override
            public Soba toValue(String string) {
                Soba sob = sobaDao.getSobaById(Integer.parseInt(string));
                return sob;
            }

        };
    }

    @Property
    private List<Rezervacija> listaRezervacija;

    void onActivate() {
        rezervacija = new Rezervacija();
        if (listaRezervacija == null) {
            listaRezervacija = new ArrayList<Rezervacija>();
        }
        listaRezervacija = rezervacijaDao.getListaSvihRezervacija();
        listaSoba = sobaDao.getListaSvihSoba();
    }

    @CommitAfter
    Object onSuccess() {
        rezervacija.setId(sobid);
        rezervacijaDao.dodajiliUpdatujRezervacija(rezervacija);
        rezervacija = new Rezervacija();
        return this;
    }

    public String getSoba() {
        if (onerezervacija.getId() != null) {
            return onerezervacija.getId().getImesobe();
        } else {
            return "";
        }
    }

    @CommitAfter
    Object onActionFromDelete(int id) {
        rezervacijaDao.obrisiRezervaciju(id);
        return this;
    }

    @CommitAfter
    Object onActionFromEdit(Rezervacija rezervacije) {
        rezervacija = rezervacije;
        return this;
    }

    public JSONObject getOptions() {
        JSONObject json = new JSONObject();
        json.put("bJQueryUI", true);
        json.put("bStateSave", true);
        json.put("bAutoWidth", true);
        return json;
    }
}
