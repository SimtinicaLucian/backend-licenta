package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Cheltuieli;

public class CheltuieliUtils {
    public static boolean validareCheltuieli(Cheltuieli cheltuieli) {
        return (null == cheltuieli.getData() || cheltuieli.getData().isEmpty()) || (null == cheltuieli.getBeneficiar() || cheltuieli.getBeneficiar().isEmpty()) ||
                (null == cheltuieli.getNumber() || cheltuieli.getNumber().isEmpty()) || (null == cheltuieli.getDetalii() || cheltuieli.getDetalii().isEmpty()) ||
                (cheltuieli.getSumaTotala() == 0) || (cheltuieli.getSumaFaraTVA() == 0) || (cheltuieli.getSumaTVA() == 0);
    }
}
