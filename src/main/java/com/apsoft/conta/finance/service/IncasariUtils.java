package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.exception.HttpError;
import com.apsoft.conta.finance.persistence.Incasari;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class IncasariUtils {
    public static boolean validateIncasari(Incasari incasari) {
        return (null == incasari.getData() || incasari.getData().isEmpty()) || (null == incasari.getFurnizor() || incasari.getFurnizor().isEmpty()) ||
                (null == incasari.getNumber() || incasari.getNumber().isEmpty()) || (null == incasari.getDetalii() || incasari.getDetalii().isEmpty()) ||
                (incasari.getSumaTotala() == 0);
//                (incasari.getSumaFaraTVA() == 0) || (incasari.getSumaTVA() == 0);
    }

    public static String changeDateFormat(String date) throws ParseException {
        Date dateOne = new Date(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        return formatter.format(dateOne);



//        Date dateOne = new SimpleDateFormat("yyyy-MM-dd").parse(date);
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
//        return formatter.format(dateOne);
    }


    public static String setStare(Incasari incasari) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();





//        if ((sdf.parse(incasari.getData_Plata()).after(sdf.parse(incasari.getData_Scadenta()))) && ((incasari.getSumaTotala() > incasari.getSumaTotala_Incasata())) || (incasari.getSumaTotala() > incasari.getSumaTotala_Incasata())) {
//            incasari.setStare("Intarziat");
//        }
        if(incasari.getSumaTotala() == incasari.getSumaTotala_Incasata()){
            incasari.setStare("achitata");
        }
        else if ((sdf.parse(String.valueOf(dateFormat.format(date))).after(sdf.parse(incasari.getData_Scadenta()))) && ((incasari.getSumaTotala_Incasata() == 0)|| incasari.getSumaTotala() > incasari.getSumaTotala_Incasata())) {
            incasari.setStare("intarziata");
        }else if(incasari.getSumaTotala_Incasata() == 0){
            incasari.setStare("neachitata");
        } else if(incasari.getSumaTotala() > incasari.getSumaTotala_Incasata()){
            incasari.setStare("partial achitata");
        } else if(incasari.getSumaTotala() < incasari.getSumaTotala_Incasata()){
            throw HttpError.notFound("Error");
        }


        return incasari.getStare();
    }

    public static String setSearchAll(Incasari incasari) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");




        try {
            Date date = new Date();
//            System.out.println("data este: " + dateFormat.format(date));
//            System.out.println("data2 este: " + sdf.parse(incasari.getData_Scadenta()));



//        if (((incasari.getSumaTotala_Incasata() == 0) || incasari.getSumaTotala() > incasari.getSumaTotala_Incasata()) && (sdf.parse(String.valueOf(dateFormat.format(date))).equals(sdf.parse(incasari.getData_Scadenta())))) {
//            incasari.setStare("intarziata");
//        }
            if ((dateFormat.parse(String.valueOf(dateFormat.format(date))).after(sdf.parse(incasari.getData_Scadenta()))) && ((incasari.getSumaTotala_Incasata() == 0) || incasari.getSumaTotala() > incasari.getSumaTotala_Incasata())) {
                incasari.setStare("intarziata");
            }
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return incasari.getStare();

    }



//    public static String Stadiu(Cheltuieli cheltuieli) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
//
//        if ((sdf.parse(cheltuieli.getData_Plata()).after(sdf.parse(cheltuieli.getData_Scadenta()))) && ((cheltuieli.getSumaTotala() > cheltuieli.getSumaTotala_Achitata())) || (cheltuieli.getSumaTotala() > cheltuieli.getSumaTotala_Achitata())) {
//            cheltuieli.setStare("Intarziat");
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







    public static double calculareTVA(Incasari incasari){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double sumaTVA = incasari.getSumaTotala() * incasari.getCota_TVA();
        if(incasari.getCota_TVA() == 19){
            sumaTVA = incasari.getSumaTotala() * 19/119;
        } else if(incasari.getCota_TVA() == 9){
            sumaTVA = incasari.getSumaTotala() * 9/109;
        } else if(incasari.getCota_TVA() == 5){
            sumaTVA = incasari.getSumaTotala() * 5/105;
        }
        return Double.parseDouble(numberFormat.format(sumaTVA));
    }

    public static double calculareFaraTVA(Incasari incasari){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double sumaFaraTVA = incasari.getSumaTotala() - calculareTVA(incasari);
        return Double.parseDouble(numberFormat.format(sumaFaraTVA));
    }

    public static double calculareTVA_Incasata(Incasari incasari){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double sumaTVA_Incasata = incasari.getSumaTotala_Incasata() * incasari.getCota_TVA();
        if(incasari.getCota_TVA() == 19){
            sumaTVA_Incasata = incasari.getSumaTotala_Incasata() * 19/119;
        } else if(incasari.getCota_TVA() == 9){
            sumaTVA_Incasata = incasari.getSumaTotala_Incasata() * 9/109;
        } else if(incasari.getCota_TVA() == 5){
            sumaTVA_Incasata = incasari.getSumaTotala_Incasata() * 5/105;
        }
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
