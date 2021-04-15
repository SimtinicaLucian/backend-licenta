package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.exception.HttpError;
import com.apsoft.conta.security.service.IAuthenticationFacade;
import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.repository.IncasariRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.util.*;

import static java.util.stream.Collectors.toList;


@Slf4j
@Service
public class IncasariServiceImpl implements IncasariService {


    @Autowired
    private IncasariRepository incasariRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;


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


//    @Override
//    public List<Incasari> save(@RequestParam Map<String, String> params, Incasari incasari) {
//
//        if((null == params.get("data") || params.get("data").isEmpty() )|| (null == params.get("furnizor") || params.get("furnizor").isEmpty()) ||
//                (null == params.get("number") || params.get("number").isEmpty()) || (null == params.get("detalii") || params.get("detalii").isEmpty()) ||
//                null == params.get("sumaTotala1") || null == params.get("sumaTotala2")){
//            throw HttpError.notFound("Object is null");
//        }
//
//        List<Incasari> incasariSearch = incasariRepository.findAllByNumber(incasari.getNumber());
//
//        incasari.setMonth(incasari.getData().substring(5, 7));
//        incasari.setYear(incasari.getData().substring(0, 4));
//
//
//        incasari.setData1(incasari.getData().substring(0, 10));
//        incasari.setData2(incasari.getData().substring(0, 10));
//
//        incasari.setSumaTotala1(incasari.getSumaTotala());
//        incasari.setSumaTotala2(incasari.getSumaTotala());
//
//
//        if (incasariSearch.size() < 1) {
//            incasari = incasariRepository.save(incasari);
//        }
//        log.info("The {} has been added to the database", incasari.getDetalii());
//        return (List<Incasari>) incasari;
//    }


    private void Set(Incasari incasari) {
        incasari.setMonth(incasari.getData().substring(5, 7));
        incasari.setYear(incasari.getData().substring(0, 4));


        incasari.setData1(incasari.getData().substring(0, 10));
        incasari.setData2(incasari.getData().substring(0, 10));


        incasari.setSumaTotala1(incasari.getSumaTotala());
        incasari.setSumaTotala2(incasari.getSumaTotala());
    }

    public void SetCalcule(Incasari incasari) {
        double sumaTVA = IncasariUtils.calculareTVA(incasari);
        incasari.setSumaTVA(sumaTVA);

        double sumaFaraTVA = IncasariUtils.calculareFaraTVA(incasari);
        incasari.setSumaFaraTVA(sumaFaraTVA);

        double sumaTVA_Incasata = IncasariUtils.calculareTVA_Incasata(incasari);
        incasari.setSumaTVA_Incasata(sumaTVA_Incasata);

        double sumaFaraTVA_Incasata = IncasariUtils.calculareFaraTVA_Incasata(incasari);
        incasari.setSumaFaraTVA_Incasata(sumaFaraTVA_Incasata);

        double rest = IncasariUtils.Rest(incasari);
        incasari.setRest(rest);
    }


    @Override
    public Incasari saveIncasari(Incasari incasari) throws ParseException {
        Incasari newIncasari = incasari;
        newIncasari.setBy_added(getUsername());
        if (IncasariUtils.validateIncasari(incasari)) {
            throw HttpError.notFound("Object is null");
        }


        List<Incasari> incasariSearch = incasariRepository.findAllByNumber(incasari.getNumber());
        List<Incasari> furnizorSearch = incasariRepository.findAllByFurnizorAndNumber(incasari.getFurnizor(), incasari.getNumber());


        Set(incasari);

//        double sumaTVA = IncasariUtils.calculareTVA(incasari);
//        incasari.setSumaTVA(sumaTVA);
//
//        double sumaFaraTVA = IncasariUtils.calculareFaraTVA(incasari);
//        incasari.setSumaFaraTVA(sumaFaraTVA);
//
//        double sumaTVA_Incasata= IncasariUtils.calculareTVA_Incasata(incasari);
//        incasari.setSumaTVA_Incasata(sumaTVA_Incasata);
//
//        double sumaFaraTVA_Incasata= IncasariUtils.calculareFaraTVA_Incasata(incasari);
//        incasari.setSumaFaraTVA_Incasata(sumaFaraTVA_Incasata);
//
//        double rest = IncasariUtils.Rest(incasari);
//        incasari.setRest(rest);

        SetCalcule(incasari);

        IncasariUtils.setStare(incasari);


        if (furnizorSearch.size() < 1) {
            incasari = incasariRepository.save(incasari);
        } else {
            throw HttpError.notFound("This provider with this number exists !");
        }

        log.info("The {} has been added to the database", incasari.getDetalii());
        return incasari;
    }


    @Override
    public List<Incasari> searchAll() throws ParseException {
        log.info("Return all incasari");
        return incasariRepository.findAll().stream()
                .map(i -> {
                    i.setStare(IncasariUtils.setSearchAll(i));
                    return i;
                })
                .collect(toList());
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
    public List<Incasari> searchWithoutDatesAndStare(String furnizor, double sumaTotala1, double SumaTotala2) {
        log.info("without data1, data2");
        return incasariRepository.findAllByFurnizorAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(furnizor, sumaTotala1, SumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutDates(String furnizor, double sumaTotala1, double SumaTotala2, String stare) {
        log.info("without data1, data2");
        return incasariRepository.findAllByFurnizorAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(furnizor, sumaTotala1, SumaTotala2, stare);
    }
//    ---------------------

    @Override
    public List<Incasari> searchWithoutFurnizorAndStare(String firstDate, String secondDate, double totalSum, double totalSumTwo) {
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(firstDate, secondDate, totalSum, totalSumTwo);
    }

    @Override
    public List<Incasari> searchWithoutFurnizor(String firstDate, String secondDate, double totalSum, double totalSumTwo, String stare) {
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(firstDate, secondDate, totalSum, totalSumTwo, stare);
    }
//    -------------------------

    @Override
    public List<Incasari> searchWithoutSumAndStare(String furnizor, String data1, String data2) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqual(furnizor, data1, data2);
    }

    @Override
    public List<Incasari> searchWithoutSum(String furnizor, String data1, String data2, String stare) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndStare(furnizor, data1, data2, stare);
    }

    //-----------------------
    @Override
    public List<Incasari> searchWithoutFurnizorAndSumAndStare(String data1, String data2) {
        log.info("without sum1, sum2, furnizor");
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqual(data1, data2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndSum(String data1, String data2, String stare) {
        log.info("without sum1, sum2, furnizor");
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndStare(data1, data2, stare);
    }
//    -------------------

    @Override
    public List<Incasari> searchWithoutFurnizorAndDatesAndStare(double sumaTotala1, double sumaTotala2) {
        return incasariRepository.findAllBySumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndDates(double sumaTotala1, double sumaTotala2, String stare) {
        return incasariRepository.findAllBySumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(sumaTotala1, sumaTotala2, stare);
    }


// SUMATOTALA2


    @Override
    public List<Incasari> searchWithoutsumaTotala2AndStare(String furnizor, String data1, String data2, double sumaTotala1) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(furnizor, data1, data2, sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutsumaTotala2(String furnizor, String data1, String data2, double sumaTotala1, String stare) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(furnizor, data1, data2, sumaTotala1, stare);
    }
//    -------------------------------


    @Override
    public List<Incasari> searchWithoutDatesAndSum2AndStare(String furnizor, double sumaTotala1) {
        return incasariRepository.findAllByFurnizorAndSumaTotala1GreaterThanEqual(furnizor, sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutDatesAndSum2(String furnizor, double sumaTotala1, String stare) {
        return incasariRepository.findAllByFurnizorAndSumaTotala1GreaterThanEqualAndStare(furnizor, sumaTotala1, stare);
    }

//    -------------------

    @Override
    public List<Incasari> searchWithoutFurnizorAndSum2AndStare(String data1, String data2, double sumaTotala1) {
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(data1, data2, sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndSum2(String data1, String data2, double sumaTotala1, String stare) {
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(data1, data2, sumaTotala1, stare);
    }
//    -----------

    @Override
    public List<Incasari> searchWithoutFunrizorAndDatesAndSum2AndStare(double sumaTotala1) {
        return incasariRepository.findAllBySumaTotala1GreaterThanEqual(sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutFunrizorAndDatesAndSum2(double sumaTotala1, String stare) {
        return incasariRepository.findAllBySumaTotala1GreaterThanEqualAndStare(sumaTotala1, stare);
    }
//    ------------


    // SUMATOTALA1
    @Override
    public List<Incasari> searchWithoutsumaTotala1AndStare(String furnizor, String data1, String data2, double sumaTotala2) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqual(furnizor, data1, data2, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutsumaTotala1(String furnizor, String data1, String data2, double sumaTotala2, String stare) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(furnizor, data1, data2, sumaTotala2, stare);
    }
//    --------

    @Override
    public List<Incasari> searchWithoutDatesAndSum1AndStare(String furnizor, double sumaTotala2) {
        return incasariRepository.findAllByFurnizorAndSumaTotala2LessThanEqual(furnizor, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutDatesAndSum1(String furnizor, double sumaTotala2, String stare) {
        return incasariRepository.findAllByFurnizorAndSumaTotala2LessThanEqualAndStare(furnizor, sumaTotala2, stare);
    }
//    -------

    @Override
    public List<Incasari> searchWithoutFurnizorAndSum1AndStare(String data1, String data2, double sumaTotala2) {
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqual(data1, data2, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndSum1(String data1, String data2, double sumaTotala2, String stare) {
        return incasariRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(data1, data2, sumaTotala2, stare);
    }
//    ----------------

    @Override
    public List<Incasari> searchWithoutFunrizorAndDatesAndSum1AndStare(double sumaTotala2) {
        return incasariRepository.findAllBySumaTotala2LessThanEqual(sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFunrizorAndDatesAndSum1(double sumaTotala2, String stare) {
        return incasariRepository.findAllBySumaTotala2LessThanEqualAndStare(sumaTotala2, stare);
    }

//    ------

    // WITHOUT DATA1
    @Override
    public List<Incasari> searchWithoutData1AndStare(String furnizor, String data2, double sumaTotala1, double sumaTotala2) {
        return incasariRepository.findAllByFurnizorAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(furnizor, data2, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutData1(String furnizor, String data2, double sumaTotala1, double sumaTotala2, String stare) {
        return incasariRepository.findAllByFurnizorAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(furnizor, data2, sumaTotala1, sumaTotala2, stare);
    }
//    -------------------

    @Override
    public List<Incasari> searchWithoutData1AndSum1AndStare(String furnizor, String data2, double sumaTotala2) {
        return incasariRepository.findAllByFurnizorAndData2LessThanEqualAndSumaTotala2LessThanEqual(furnizor, data2, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutData1AndSum1(String furnizor, String data2, double sumaTotala2, String stare) {
        return incasariRepository.findAllByFurnizorAndData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(furnizor, data2, sumaTotala2, stare);
    }
//    ---------------

    @Override
    public List<Incasari> searchWithoutData1AndSum2AndStare(String furnizor, String data2, double sumaTotala1) {
        return incasariRepository.findAllByFurnizorAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(furnizor, data2, sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutData1AndSum2(String furnizor, String data2, double sumaTotala1, String stare) {
        return incasariRepository.findAllByFurnizorAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(furnizor, data2, sumaTotala1, stare);
    }

//    -----------

    @Override
    public List<Incasari> searchWithoutFurnizorAndData1AndStare(String data2, double sumaTotala1, double sumaTotala2) {
        return incasariRepository.findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(data2, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData1(String data2, double sumaTotala1, double sumaTotala2, String stare) {
        return incasariRepository.findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(data2, sumaTotala1, sumaTotala2, stare);
    }

//    ---------------

    @Override
    public List<Incasari> searchWithoutFurnizorAndData1AndSum1AndStare(String data2, double sumaTotala2) {
        return incasariRepository.findAllByData2LessThanEqualAndSumaTotala2LessThanEqual(data2, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData1AndSum1(String data2, double sumaTotala2, String stare) {
        return incasariRepository.findAllByData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(data2, sumaTotala2, stare);
    }

//    ----------------------

    @Override
    public List<Incasari> searchWithoutFurnizorAndData1AndSum2AndStare(String data2, double sumaTotala1) {
        return incasariRepository.findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqual(data2, sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData1AndSum2(String data2, double sumaTotala1, String stare) {
        return incasariRepository.findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(data2, sumaTotala1, stare);
    }
//    -----------------------

    @Override
    public List<Incasari> searchWithoutFurnizorAndData1AndSumsAndStare(String data2) {
        return incasariRepository.findAllByData2LessThanEqual(data2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData1AndSums(String data2, String stare) {
        return incasariRepository.findAllByData2LessThanEqualAndStare(data2, stare);
    }

//    ------------------------

    @Override
    public List<Incasari> searchWithoutData1AndSumsAndStare(String furnizor, String data2) {
        return incasariRepository.findAllByFurnizorAndData2LessThanEqual(furnizor, data2);
    }

    @Override
    public List<Incasari> searchWithoutData1AndSums(String furnizor, String data2, String stare) {
        return incasariRepository.findAllByFurnizorAndData2LessThanEqualAndStare(furnizor, data2, stare);
    }

//    -------------------------


    //WITHOUT DATA2
    @Override
    public List<Incasari> searchWithoutData2AndStare(String furnizor, String data1, double sumaTotala1, double sumaTotala2) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(furnizor, data1, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutData2(String furnizor, String data1, double sumaTotala1, double sumaTotala2, String stare) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(furnizor, data1, sumaTotala1, sumaTotala2, stare);
    }

//    ---------------------

    @Override
    public List<Incasari> searchWithoutData2AndSum1AndStare(String furnizor, String data1, double sumaTotala2) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala2LessThanEqual(furnizor, data1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutData2AndSum1(String furnizor, String data1, double sumaTotala2, String stare) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(furnizor, data1, sumaTotala2, stare);
    }

//    ---------

    @Override
    public List<Incasari> searchWithoutData2AndSum2AndStare(String furnizor, String data1, double sumaTotala1) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqual(furnizor, data1, sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutData2AndSum2(String furnizor, String data1, double sumaTotala1, String stare) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndStare(furnizor, data1, sumaTotala1, stare);
    }

//    -----------------------

    @Override
    public List<Incasari> searchWithoutFurnizorAndData2AndStare(String data1, double sumaTotala1, double sumaTotala2) {
        return incasariRepository.findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(data1, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData2(String data1, double sumaTotala1, double sumaTotala2, String stare) {
        return incasariRepository.findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(data1, sumaTotala1, sumaTotala2, stare);
    }

//    ------------------------

    @Override
    public List<Incasari> searchWithoutFurnizorAndData2AndSum1AndStare(String data1, double sumaTotala2) {
        return incasariRepository.findAllByData1GreaterThanEqualAndSumaTotala2LessThanEqual(data1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData2AndSum1(String data1, double sumaTotala2, String stare) {
        return incasariRepository.findAllByData1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(data1, sumaTotala2, stare);
    }

//    -------------------------

    @Override
    public List<Incasari> searchWithoutFurnizorAndData2AndSum2AndStare(String data1, double sumaTotala1) {
        return incasariRepository.findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqual(data1, sumaTotala1);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData2AndSum2(String data1, double sumaTotala1, String stare) {
        return incasariRepository.findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndStare(data1, sumaTotala1, stare);
    }

//    -------------------

    @Override
    public List<Incasari> searchWithoutFurnizorAndData2AndSumsAndStare(String data1) {
        return incasariRepository.findAllByData1GreaterThanEqual(data1);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndData2AndSums(String data1, String stare) {
        return incasariRepository.findAllByData1GreaterThanEqualAndStare(data1, stare);
    }

//    -------------------

    @Override
    public List<Incasari> searchWithoutData2AndSumsAndStare(String furnizor, String data1) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqual(furnizor, data1);
    }

    @Override
    public List<Incasari> searchWithoutData2AndSums(String furnizor, String data1, String stare) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndStare(furnizor, data1, stare);
    }

//    ------------------------


    @Override
    public List<Incasari> searchWithoutStare(String furnizor, String data1, String data2, Double sumaTotala1, Double sumaTotala2) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(furnizor, data1, data2, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Incasari> searchAllParams(String furnizor, String data1, String data2, Double sumaTotala1, Double sumaTotala2, String stare) {
        return incasariRepository.findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(furnizor, data1, data2, sumaTotala1, sumaTotala2, stare);
    }

    @Override
    public List<Incasari> searchWithoutFurnizorAndDatesAndSums(String stare) {
        return incasariRepository.findAllByStare(stare);
    }

    @Override
    public List<Incasari> searchWithoutDatesAndSums(String furnizor, String stare) {
        return incasariRepository.findAllByFurnizorAndStare(furnizor, stare);
    }


    @Override
    public List<Incasari> searchByNumber(String number) {

        return incasariRepository.findAllByNumber(number);
    }

    @Override
    public List<Incasari> searchById(long id) {
        return incasariRepository.findAllById(id);
    }


    @Override
    public Incasari update(long id, Incasari incasari) throws ParseException {
        Incasari newIncasari = incasari;
        newIncasari.setBy_added(getUsername());

        if (IncasariUtils.validateIncasari(incasari)) {
            throw HttpError.notFound("Object is null");
        }

        Set(incasari);

        SetCalcule(incasari);

        IncasariUtils.setStare(incasari);

        long noOfIncasari = incasariRepository.findAllByFurnizorAndNumber(incasari.getFurnizor(), incasari.getNumber()).stream()
                .filter(i -> i.getId() != id)
                .count();

        if (noOfIncasari > 0L) {
            throw HttpError.notFound("This provider with this number exists !");
        }


        Optional<Incasari> incasariFound = incasariRepository.findById(id);
        incasariFound.ifPresent(i -> {
            incasari.setId(i.getId());
//            incasari.setData(i.getData());
//            incasari.setData1(i.getData());
//            incasari.setData2(i.getData());
//            incasari.setFurnizor(i.getFurnizor());
//            incasari.setNumber(i.getNumber());
//            incasari.setDetalii(i.getDetalii());
//            incasari.setSumaTotala(i.getSumaTotala());
//            incasari.setSumaFaraTVA(i.getSumaFaraTVA());
//            incasari.setSumaTVA(i.getSumaTVA());
//            incasari.setMonth((i.getMonth()));
//            incasari.setYear(i.getYear());
//            incasari.setSumaTotala1(i.getSumaTotala());
//            incasari.setSumaTotala2(i.getSumaTotala());

            incasariRepository.save(incasari);
        });
        log.info("Update");
        return incasari;
    }


    @Override
    public void deleteId(long id) {
        log.info("delete");
        incasariRepository.deleteById(id);
    }

//    @Override
//    public long countFurnizor(String furnizor){
//        return incasariRepository.countByFurnizor(furnizor);
//    }
//
//    @Override
//    public long countAll(){
//        return incasariRepository.countAll();
//    }

    @Override
    public long count() {
        log.info("Count all");
        return incasariRepository.count();
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
        List<Incasari> incasariListByDate = incasariRepository.findAllByYear(year);
        log.info("Calculate by year");
        return incasariListByDate.stream().map(Incasari::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public List<Incasari> searchByYear(String year) {
        return incasariRepository.findAllByYear(year);
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

    private String getUsername() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }


    public void addFact(String detalii) {
        log.info("addFact");
        incasariRepository.addFact(detalii);
    }

    public void deleteFact(String detalii) {
        log.info("deleteFact");
        incasariRepository.deleteFact(detalii);
    }


}