package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Incasari;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface IncasariService {


    Incasari saveIncasari(Incasari incasari);


    List<Incasari> searchAll();
    List<Incasari> searchByData(String data);
    List<Incasari> searchByFurnizor(String furnizor);



    List<Incasari> searchByNumber(int number);
    List<Incasari> searchByMonthAndYear(String month, String year);
    List<Incasari> searchByBetweenData(String data1, String data2);
    List<Incasari> searchByBetweenSumaTotala(String data1, String data2, double sumaTotala1, double sumaTotala2);
    List<Incasari> searchByFurnizorAndBetweenSumaTotala(String furnizor, String data1, String data2, double sumaTotala1, double sumaTotala2);


    List<Incasari> testMethod(Map<String, String> params);





    Incasari update(int number, Incasari incasari);
    void deleteNumber(int number);

    double calculateTotalByMonth(String month);
    double calculateTotalByYear(String year);

    List<Incasari> searchByYear(String year);

    double calculateTotalByMonthAndYear(String month, String year);

    double calculateTotalByDataBetweenData(String data1, String data2);



    double calculateTotalTVA();
    double calculateTVAByDate(String data);

    Incasari updaterows(Incasari incasari);

    double calculateSumaTotala();
    double calculateSumaFaraTVA();



}