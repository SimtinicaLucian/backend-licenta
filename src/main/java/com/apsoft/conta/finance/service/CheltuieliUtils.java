package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Cheltuieli;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CheltuieliUtils {
    public static boolean validareCheltuieli(Cheltuieli cheltuieli) {
        return (null == cheltuieli.getData() || cheltuieli.getData().isEmpty()) || (null == cheltuieli.getBeneficiar() || cheltuieli.getBeneficiar().isEmpty()) ||
                (null == cheltuieli.getNumber() || cheltuieli.getNumber().isEmpty()) || (null == cheltuieli.getDetalii() || cheltuieli.getDetalii().isEmpty()) ||
                (cheltuieli.getSumaTotala() == 0);
    }


    public static double calculareTVA(Cheltuieli cheltuieli){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double sumaTVA = cheltuieli.getSumaTotala() * 19/119;
        return Double.parseDouble(numberFormat.format(sumaTVA));
    }

    public static double calculareFaraTVA(Cheltuieli cheltuieli){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double sumaFaraTVA = cheltuieli.getSumaTotala() - calculareTVA(cheltuieli);
        return Double.parseDouble(numberFormat.format(sumaFaraTVA));
    }

    public static double calculareTVA_Achitata(Cheltuieli cheltuieli){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double sumaTVA_Achitata = cheltuieli.getSumaTotala_Achitata() * 19/119;
        return Double.parseDouble(numberFormat.format(sumaTVA_Achitata));
    }

    public static double calculareFaraTVA_Achitata(Cheltuieli cheltuieli){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double sumaFaraTVA_Achitata = cheltuieli.getSumaTotala_Achitata() - calculareTVA_Achitata(cheltuieli);
        return Double.parseDouble(numberFormat.format(sumaFaraTVA_Achitata));
    }

    public static double Rest(Cheltuieli cheltuieli){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double rest = cheltuieli.getSumaTotala() - cheltuieli.getSumaTotala_Achitata();
        return Double.parseDouble(numberFormat.format(rest));
    }

    }



