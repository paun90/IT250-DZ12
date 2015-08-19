/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milospaunovic.sedam.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Paun
 */
@Entity
@Table(name = "rezervacija")
@NamedQueries({
    @NamedQuery(name = "Rezervacija.findAll", query = "SELECT r FROM Rezervacija r")})
public class Rezervacija implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REZ_ID")
    private Integer rezId;
    @Lob
    @Column(name = "BROJREZERVACIJE")
    private String brojrezervacije;
    @Column(name = "BROJDANA")
    private Integer brojdana;
    @Column(name = "CENA")
    private Integer cena;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private User userId;
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    @ManyToOne
    private Soba id;
    
    @Inject
    public Rezervacija() {
    }

    public Rezervacija(Integer rezId) {
        this.rezId = rezId;
    }

    public Integer getRezId() {
        return rezId;
    }

    public void setRezId(Integer rezId) {
        this.rezId = rezId;
    }

    public String getBrojrezervacije() {
        return brojrezervacije;
    }

    public void setBrojrezervacije(String brojrezervacije) {
        this.brojrezervacije = brojrezervacije;
    }

    public Integer getBrojdana() {
        return brojdana;
    }

    public void setBrojdana(Integer brojdana) {
        this.brojdana = brojdana;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Soba getId() {
        return id;
    }

    public void setId(Soba id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rezId != null ? rezId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rezervacija)) {
            return false;
        }
        Rezervacija other = (Rezervacija) object;
        if ((this.rezId == null && other.rezId != null) || (this.rezId != null && !this.rezId.equals(other.rezId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.milospaunovic.sedam.entities.Rezervacija[ rezId=" + rezId + " ]";
    }
    
}
