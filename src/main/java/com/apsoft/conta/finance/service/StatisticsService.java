package com.apsoft.conta.finance.service;

public interface StatisticsService {

    double calculareSumaTotalaCuTVA_Incasari();
    double calculareSumaTotalaCuTVA_Cheltuieli();

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

    double calculareSumaTotalaFaraTVAMonthAndYear_Incasari(String month, String year);
    double calculareSumaTotalaFaraTVAMonthAndYear_Cheltuieli(String month, String year);

    double calculareSumaTotalaTVAMonthAndYear_Incasari(String month, String year);
    double calculareSumaTotalaTVAMonthAndYear_Cheltuieli(String month, String year);

    double calculareSumaTotalaCuTVAPerYear_Incasari(String year);
    double calculareSumaTotalaCuTVAPerYear_Cheltuieli(String year);

    double calculareSumaTotalaFaraTVAPerYear_Incasari(String year);
    double calculareSumaTotalaFaraTVAPerYear_Cheltuieli(String year);

    double calculareSumaTotalaTVAPerYear_Incasari(String year);
    double calculareSumaTotalaTVAPerYear_Cheltuieli(String year);

    double Profit_Total();
    double Profit_Lunar(String month, String year);
    double Profit_Anual(String year);


    double calculareSumaTotalaCuTVAPerStare_Incasari(String stare);

    double calculareSumaTotalaCuTVAPerStare_Cheltuieli(String stare);

    double sold();
}
