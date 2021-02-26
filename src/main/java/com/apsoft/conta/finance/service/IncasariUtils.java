package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Incasari;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IncasariUtils {
    public static boolean validateIncasari(Incasari incasari) {
        return (null == incasari.getData() || incasari.getData().isEmpty()) || (null == incasari.getFurnizor() || incasari.getFurnizor().isEmpty()) ||
                (null == incasari.getNumber() || incasari.getNumber().isEmpty()) || (null == incasari.getDetalii() || incasari.getDetalii().isEmpty()) ||
                (incasari.getSumaTotala() == 0) || (incasari.getSumaFaraTVA() == 0) || (incasari.getSumaTVA() == 0);
    }

    public static String changeDateFormat(String date) {
        Date dateOne = new Date(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        return formatter.format(dateOne);
    }


}
