package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Incasari;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IncasariUtils {
    public static boolean validateIncasari(Incasari incasari) {
        return (null == incasari.getData() || incasari.getData().isEmpty()) || (null == incasari.getFurnizor() || incasari.getFurnizor().isEmpty()) ||
                (null == incasari.getNumber() || incasari.getNumber().isEmpty()) || (null == incasari.getDetalii() || incasari.getDetalii().isEmpty()) ||
                (incasari.getSumaTotala() == 0);
//                (incasari.getSumaFaraTVA() == 0) || (incasari.getSumaTVA() == 0);
    }

    public static String changeDateFormat(String date) {
        Date dateOne = new Date(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        return formatter.format(dateOne);
    }


    public static double calculareTVA(Incasari incasari){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double sumaTVA = incasari.getSumaTotala() * 19/119;
        return Double.parseDouble(numberFormat.format(sumaTVA));
    }

    public static double calculareFaraTVA(Incasari incasari){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double sumaFaraTVA = incasari.getSumaTotala() - calculareTVA(incasari);
        return Double.parseDouble(numberFormat.format(sumaFaraTVA));
    }

    public static double calculareTVA_Incasata(Incasari incasari){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double sumaTVA_Incasata = incasari.getSumaTotala_Incasata() * 19/119;
        return Double.parseDouble(numberFormat.format(sumaTVA_Incasata));
    }

    public static double calculareFaraTVA_Incasata(Incasari incasari){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double sumaFaraTVA_Incasata = incasari.getSumaTotala_Incasata() - calculareTVA_Incasata(incasari);
        return Double.parseDouble(numberFormat.format(sumaFaraTVA_Incasata));
    }

    public static double Rest(Incasari incasari){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double rest = incasari.getSumaTotala() - incasari.getSumaTotala_Incasata();
        return Double.parseDouble(numberFormat.format(rest));
    }



}
