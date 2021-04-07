package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Incasari;

import java.util.List;

public interface CheltuieliService {

    Cheltuieli saveCheltuieli(Cheltuieli cheltuieli);

    void deleteId(long id);

    double calculareSumaTotalaCuTVA();

    double calculareSumaTVA();

    double calculareSumaFaraTVA();

    double rest();

    double culculareSumaTotalaCuTVADataMinDataMax(String data1, String data2);

    double calculareSumaTotalaCuTVAMonthYear(String month, String year);

    List<Cheltuieli> searchAll();




}
