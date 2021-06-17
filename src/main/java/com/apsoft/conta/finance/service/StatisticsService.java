package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Incasari;

import java.text.ParseException;
import java.util.List;

public interface StatisticsService {

    double calculareSumaTotalaCuTVA_Incasari();
    double calculareSumaTotalaCuTVA_Cheltuieli();

    double calculareSalariuNetTotal_Salariu();

    double calculareSumaTotalaFaraTVA_Incasari();
    double calculareSumaTotalaFaraTVA_Cheltuieli();

    double calculareSumaTotalaTVA_Incasari();
    double calculareSumaTotalaTVA_Cheltuieli();

    double calculareSumaTotalaCuTVADataMinDataMax_Incasari(String data1, String data2);
    double calculareSumaTotalaCuTVADataMinDataMax_Cheltuieli(String data1, String data2);

    double calculareSumaTotalaFaraTVADataMinDataMax_Incasari(String data1, String data2);
    double calculareSumaTotalaFaraTVADataMinDataMax_Cheltuieli(String data1, String data2);

    double calculareSumaTotalaTVADataMinDataMax_Incasari(String data1, String data2);
    double calculareSumaTotalaTVADataMinDataMax_Cheltuieli(String data1, String data2);

    double calculareSumaTotalaCuTVAMonthAndYear_Incasari(String month, String year);
    double calculareSumaTotalaCuTVAMonthAndYear_Cheltuieli(String month, String year);

    double calculareSalariuNetTotalMonthAndYear_Salariu(String month, String year);

    double calculareSumaTotalaFaraTVAMonthAndYear_Incasari(String month, String year);
    double calculareSumaTotalaFaraTVAMonthAndYear_Cheltuieli(String month, String year);

    double calculareSumaTotalaTVAMonthAndYear_Incasari(String month, String year);
    double calculareSumaTotalaTVAMonthAndYear_Cheltuieli(String month, String year);

    double calculareSumaTotalaCuTVAPerYear_Incasari(String year);
    double calculareSumaTotalaCuTVAPerYear_Cheltuieli(String year);

    double calculareSalariuNetTotalPerYear_Salariu(String year);

    double calculareSumaTotalaFaraTVAPerYear_Incasari(String year);
    double calculareSumaTotalaFaraTVAPerYear_Cheltuieli(String year);

    double calculareSumaTotalaTVAPerYear_Incasari(String year);
    double calculareSumaTotalaTVAPerYear_Cheltuieli(String year);




    double calculareSumaTVAMonthAndYear_LaIncasare_Incasari(String month, String year);

    double Profit_Total();
    double Profit_Lunar(String month, String year);
    double Profit_Anual(String year);


    double calculareSumaTotalaCuTVAPerStare_Incasari(String stare);

    double calculareSumaTotalaCuTVAPerStare_Cheltuieli(String stare);

    double calculareSumaTotalaCuTVAByStare_Incasari_Achitata(String stare);

    double calculareSumaTotalaCuTVAPerStare_Cheltuieli_Achitata(String stare);

    double calculareSalariuNetTotalPerStare_Salariu_Achitat(String stare);

    double sold();


    double CifraAfaceri(String year);

    long Incasari_Intarziate();

    long Cheltuieli_Intarziate();

    long Salarii_Intarziate();

    double Incasari_Intarziate_Rest_DeIncasat();

    double Cheltuieli_Intarziate_Rest_DeAchitat();

    double Salarii_Intarziate_Rest_DeAchitat();

    double BugetulDeStat_TVA(String month, String year);

    double BugetulDeStat_TVAIncasare(String month, String year);
}
