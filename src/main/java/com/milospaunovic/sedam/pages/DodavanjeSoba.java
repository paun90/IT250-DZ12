
package com.milospaunovic.sedam.pages;

import com.milospaunovic.sedam.entities.Soba;
import com.milospaunovic.sedam.services.ProtectedPage;
import com.milospaunovic.sedam.services.SobaDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.got5.tapestry5.jquery.components.InPlaceEditor;

@ProtectedPage
@RolesAllowed(value = {"Admin", "Recepcionar"})
public class DodavanjeSoba {
    
    @Property
    @Persist
    private Soba soba;
    
    @Property
    private Soba onesoba;
    
    @Inject
    private SobaDao sobaDao;
    
    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;
    
    @Inject
    private ComponentResources componentResources;
    
    @Property
    private ArrayList<Soba> listaSoba;
    
    void onActivate(){
        if (listaSoba == null){
            listaSoba = new ArrayList<Soba>();
        }
        listaSoba = (ArrayList<Soba>) sobaDao.getListaSvihSoba();
    }
    
    @CommitAfter
    Object onSuccess(){
        sobaDao.dodajIliUpdatujSoba(soba);
        soba = new Soba();
        return this;
    }
    
    @CommitAfter
    Object onActionFromDelete(int id) {
        sobaDao.obrisiSobu(id);
        return this;
    }
    
    @CommitAfter
    Object onActionFromEdit(Soba sobe){
        soba = sobe;
        return this;
    }
    
    public JSONObject getOptions(){
        JSONObject json = new JSONObject();
        json.put("bJQeryUI", true);
        json.put("bStateSave", true);
        json.put("bAutoWidth", true);
        return json;
    }
    
    @CommitAfter
    @OnEvent(component = "imeSobe", value = InPlaceEditor.SAVE_EVENT)
    void setImeSobe(Long id, String value){
        Soba soba = (Soba) sobaDao.getSobaById(id.intValue());
        soba.setImesobe(value);
        System.out.println("cuvam sobu");
        sobaDao.dodajIliUpdatujSoba(soba);
        
    }
    
}
