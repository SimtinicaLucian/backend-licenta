package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.repository.IncasariRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

import java.util.List;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class IncasariServiceImpl implements IncasariService {


    @Autowired
    private IncasariRepository incasariRepository;


//    @Override
//    public Incasari saveIncasari(Incasari incasari) {
//
//        return incasariRepository.save(incasari);
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

    @Override
    public Incasari saveIncasari(Incasari incasari) {
        List<Incasari> incasariSearch = incasariRepository.findAllByNumber(incasari.getNumber());

        incasari.setMonth(incasari.getData().substring(5, 7));
        incasari.setYear(incasari.getData().substring(0, 4));


        incasari.setData1(incasari.getData().substring(0, 10));
        incasari.setData2(incasari.getData().substring(0, 10));

        incasari.setSumaTotala1(incasari.getSumaTotala());
        incasari.setSumaTotala2(incasari.getSumaTotala());


        if (incasariSearch.size() < 1) {
            incasari = incasariRepository.save(incasari);
        }
        log.info("The {} has been added to the database", incasari.getDetalii());
        return incasari;
    }


    @Override
    public List<Incasari> searchAll() {
        log.info("Return all incasari");
        return incasariRepository.findAll();
    }


    @Override
    public List<Incasari> searchByData(String data) {
        return incasariRepository.findAllByData(data);
    }

    @Override
    public List<Incasari> searchByFurnizor(String furnizor) {

        return incasariRepository.findAllByFurnizor(furnizor);
    }

    @Override
    public List<Incasari> searchByMonthAndYear(String month, String year) {
        return incasariRepository.findAllByMonthAndYear(month, year);
    }

    @Override
    public List<Incasari> searchByBetweenData(String data1, String data2) {
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqual(data1, data2);
    }

    @Override
    public List<Incasari> searchByBetweenSumaTotala(String data1, String data2, double sumaTotala1, double sumaTotala2) {
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(data1, data2, sumaTotala1, sumaTotala2);
    }

//    @Override
//    public List<Incasari> searchByFurnizorAndBetweenSumaTotala(String furnizor,String data1, String data2, double sumaTotala1, double sumaTotala2) {
//        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(furnizor,data1, data2, sumaTotala1, sumaTotala2);
//    }


    @Override
    public List<Incasari> searchWithoutDates(String furnizor, double sumaTotala1, double SumaTotala2){
        log.info("without data1, data2");
        return incasariRepository.findAllByFurnizorAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(furnizor,sumaTotala1,SumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizor(String firstDate, String secondDate, double totalSum, double totalSumTwo){
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(firstDate, secondDate, totalSum, totalSumTwo);
    }

    @Override
    public List<Incasari> searchWithoutSum(String furnizor, String data1, String data2){
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqual(furnizor,data1,data2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndSum(String data1, String data2) {
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqual(data1, data2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndDates(double sumaTotala1, double sumaTotala2) {
        return incasariRepository.findAllBySumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(sumaTotala1, sumaTotala2);
    }



// SUMATOTALA2


    @Override
    public List<Incasari> searchWithoutsumaTotala2(String furnizor, String data1, String data2, double sumaTotala1) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(furnizor, data1, data2, sumaTotala1);
    }


    @Override
    public List<Incasari> searchWithoutDatesAndSum2(String furnizor, double sumaTotala1) {
        return incasariRepository.findAllByFurnizorAndSumaTotala1GreaterThanEqual(furnizor, sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndSum2(String data1, String data2, double sumaTotala1) {
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(data1, data2, sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutFunrizorAndDatesAndSum2(double sumaTotala1) {
        return incasariRepository.findAllBySumaTotala1GreaterThanEqual(sumaTotala1);
    }


// SUMATOTALA1
    @Override
    public List<Incasari> searchWithoutsumaTotala1(String furnizor, String data1, String data2, double sumaTotala2) {
         return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqual(furnizor, data1, data2, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutDatesAndSum1(String furnizor, double sumaTotala2) {
        return incasariRepository.findAllByFurnizorAndSumaTotala2LessThanEqual(furnizor, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndSum1(String data1, String data2, double sumaTotala2) {
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqual(data1, data2, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFunrizorAndDatesAndSum1(double sumaTotala2) {
        return incasariRepository.findAllBySumaTotala2LessThanEqual(sumaTotala2);
    }

// WITHOUT DATA1
    @Override
    public List<Incasari> searchWithoutData1(String furnizor, String data2, double sumaTotala1, double sumaTotala2) {
        return incasariRepository.findAllByFurnizorAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(furnizor, data2, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutData1AndSum1(String furnizor, String data2, double sumaTotala2) {
        return incasariRepository.findAllByFurnizorAndData2LessThanEqualAndSumaTotala2LessThanEqual(furnizor, data2, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutData1AndSum2(String furnizor, String data2, double sumaTotala1) {
        return incasariRepository.findAllByFurnizorAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(furnizor, data2, sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData1(String data2, double sumaTotala1, double sumaTotala2) {
        return incasariRepository.findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(data2, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData1AndSum1(String data2, double sumaTotala2) {
        return incasariRepository.findAllByData2LessThanEqualAndSumaTotala2LessThanEqual(data2, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData1AndSum2(String data2, double sumaTotala1) {
        return incasariRepository.findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqual(data2, sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData1AndSums(String data2) {
        return incasariRepository.findAllByData2LessThanEqual(data2);
    }


//WITHOUT DATA2
    @Override
    public List<Incasari> searchWithoutData2(String furnizor, String data1, double sumaTotala1, double sumaTotala2) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(furnizor, data1, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutData2AndSum1(String furnizor, String data1, double sumaTotala2) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala2LessThanEqual(furnizor, data1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutData2AndSum2(String furnizor, String data1, double sumaTotala1) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqual(furnizor, data1, sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData2(String data1, double sumaTotala1, double sumaTotala2) {
        return incasariRepository.findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(data1, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData2AndSum1(String data1, double sumaTotala2) {
        return incasariRepository.findAllByData1GreaterThanEqualAndSumaTotala2LessThanEqual(data1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData2AndSum2(String data1, double sumaTotala1) {
        return incasariRepository.findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqual(data1, sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData2AndSums(String data1) {
        return incasariRepository.findAllByData1GreaterThanEqual(data1);
    }










    @Override
    public List<Incasari> searchAllParams(String furnizor, String data1, String data2, Double sumaTotala1, Double sumaTotala2) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(furnizor,data1,data2,sumaTotala1,sumaTotala2);
    }


    @Override
    public List<Incasari> searchByNumber(String number) {

        return incasariRepository.findAllByNumber(number);
    }


    @Override
    public Incasari update(String id, Incasari incasari) {
        List<Incasari> numberSearch = incasariRepository.findAllById(id);

            Incasari search = numberSearch.get(0);
//        Incasari search = new Incasari();
            search.setData(incasari.getData());
            search.setData1(incasari.getData());
            search.setData2(incasari.getData());
            search.setFurnizor(incasari.getFurnizor());
            search.setNumber(incasari.getNumber());
            search.setDetalii(incasari.getDetalii());
            search.setSumaTotala(incasari.getSumaTotala());
            search.setSumaFaraTVA(incasari.getSumaFaraTVA());
            search.setSumaTVA(incasari.getSumaTVA());

//        Incasari search = new Incasari.IncasariBilder()
//                .data(incasari.getData())
//                .furnizor(incasari.getFurnizor())
//                .number(incasari.getNumber())
//                .detalii(incasari.getDetalii())
//                .sumaTotala(incasari.getSumaTotala())
//                .sumaFaraTVA(incasari.getSumaFaraTVA())
//                .sumaTVA(incasari.getSumaTotala())
//                .build();
//            incasariRepository.deleteById(id);
            search = incasariRepository.save(search);

            log.info("Update");
        return search;


    }


    @Override
    public void deleteNumber(String number) {
        log.info("delete");
        incasariRepository.deleteByNumber(number);
    }


    public double calculateTVAByDate(String data) {
        List<Incasari> incasariListByDate = incasariRepository.findAllByData(data);
        return incasariListByDate.stream().map(Incasari::getSumaTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculateSumaTotala() {
        List<Incasari> incasariList = incasariRepository.findAll();
//        log.info("Calculare suma totala");
        return incasariList.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }

    public double calculateSumaFaraTVA() {
        List<Incasari> incasariList = incasariRepository.findAll();
//        return incasariList.stream().map(Incasari::getSumaFaraTVA).reduce(0.0, Double::sum);
//        log.info("Calculare suma totala fara TVA");
        return calculateSumaTotala() - calculateTotalTVA();
    }

    @Override
    public double calculateTotalByMonth(String month) {
        List<Incasari> incasariListByDate = incasariRepository.findByMonth(month);
        log.info("Calculare");
        return incasariListByDate.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculateTotalByYear(String year) {
        List<Incasari> incasariListByDate = incasariRepository.findAByYear(year);
        log.info("Calculate by year");
        return incasariListByDate.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public List<Incasari> searchByYear(String year) {
        return incasariRepository.findAByYear(year);
    }


    @Override
    public double calculateTotalByMonthAndYear(String month, String year) {
        List<Incasari> incasariListByMonthAndYear = incasariRepository.findAllByMonthAndYear(month, year);
        log.info("Month and year");
        return incasariListByMonthAndYear.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculateTotalByDataBetweenData(String data1, String data2) {

        List<Incasari> incasariListByDataBetweenData = incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqual(data1, data2);
        log.info("Between");

        return incasariListByDataBetweenData.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }


    public double calculateTotalTVA() {
        List<Incasari> incasariList = incasariRepository.findAll();
//        log.info("Calculare suma totala  TVA");
        return incasariList.stream().map(Incasari::getSumaTVA).reduce(0.0, Double::sum);
    }

    @Override
    public Incasari updaterows(Incasari incasari) {
        List<Incasari> Search = incasariRepository.findAll();
        Incasari search = new Incasari.IncasariBilder()
                .data(incasari.getData())
                .furnizor(incasari.getFurnizor())
                .number(incasari.getNumber())
                .detalii(incasari.getDetalii())
                .sumaTotala(incasari.getSumaTotala())
                .sumaFaraTVA(incasari.getSumaFaraTVA())
                .sumaTVA(incasari.getSumaTotala())
                .build();


        return incasariRepository.save(search);


    }
}