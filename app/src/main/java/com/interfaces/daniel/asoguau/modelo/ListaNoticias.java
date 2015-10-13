package com.interfaces.daniel.asoguau.modelo;

import java.util.List;

/**
 * Created by hanyou on 04/10/15.
 */
public class ListaNoticias {

    private List<Noticia> items;

    public ListaNoticias(List<Noticia> items) {
        this.items = items;
    }

    public List<Noticia> getItems() {
        return items;
    }

    public void setItems(List<Noticia> items) {
        this.items = items;
    }
}
