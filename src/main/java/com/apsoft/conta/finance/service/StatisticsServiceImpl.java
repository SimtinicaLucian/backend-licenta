package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.persistence.Salariu;
import com.apsoft.conta.finance.repository.CheltuieliRepository;
import com.apsoft.conta.finance.repository.IncasariRepository;
import com.apsoft.conta.finance.repository.SalariuRepository;
import com.apsoft.conta.finance.repository.StatisticsRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.List;


@Slf4j
@Service
public class StatisticsServiceImpl implements StatisticsService{

    @Autowired
    private CheltuieliRepository cheltuieliRepository;

    @Autowired
    private IncasariRepository incasariRepository;

    @Autowired
    private SalariuRepository salariuRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;



    @Autowired
    private IncasariServiceImpl incasariServiceImpl;

    @Autowired
    private CheltuieliServiceImpl cheltuieliServiceImpl;

    DecimalFormat numberFormat = new DecimalFormat("#.##");

    String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

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
    public double calculareSalariuNetTotal_Salariu(){
        log.info("Calculare salariu net total - Salariu");
        List<Salariu> salariuList = salariuRepository.findAll();
        return salariuList.stream().map(Salariu::getSalariu_net).reduce(0.0, Double::sum);
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
    public double calculareSalariuNetTotalMonthAndYear_Salariu(String month, String year){
        List<Salariu> salariuList = salariuRepository.findAllByMonthAndYear(month, year);
        log.info("Calculare salariu net total MonthAndYear_Salariu");
        return salariuList.stream().map(Salariu::getSalariu_net).reduce(0.0, Double::sum);
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
    public double calculareSalariuNetTotalPerYear_Salariu(String year) {
        List<Salariu> salariuList = salariuRepository.findAllByYear(year);
        log.info("Calculare salariu net total per Year_Cheltuieli");
        return salariuList.stream().map(Salariu::getSalariu_net).reduce(0.0, Double::sum);
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
    public double calculareSumaTVAMonthAndYear_LaIncasare_Incasari(String month, String year){
        List<Incasari> incasariList = incasariRepository.findAllByMonthAndYear(month, year);
        return incasariList.stream().map(Incasari::getSumaTVA_Incasata).reduce(0.0, Double :: sum);
    }


    @Override
    public double Profit_Total(){
        log.info("Calculare profit_total");
        return Double.parseDouble(numberFormat.format(calculareSumaTotalaCuTVA_Incasari() -
                calculareSumaTotalaCuTVA_Cheltuieli() - calculareSalariuNetTotal_Salariu()));
    }

    @Override
    public double Profit_Lunar(String month, String year){
        log.info("Calculare profit_lunar");
        return Double.parseDouble(numberFormat.format(calculareSumaTotalaCuTVAMonthAndYear_Incasari(month,year) -
                calculareSumaTotalaCuTVAMonthAndYear_Cheltuieli(month, year) - calculareSalariuNetTotalMonthAndYear_Salariu(month, year)));
    }

    @Override
    public double Profit_Anual(String year){
        log.info("Calculare profit anual");
        return Double.parseDouble(numberFormat.format(calculareSumaTotalaCuTVAPerYear_Incasari(year) -
                calculareSumaTotalaCuTVAPerYear_Cheltuieli(year) - calculareSalariuNetTotalPerYear_Salariu(year)));
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
    public double calculareSumaTotalaCuTVAByStare_Incasari_Achitata(String stare) {
        log.info("Calculare sumaTotalaPerStare - Incasate");
        List<Incasari> incasariList = incasariRepository.findAllByStare(stare);
        return incasariList.stream().map(Incasari::getSumaTotala_Incasata).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaCuTVAPerStare_Cheltuieli_Achitata(String stare){
        log.info("Calculare sumaTotalaPerStare - Achitata");
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByStare(stare);
        return cheltuieliList.stream().map(Cheltuieli::getSumaTotala_Achitata).reduce(0.0, Double::sum);
    }


    @Override
    public double calculareSalariuNetTotalPerStare_Salariu_Achitat(String stare){
        log.info("Calculare salariu net _ total per stare");
        List<Salariu> salariuList = salariuRepository.findAllByStare(stare);
        return salariuList.stream().map(Salariu::getSalariu_net_achitat).reduce(0.0, Double::sum);
    }


    @Override
    public double sold()  {
        double sold = 0;
        log.info("Sold");


//        double sumaTVA = incasari.getSumaTotala() * 19/119;
//        return Double.parseDouble(numberFormat.format(sumaTVA));

        return Double.parseDouble(numberFormat.format(sold + calculareSumaTotalaCuTVAPerStare_Incasari("achitata") -
                calculareSumaTotalaCuTVAPerStare_Cheltuieli("achitata") - calculareSalariuNetTotalPerStare_Salariu_Achitat("achitata") +
                calculareSumaTotalaCuTVAByStare_Incasari_Achitata("partial achitata") - calculareSumaTotalaCuTVAPerStare_Cheltuieli_Achitata("partial achitata") -
                calculareSalariuNetTotalPerStare_Salariu_Achitat("partial achitata") + calculareSumaTotalaCuTVAByStare_Incasari_Achitata("intarziata")-
                calculareSumaTotalaCuTVAPerStare_Cheltuieli_Achitata("intarziata") - calculareSalariuNetTotalPerStare_Salariu_Achitat("intarziata")));
    }

    @Override
    public double CifraAfaceri(String year){
        log.info("Cifra afaceri");
        return Double.parseDouble(numberFormat.format(calculareSumaTotalaCuTVAPerYear_Incasari(year)));
    }

    @Override
    public long Incasari_Intarziate(){
        log.info("count");
        return incasariRepository.countAllByStare("intarziata");
    }

    @Override
    public long Cheltuieli_Intarziate(){
        log.info("count cheltuieli");
        return cheltuieliRepository.countAllByStare("intarziata");
    }

    @Override
    public long Salarii_Intarziate(){
        log.info("count salarii");
        return salariuRepository.countAllByStare("intarziata");
    }

    @Override
    public double Incasari_Intarziate_Rest_DeIncasat(){
        log.info("Incasari_Intarziate_Rest_DeIncasat");
        List<Incasari> incasariList = incasariRepository.findAllByStare("intarziata");
        return Double.parseDouble(numberFormat.format(incasariList.stream().map(Incasari::getRest).reduce(0.0, Double:: sum)));
    }

    @Override
    public double Cheltuieli_Intarziate_Rest_DeAchitat(){
        log.info("Cheltuieli_Intarziate_Rest_DeAchitat");
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByStare("intarziata");
        return Double.parseDouble(numberFormat.format(cheltuieliList.stream().map(Cheltuieli::getRest).reduce(0.0, Double:: sum)));
    }

    @Override
    public double Salarii_Intarziate_Rest_DeAchitat(){
        log.info("Salarii_Intarziate_Rest_DeAchitat");
        List<Salariu> salariuList = salariuRepository.findAllByStare("intarziata");
        return Double.parseDouble(numberFormat.format(salariuList.stream().map(Salariu::getRest).reduce(0.0, Double:: sum)));
    }

    @Override
    public double BugetulDeStat_TVA(String month, String year){
        log.info("BugetulDeStat_TVA");
        double Bugetul_DeStat_TVA = Double.parseDouble(numberFormat.format(calculareSumaTotalaTVAMonthAndYear_Incasari(month,year) - calculareSumaTotalaTVAMonthAndYear_Cheltuieli(month,year)));
        if(Bugetul_DeStat_TVA < 0){



        }
        return Bugetul_DeStat_TVA;
    }

    @Override
    public double BugetulDeStat_TVAIncasare(String month, String year){
        log.info("BugetulDeStat_TVA_LaIncasare");
        return Double.parseDouble(numberFormat.format(calculareSumaTVAMonthAndYear_LaIncasare_Incasari(month, year)- calculareSumaTotalaTVAMonthAndYear_Cheltuieli(month,year)));

    }









}

