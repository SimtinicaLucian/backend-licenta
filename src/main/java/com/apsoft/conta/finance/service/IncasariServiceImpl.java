package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.repository.IncasariRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        incasari.setMonth(incasari.getData().substring(3,5));
        incasari.setYear(incasari.getData().substring(6,10));
        incasari.setData1(incasari.getData().substring(0,10));
        incasari.setData2(incasari.getData().substring(0,10));


        if (incasariSearch.size() < 1) {
            incasari = incasariRepository.save(incasari);
        }
        log.info("The {} has been added to the database", incasari.getDetalii());
        return incasari;
    }

//    private String getData() {
//        Date date = new Date();
//        String strDateFormat = "yyyy-MM-dd";
//        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
//        String formattedDate = dateFormat.format(date);
//        return formattedDate;
//    }


    @Override
    public List<Incasari> searchAll() {
        log.info("Return all incasari");
        return incasariRepository.findAll();
//        if(incasariList.size() >0 ){
//            return incasariList;
//        }else{
//            return null;
//        }
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
    public List<Incasari> searchByNumber(int number) {

        return incasariRepository.findAllByNumber(number);
    }


    @Override
    public Incasari update(int number, Incasari incasari) {
        List<Incasari> numberSearch = incasariRepository.findAllByNumber(number);
//        Incasari search = numberSearch.get(0);
////        Incasari search = new Incasari();
//        search.setData(incasari.getData());
//        search.setFurnizor(incasari.getFurnizor());
//        search.setNumber(incasari.getNumber());
//        search.setDetalii(incasari.getDetalii());
//        search.setSumaTotala(incasari.getSumaTotala());
//        search.setSumaFaraTVA(incasari.getSumaFaraTVA());
//        search.setSumaTVA(incasari.getSumaTVA());

        Incasari search = new Incasari.IncasariBilder()
                .data(incasari.getData())
                .furnizor(incasari.getFurnizor())
                .number(incasari.getNumber())
                .detalii(incasari.getDetalii())
                .sumaTotala(incasari.getSumaTotala())
                .sumaFaraTVA(incasari.getSumaFaraTVA())
                .sumaTVA(incasari.getSumaTotala())
                .build();


        search = incasariRepository.save(search);
        incasariRepository.deleteByNumber(number);

        return search;



    }




    @Override
    public void deleteNumber(int number) {
        incasariRepository.deleteByNumber(number);
    }




    public double calculateTVAByDate(String data) {
        List<Incasari> incasariListByDate = incasariRepository.findAllByData(data);
        return incasariListByDate.stream().map(Incasari::getSumaTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculateSumaTotala() {
        List<Incasari> incasariList = incasariRepository.findAll();
        log.info("Calculare suma totala");
        return incasariList.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }

    public double calculateSumaFaraTVA(){
        List<Incasari> incasariList = incasariRepository.findAll();
//        return incasariList.stream().map(Incasari::getSumaFaraTVA).reduce(0.0, Double::sum);
        log.info("Calculare suma totala fara TVA");
        return calculateSumaTotala()-calculateTotalTVA();
    }

    @Override
    public double calculateTotalByMonth(String month) {

//        String [] dateParts = data.split(".");
//        //        String day = dateParts[0];
//        String month = dateParts[1];
////        String year = dateParts[2];

//        switch(month) {
//            case "01":
//                month = "Ianuarie";
//                break;
//            case "02":
//                month = "Februarie";
//                break;
//            case "03":
//                month = "Martie";
//                break;
//            case "04":
//                month = "Aprilie";
//                break;
//            case "05":
//                month = "Mai";
//                break;
//            case "06":
//                month = "Iunie";
//                break;
//            case "07":
//                month = "Iulie";
//                break;
//            case "08":
//                month = "August";
//                break;
//            case "09":
//                month = "Septembrie";
//                break;
//            case "10":
//                month = "Octombrie";
//                break;
//            case "11":
//                month = "Noiembrie";
//                break;
//            case "12":
//                month = "Decembrie";
//                break;
//            default:
//                System.out.println(month);
//        }

//        if (month == "01"){
//            month = "Ianuarie";
//        }else if(month == "02"){
//            month = "Februarie";
//        }else if(month == "03") {
//            month = "Februarie";
//        }else if(month == "04") {
//            month = "Martie";
//        } else if(month == "05") {
//            month = "Aprilie";
//        }else if(month == "06") {
//            month = "Mai";
//        }else if(month == "07") {
//            month = "Iunie";
//        }else if(month == "08") {
//            month = "Iulie";
//        }else if(month == "09") {
//            month = "August";
//        }else if(month == "10") {
//            month = "Septembrie";
//        }else if(month == "11") {
//            month = "Noiembrie";
//        }else if(month == "12") {
//            month = "Decembrie";
//        }


        List<Incasari> incasariListByDate = incasariRepository.findByMonth(month);
        log.info("Calculare");
//        System.out.println(month);
        return incasariListByDate.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);




    }

    @Override
    public double calculateTotalByYear(String year) {
        List<Incasari> incasariListByDate = incasariRepository.findAByYear(year);
        log.info("Calculate by year");
        return incasariListByDate.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }





    @Override
    public double calculateTotalByMonthAndYear(String month, String year) {
        List<Incasari> incasariListByMonthAndYear = incasariRepository.findAllByMonthAndYear(month,year);
        log.info("Month and year");
        return incasariListByMonthAndYear.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculateTotalByDataBetweenData(String data1, String data2) {
        List<Incasari> incasariListByDataBetweenData = incasariRepository.findAllByData1IsAfterAndData2IsBefore(data1, data2);
        log.info("Between");
        return incasariListByDataBetweenData.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }


    public double calculateTotalTVA() {
        List<Incasari> incasariList = incasariRepository.findAll();
        log.info("Calculare suma totala  TVA");
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