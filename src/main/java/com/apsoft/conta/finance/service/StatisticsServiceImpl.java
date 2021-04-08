package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.repository.CheltuieliRepository;
import com.apsoft.conta.finance.repository.IncasariRepository;
import com.apsoft.conta.finance.repository.StatisticsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StatisticsServiceImpl implements StatisticsService{

    @Autowired
    private CheltuieliRepository cheltuieliRepository;

    @Autowired
    private IncasariRepository incasariRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private IncasariServiceImpl incasariServiceImpl;

    @Autowired
    private CheltuieliServiceImpl cheltuieliServiceImpl;

    @Override
    public double calculareSumaTotalaCuTVA_Incasari(){
        log.info("Calculare suma cu TVA - Incasari");
        List<Incasari> incasariList = incasariRepository.findAll();
        return incasariList.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaCuTVA_Cheltuieli(){
        log.info("Calculare suma cu TVA - Cheltuieli");
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAll();
        return cheltuieliList.stream().map(Cheltuieli::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaFaraTVA_Incasari(){
        log.info("Calculare suma Totala fara TVA - Incasari");
        List<Incasari> incasariList = incasariRepository.findAll();
        return incasariList.stream().map(Incasari::getSumaFaraTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaFaraTVA_Cheltuieli(){
        log.info("Calculare suma Totala fara TVA - Cheltuieli");
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAll();
        return cheltuieliList.stream().map(Cheltuieli::getSumaFaraTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaTVA_Incasari(){
        log.info("Calculare suma Totala TVA - Incasari");
        List<Incasari> incasariList = incasariRepository.findAll();
        return incasariList.stream().map(Incasari::getSumaTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaTVA_Cheltuieli(){
        log.info("Calculare suma Totala TVA - Cheltuieli");
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAll();
        return cheltuieliList.stream().map(Cheltuieli::getSumaTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaCuTVADataMinDataMax_Incasari(String data1, String data2) {
        List<Incasari> incasariList = incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqual(data1, data2);
        log.info("Calculare suma totala cu TVA DataMin DataMax_Incasari");
        return incasariList.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaCuTVADataMinDataMax_Cheltuieli(String data1, String data2) {
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByData1GreaterThanEqualAndData2LessThanEqual(data1, data2);
        log.info("Calculare suma totala cu TVA DataMin DataMax_Cheltuieli");
        return cheltuieliList.stream().map(Cheltuieli::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaFaraTVADataMinDataMax_Incasari(String data1, String data2) {
        List<Incasari> incasariList = incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqual(data1, data2);
        log.info("Calculare suma totala Fara TVA DataMin DataMax_Incasari");
        return incasariList.stream().map(Incasari::getSumaFaraTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaFaraTVADataMinDataMax_Cheltuieli(String data1, String data2) {
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByData1GreaterThanEqualAndData2LessThanEqual(data1, data2);
        log.info("Calculare suma totala Fara TVA DataMin DataMax_Cheltuieli");
        return cheltuieliList.stream().map(Cheltuieli::getSumaFaraTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaTVADataMinDataMax_Incasari(String data1, String data2) {
        List<Incasari> incasariList = incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqual(data1, data2);
        log.info("Calculare suma totala  TVA DataMin DataMax_Incasari");
        return incasariList.stream().map(Incasari::getSumaTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaTVADataMinDataMax_Cheltuieli(String data1, String data2) {
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByData1GreaterThanEqualAndData2LessThanEqual(data1, data2);
        log.info("Calculare suma totala TVA DataMin DataMax_Cheltuieli");
        return cheltuieliList.stream().map(Cheltuieli::getSumaTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaCuTVAMonthAndYear_Incasari(String month, String year){
        List<Incasari> incasariList = incasariRepository.findAllByMonthAndYear(month, year);
        log.info("Calculare suma Totala cu TVA MonthAndYear_Incasari");
        return incasariList.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaCuTVAMonthAndYear_Cheltuieli(String month, String year){
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByMonthAndYear(month, year);
        log.info("Calculare suma Totala cu TVA MonthAndYear_Cheltuieli");
        return cheltuieliList.stream().map(Cheltuieli::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaFaraTVAMonthAndYear_Incasari(String month, String year){
        List<Incasari> incasariList = incasariRepository.findAllByMonthAndYear(month, year);
        log.info("Calculare suma Totala Fara TVA MonthAndYear_Incasari");
        return incasariList.stream().map(Incasari::getSumaFaraTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaFaraTVAMonthAndYear_Cheltuieli(String month, String year){
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByMonthAndYear(month, year);
        log.info("Calculare suma Totala Fara TVA MonthAndYear_Incasari");
        return cheltuieliList.stream().map(Cheltuieli::getSumaFaraTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaTVAMonthAndYear_Incasari(String month, String year){
        List<Incasari> incasariList = incasariRepository.findAllByMonthAndYear(month, year);
        log.info("Calculare suma Totala TVA MonthAndYear_Incasari");
        return incasariList.stream().map(Incasari::getSumaTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaTVAMonthAndYear_Cheltuieli(String month, String year){
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByMonthAndYear(month, year);
        log.info("Calculare suma Totala TVA MonthAndYear_Incasari");
        return cheltuieliList.stream().map(Cheltuieli::getSumaTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaCuTVAPerYear_Incasari(String year) {
        List<Incasari> incasariList = incasariRepository.findAllByYear(year);
        log.info("Calculare suma Totala cu TVA per Year_Incasari");
        return incasariList.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaCuTVAPerYear_Cheltuieli(String year) {
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByYear(year);
        log.info("Calculare suma Totala cu TVA per Year_Cheltuieli");
        return cheltuieliList.stream().map(Cheltuieli::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaFaraTVAPerYear_Incasari(String year) {
        List<Incasari> incasariList = incasariRepository.findAllByYear(year);
        log.info("Calculare suma Totala Fara TVA per Year_Incasari");
        return incasariList.stream().map(Incasari::getSumaFaraTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaFaraTVAPerYear_Cheltuieli(String year) {
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByYear(year);
        log.info("Calculare suma Totala Fara TVA per Year_Cheltuieli");
        return cheltuieliList.stream().map(Cheltuieli::getSumaFaraTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaTVAPerYear_Incasari(String year) {
        List<Incasari> incasariList = incasariRepository.findAllByYear(year);
        log.info("Calculare suma Totala TVA per Year_Incasari");
        return incasariList.stream().map(Incasari::getSumaTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaTVAPerYear_Cheltuieli(String year) {
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByYear(year);
        log.info("Calculare suma Totala TVA per Year_Cheltuieli");
        return cheltuieliList.stream().map(Cheltuieli::getSumaTVA).reduce(0.0, Double::sum);
    }


    @Override
    public double Profit_Total(){
        log.info("Calculare profit_total");
        return calculareSumaTotalaCuTVA_Incasari() - calculareSumaTotalaCuTVA_Cheltuieli();
    }

    @Override
    public double Profit_Lunar(String month, String year){
        log.info("Calculare profit_lunar");
        return calculareSumaTotalaCuTVAMonthAndYear_Incasari(month,year) - calculareSumaTotalaCuTVAMonthAndYear_Cheltuieli(month, year);
    }

    @Override
    public double Profit_Anual(String year){
        log.info("Calculare profit anual");
        return calculareSumaTotalaCuTVAPerYear_Incasari(year) - calculareSumaTotalaCuTVAPerYear_Cheltuieli(year);
    }


    @Override
    public double calculareSumaTotalaCuTVAPerStare_Incasari(String stare) {
        log.info("Calculare sumaTotalaPerStare - Incasari");
        List<Incasari> incasariList = incasariRepository.findAllByStare(stare);
        return incasariList.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaCuTVAPerStare_Cheltuieli(String stare){
        log.info("Calculare sumaTotalaPerStare - Cheltuieli");
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByStare(stare);
        return cheltuieliList.stream().map(Cheltuieli::getSumaTotala).reduce(0.0, Double::sum);
    }


    @Override
    public double sold(){
        double sold = 0;
        log.info("Sold");
        return sold + calculareSumaTotalaCuTVAPerStare_Incasari("achitat") - calculareSumaTotalaCuTVAPerStare_Cheltuieli("achitat");
    }


}

