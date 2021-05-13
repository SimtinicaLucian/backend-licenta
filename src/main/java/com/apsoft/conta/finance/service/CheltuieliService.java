package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Incasari;

import java.text.ParseException;
import java.util.List;

public interface CheltuieliService {

    Cheltuieli saveCheltuieli(Cheltuieli cheltuieli) throws ParseException;

    void deleteId(long id);

    List<Cheltuieli> searchById(long id);

    double calculareSumaTotalaCuTVA();

    double calculareSumaTVA();

    double calculareSumaFaraTVA();

    double rest();

    double culculareSumaTotalaCuTVADataMinDataMax(String data1, String data2);

    double calculareSumaTotalaCuTVAMonthYear(String month, String year);

    List<Cheltuieli> searchAll();


    Cheltuieli update(long id, Cheltuieli cheltuieli) throws ParseException;

    List<Cheltuieli> searchWithoutFunrizorAndDatesAndSum2AndStare(double sumaTotala1);

    List<Cheltuieli> searchWithoutFunrizorAndDatesAndSum1AndStare(double sumaTotala2);

    List<Cheltuieli> searchWithoutFurnizorAndData1AndSumsAndStare(String data2);

    List<Cheltuieli> searchWithoutFurnizorAndDatesAndSums(String stare);

    List<Cheltuieli> searchWithoutFurnizorAndData2AndSumsAndStare(String data1);

    List<Cheltuieli> searchWithoutFunrizorAndDatesAndSum2(double sumaTotala1, String stare);

    List<Cheltuieli> searchWithoutFunrizorAndDatesAndSum1(double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutFurnizorAndData1AndSums(String data2, String stare);

    List<Cheltuieli> searchWithoutFurnizorAndData2AndSums(String data1, String stare);

    List<Cheltuieli> searchWithoutFurnizorAndData1AndSum2AndStare(String data2, double sumaTotala1);

    List<Cheltuieli> searchWithoutFurnizorAndData2AndSum2AndStare(String data1, double sumaTotala1);

    List<Cheltuieli> searchWithoutFurnizorAndData1AndSum1AndStare(String data2, double sumaTotala2);

    List<Cheltuieli> searchWithoutFurnizorAndData2AndSum1AndStare(String data1, double sumaTotala2);

    List<Cheltuieli> searchWithoutFurnizorAndDatesAndStare(double sumaTotala1, double sumaTotala2);

    List<Cheltuieli> searchWithoutData2AndSumsAndStare(String beneficiar, String data1);

    List<Cheltuieli> searchWithoutData1AndSumsAndStare(String beneficiar, String data2);

    List<Cheltuieli> searchWithoutDatesAndSum2AndStare(String beneficiar, double sumaTotala1);

    List<Cheltuieli> searchWithoutDatesAndSum1AndStare(String beneficiar, double sumaTotala2);

    List<Cheltuieli> searchWithoutFurnizorAndSumAndStare(String data1, String data2);

    List<Cheltuieli> searchWithoutDatesAndSums(String beneficiar, String stare);

    List<Cheltuieli> searchWithoutFurnizorAndData1AndSum2(String data2, double sumaTotala1, String stare);

    List<Cheltuieli> searchWithoutFurnizorAndData2AndSum2(String data1, double sumaTotala1, String stare);

    List<Cheltuieli> searchWithoutFurnizorAndData1AndSum1(String data2, double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutFurnizorAndData2AndSum1(String data1, double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutFurnizorAndDates(double sumaTotala1, double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutData2AndSums(String beneficiar, String data1, String stare);

    List<Cheltuieli> searchWithoutData1AndSums(String beneficiar, String data2, String stare);

    List<Cheltuieli> searchWithoutDatesAndSum2(String beneficiar, double sumaTotala1, String stare);

    List<Cheltuieli> searchWithoutDatesAndSum1(String beneficiar, double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutFurnizorAndSum(String data1, String data2, String stare);

    List<Cheltuieli> searchWithoutFurnizorAndData1AndStare(String data2, double sumaTotala1, double sumaTotala2);

    List<Cheltuieli> searchWithoutFurnizorAndData2AndStare(String data1, double sumaTotala1, double sumaTotala2);

    List<Cheltuieli> searchWithoutData1AndSum2AndStare(String beneficiar, String data2, double sumaTotala1);

    List<Cheltuieli> searchWithoutData2AndSum2AndStare(String beneficiar, String data1, double sumaTotala1);

    List<Cheltuieli> searchWithoutData1AndSum1AndStare(String beneficiar, String data2, double sumaTotala2);

    List<Cheltuieli> searchWithoutData2AndSum1AndStare(String beneficiar, String data1, double sumaTotala2);

    List<Cheltuieli> searchWithoutDatesAndStare(String beneficiar, double sumaTotala1, double SumaTotala2);

    List<Cheltuieli> searchWithoutFurnizorAndSum2AndStare(String data1, String data2, double sumaTotala1);

    List<Cheltuieli> searchWithoutFurnizorAndSum1AndStare(String data1, String data2, double sumaTotala2);

    List<Cheltuieli> searchWithoutSumAndStare(String beneficiar, String data1, String data2);

    List<Cheltuieli> searchWithoutFurnizorAndStare(String data1, String data2, double sumaTotala1, double sumaTotala2);

    List<Cheltuieli> searchWithoutFurnizorAndData1(String data2, double sumaTotala1, double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutFurnizorAndData2(String data1, double sumaTotala1, double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutData1AndSum2(String beneficiar, String data2, double sumaTotala1, String stare);

    List<Cheltuieli> searchWithoutData2AndSum2(String beneficiar, String data1, double sumaTotala1, String stare);

    List<Cheltuieli> searchWithoutData1AndSum1(String beneficiar, String data2, double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutData2AndSum1(String beneficiar, String data1, double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutDates(String beneficiar, double sumaTotala1, double SumaTotala2, String stare);

    List<Cheltuieli> searchWithoutFurnizorAndSum2(String data1, String data2, double sumaTotala1, String stare);

    List<Cheltuieli> searchWithoutFurnizorAndSum1(String data1, String data2, double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutFurnizor(String data1, String data2, double sumaTotala1, double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutSum(String beneficiar, String data1, String data2, String stare);

    List<Cheltuieli> searchWithoutsumaTotala2AndStare(String beneficiar, String data1, String data2, double sumaTotala1);

    List<Cheltuieli> searchWithoutsumaTotala1AndStare(String beneficiar, String data1, String data2, double sumaTotala2);

    List<Cheltuieli> searchWithoutData1AndStare(String beneficiar, String data2, double sumaTotala1, double sumaTotala2);

    List<Cheltuieli> searchWithoutData2AndStare(String beneficiar, String data1, double sumaTotala1, double sumaTotala2);

    List<Cheltuieli> searchWithoutsumaTotala2(String beneficiar, String data1, String data2, double sumaTotala1, String stare);

    List<Cheltuieli> searchWithoutsumaTotala1(String beneficiar, String data1, String data2, double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutData1(String beneficiar, String data2, double sumaTotala1, double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutData2(String beneficiar, String data1, double sumaTotala1, double sumaTotala2, String stare);

    List<Cheltuieli> searchWithoutStare(String beneficiar, String data1, String data2, Double sumaTotala1, Double sumaTotala2);

    List<Cheltuieli> searchAllParams(String beneficiar, String data1, String data2, Double sumaTotala1, Double sumaTotala2, String stare);
}
