package com.interfaces.daniel.asoguau.modelo;

import java.util.List;

/**
 * Created by hanyou on 12/10/15.
 */
public class Noticias {

    private List<Noticia> items;

    public Noticias(List<Noticia> items) {
        this.items = items;
    }

    public List<Noticia> getItems() {
        return items;
    }

    public void setItems(List<Noticia> items) {
        this.items = items;
    }
}
