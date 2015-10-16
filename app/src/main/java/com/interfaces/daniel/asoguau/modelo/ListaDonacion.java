package com.interfaces.daniel.asoguau.modelo;

import java.util.List;

/**
 * Created by hanyou on 04/10/15.
 */
public class ListaDonacion {

    private List<Donacion> items;

    public ListaDonacion(List<Donacion> items) {
        this.items = items;
    }

    public List<Donacion> getItems() {
        return items;
    }

    public void setItems(List<Donacion> items) {
        this.items = items;
    }
}
