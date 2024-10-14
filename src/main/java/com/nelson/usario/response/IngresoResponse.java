package com.nelson.usario.response;

import com.nelson.usario.model.entity.Ingresos;
import java.util.List;

public class IngresoResponse {

    private List<Ingresos> ingreso;

    public List<Ingresos> getIngresos() {
        return ingreso;
    }

    public void setIngresos(List<Ingresos> ingreso) {
        this.ingreso = ingreso;
    }

}
