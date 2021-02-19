package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Incasari;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface IncasariService {


    //    @Override
    //    public List<Incasari> deleteByNumar(int numar) {
    //        List<Incasari> deleteNumar = incasariRepository.findAllByNumar(numar);
    //        if (deleteNumar.size() >= 1) {
    //            incasariRepository.deleteByNumar(numar);
    //        }
    //        return deleteNumar;
    //
    //    }

    //    @Override
    //    public List<Incasari> deleteByNumar(int numar) {
    //        List<Incasari> deleteNumar = incasariRepository.findAllByNumar(numar);
    //        if (deleteNumar.size() >= 1) {
    //            incasariRepository.deleteByNumar(numar);
    //        }
    //        return deleteNumar;
    //
    //    }



    Incasari saveIncasari(Incasari incasari);




    List<Incasari> searchAll();
    List<Incasari> searchByData(String data);
    List<Incasari> searchByFurnizor(String furnizor);



    List<Incasari> searchByNumber(String number);
    List<Incasari> searchByMonthAndYear(String month, String year);
    List<Incasari> searchByBetweenData(String data1, String data2);
    List<Incasari> searchByBetweenSumaTotala(String data1, String data2, double sumaTotala1, double sumaTotala2);




    List<Incasari> searchWithoutDates(String furnizor, double sumaTotala1, double SumaTotala2);
    List<Incasari> searchWithoutFurnizor(String firstDate, String secondDate, double totalSum, double totalSumTwo);
    List<Incasari> searchWithoutSum(String furnizor, String data1, String data2);
    List<Incasari> searchWithoutFurnizorAndSum(String data1, String data2);
    List<Incasari> searchWithoutFurnizorAndDates(double sumaTotala1, double sumaTotala2);
    List<Incasari> searchAllParams(String furnizor, String data1, String data2, Double sumaTotala1, Double sumaTotala2);


// SUMATOTALA2

    List<Incasari> searchWithoutsumaTotala2(String furnizor, String data1, String data2, double sumaTotala1);
    List<Incasari> searchWithoutDatesAndSum2(String furnizor, double sumaTotala1);
    List<Incasari> searchWithoutFurnizorAndSum2(String data1, String data2, double sumaTotala1);
    List<Incasari> searchWithoutFunrizorAndDatesAndSum2(double sumaTotala1);

// SUMATOTALA1
    List<Incasari> searchWithoutsumaTotala1(String furnizor, String data1, String data2, double sumaTotala2);
    List<Incasari> searchWithoutDatesAndSum1(String furnizor, double sumaTotala2);
    List<Incasari> searchWithoutFurnizorAndSum1(String data1, String data2, double sumaTotala2);
    List<Incasari> searchWithoutFunrizorAndDatesAndSum1(double sumaTotala2);


// DATA1
    List<Incasari> searchWithoutData1(String furnizor, String data2, double sumaTotala1, double sumaTotala2);
    List<Incasari> searchWithoutData1AndSum1(String furnizor, String data2, double sumaTotala2);
    List<Incasari> searchWithoutData1AndSum2(String furnizor, String data2, double sumaTotala1);
    List<Incasari> searchWithoutFurnizorAndData1(String data2, double sumaTotala1, double sumaTotala2);
    List<Incasari> searchWithoutFurnizorAndData1AndSum1(String data2, double sumaTotala2);
    List<Incasari> searchWithoutFurnizorAndData1AndSum2(String data2, double sumaTotala);
    List<Incasari> searchWithoutFurnizorAndData1AndSums(String data2);

//DATA2
    List<Incasari> searchWithoutData2(String furnizor, String data1, double sumaTotala1, double sumaTotala2);
    List<Incasari> searchWithoutData2AndSum1(String furnizor, String data1, double sumaTotala2);
    List<Incasari> searchWithoutData2AndSum2(String furnizor, String data1, double sumaTotala1);
    List<Incasari> searchWithoutFurnizorAndData2(String data1, double sumaTotala1, double sumaTotala2);
    List<Incasari> searchWithoutFurnizorAndData2AndSum1(String data1, double sumaTotala2);
    List<Incasari> searchWithoutFurnizorAndData2AndSum2(String data1, double sumaTotala);
    List<Incasari> searchWithoutFurnizorAndData2AndSums(String data1);



    Incasari update(String number, Incasari incasari);
    void deleteNumber(String number);

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