package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.exception.HttpError;
import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.persistence.Salariu;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalariuUtils {

    public static String setStare(Salariu salariu) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        if(salariu.getSalariu_net() == salariu.getSalariu_net_achitat()){
            salariu.setStare("achitata");
        }
        else if ((sdf.parse(String.valueOf(dateFormat.format(date))).after(sdf.parse(salariu.getData_Scadenta()))) && ((salariu.getSalariu_net_achitat() == 0)|| salariu.getSalariu_net() > salariu.getSalariu_net_achitat())) {
            salariu.setStare("intarziata");
        }else if(salariu.getSalariu_net_achitat() == 0){
            salariu.setStare("neachitata");
        } else if(salariu.getSalariu_net() > salariu.getSalariu_net_achitat()){
            salariu.setStare("partial achitata");
        } else if(salariu.getSalariu_net() < salariu.getSalariu_net_achitat()){
            throw HttpError.notFound("Error");
        }

        return salariu.getStare();
    }


    public static String setSearchAll_salariu(Salariu salariu) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        try {
            Date date = new Date();
            if ((dateFormat.parse(String.valueOf(dateFormat.format(date))).after(sdf.parse(salariu.getData_Scadenta()))) && ((salariu.getSalariu_net_achitat() == 0) || salariu.getSalariu_net() > salariu.getSalariu_net_achitat())) {
                salariu.setStare("intarziata");
            }
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return salariu.getStare();

    }

    public static double calculareSalariu_net(Salariu salariu){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double salariu_net_partea1 = salariu.getSalariu_brut() - salariu.getSalariu_brut() * 0.25 - salariu.getSalariu_brut() * 0.1;
        double salariu_net = salariu_net_partea1 - salariu_net_partea1 * 0.1;
        return Double.parseDouble(numberFormat.format(salariu_net));
    }

    public static double Rest(Salariu salariu){
        DecimalFormat numberFormat = new DecimalFormat("#.##");
        double rest = salariu.getSalariu_net() - salariu.getSalariu_net_achitat();
        return Double.parseDouble(numberFormat.format(rest));
    }
}
