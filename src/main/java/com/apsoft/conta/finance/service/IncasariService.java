package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Incasari;

import java.util.List;

public interface IncasariService {


    Incasari saveIncasari(Incasari incasari);


    List<Incasari> searchAll();
    List<Incasari> searchByData(String data);
    List<Incasari> searchByFurnizor(String furnizor);
    List<Incasari> searchByNumber(int number);


    Incasari update(int number, Incasari incasari);
    void deleteNumber(int number);

    double calculateTotalByMonth(String month);
    double calculateTotalByYear(String year);

    double calculateTotalByMonthAndYear(String month, String year);


    double calculateTotalTVA();
    double calculateTVAByDate(String data);

    Incasari updaterows(Incasari incasari);

    double calculateSumaTotala();
    double calculateSumaFaraTVA();


}