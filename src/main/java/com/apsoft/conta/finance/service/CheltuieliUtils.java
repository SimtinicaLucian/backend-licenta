package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.exception.HttpError;
import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Incasari;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

//    public static String Stadiu(Cheltuieli cheltuieli) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
//
//        if ((sdf.parse(cheltuieli.getData_Plata()).after(sdf.parse(cheltuieli.getData_Scadenta()))) && ((cheltuieli.getSumaTotala() > cheltuieli.getSumaTotala_Achitata())) || (cheltuieli.getSumaTotala() > cheltuieli.getSumaTotala_Achitata())) {
//            cheltuieli.setStare("intarziata");
//        } else if(cheltuieli.getSumaTotala() == cheltuieli.getSumaTotala_Achitata()){
//            cheltuieli.setStare("achitata");
//        }  else if(cheltuieli.getSumaTotala_Achitata() == 0){
//            cheltuieli.setStare("neachitata");
//        } else if(cheltuieli.getSumaTotala() > cheltuieli.getSumaTotala_Achitata()){
//            cheltuieli.setStare("partial achitata");
//        } else if(cheltuieli.getSumaTotala() < cheltuieli.getSumaTotala_Achitata()){
//            throw HttpError.notFound("Error");
//        }
//
//        return cheltuieli.getStare();
//    }

    public static String setStare(Cheltuieli cheltuieli) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();


//        if ((sdf.parse(incasari.getData_Plata()).after(sdf.parse(incasari.getData_Scadenta()))) && ((incasari.getSumaTotala() > incasari.getSumaTotala_Incasata())) || (incasari.getSumaTotala() > incasari.getSumaTotala_Incasata())) {
//            incasari.setStare("Intarziat");
//        }
        if(cheltuieli.getSumaTotala() == cheltuieli.getSumaTotala_Achitata()){
            cheltuieli.setStare("achitata");
        }
        else if ((sdf.parse(String.valueOf(dateFormat.format(date))).after(sdf.parse(cheltuieli.getData_Scadenta()))) && ((cheltuieli.getSumaTotala_Achitata() == 0)|| cheltuieli.getSumaTotala() > cheltuieli.getSumaTotala_Achitata())) {
            cheltuieli.setStare("intarziata");
        }else if(cheltuieli.getSumaTotala_Achitata() == 0){
            cheltuieli.setStare("neachitata");
        } else if(cheltuieli.getSumaTotala() > cheltuieli.getSumaTotala_Achitata()){
            cheltuieli.setStare("partial achitata");
        } else if(cheltuieli.getSumaTotala() < cheltuieli.getSumaTotala_Achitata()){
            throw HttpError.notFound("Error");
        }

        return cheltuieli.getStare();
    }


    public static String setSearchAll_Cheltuieli(Cheltuieli cheltuieli) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");




        try {
            Date date = new Date();
//            System.out.println("data este: " + dateFormat.format(date));
//            System.out.println("data2 este: " + sdf.parse(incasari.getData_Scadenta()));



//        if (((incasari.getSumaTotala_Incasata() == 0) || incasari.getSumaTotala() > incasari.getSumaTotala_Incasata()) && (sdf.parse(String.valueOf(dateFormat.format(date))).equals(sdf.parse(incasari.getData_Scadenta())))) {
//            incasari.setStare("intarziata");
//        }
            if ((dateFormat.parse(String.valueOf(dateFormat.format(date))).after(sdf.parse(cheltuieli.getData_Scadenta()))) && ((cheltuieli.getSumaTotala_Achitata() == 0) || cheltuieli.getSumaTotala() > cheltuieli.getSumaTotala_Achitata())) {
                cheltuieli.setStare("intarziata");
            }
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return cheltuieli.getStare();

    }



    }



