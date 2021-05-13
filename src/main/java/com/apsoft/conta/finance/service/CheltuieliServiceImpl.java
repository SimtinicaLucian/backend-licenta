package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.exception.HttpError;
import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.repository.CheltuieliRepository;
import com.apsoft.conta.finance.repository.IncasariRepository;
import com.apsoft.conta.security.service.IAuthenticationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class CheltuieliServiceImpl implements CheltuieliService {


    @Autowired
    private CheltuieliRepository cheltuieliRepository;

    @Autowired
    private IncasariRepository incasariRepository;

    @Autowired
    private IncasariServiceImpl incasariServiceImpl;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    private void SetCheltuieli(Cheltuieli cheltuieli){
        cheltuieli.setMonth(cheltuieli.getData().substring(5, 7));
        cheltuieli.setYear(cheltuieli.getData().substring(0, 4));


        cheltuieli.setData1(cheltuieli.getData().substring(0, 10));
        cheltuieli.setData2(cheltuieli.getData().substring(0, 10));


        cheltuieli.setSumaTotala1(cheltuieli.getSumaTotala());
        cheltuieli.setSumaTotala2(cheltuieli.getSumaTotala());
    }


    public void SetCalcule(Cheltuieli cheltuieli){
        double sumaTVA = CheltuieliUtils.calculareTVA(cheltuieli);
        cheltuieli.setSumaTVA(sumaTVA);

        double sumaFaraTVA = CheltuieliUtils.calculareFaraTVA(cheltuieli);
        cheltuieli.setSumaFaraTVA(sumaFaraTVA);

        double sumaTVA_Incasata= CheltuieliUtils.calculareTVA_Achitata(cheltuieli);
        cheltuieli.setSumaTVA_Achitata(sumaTVA_Incasata);

        double sumaFaraTVA_Incasata= CheltuieliUtils.calculareFaraTVA_Achitata(cheltuieli);
        cheltuieli.setSumaFaraTVA_Achitata(sumaFaraTVA_Incasata);

        double rest = CheltuieliUtils.Rest(cheltuieli);
        cheltuieli.setRest(rest);
    }

    @Override
    public Cheltuieli saveCheltuieli(Cheltuieli cheltuieli) throws ParseException {
        Cheltuieli newCheltuieli = cheltuieli;
        newCheltuieli.setBy_added(getUsername());

        if (CheltuieliUtils.validareCheltuieli(cheltuieli)){
            throw HttpError.notFound("Object is null");
        }


    List<Cheltuieli> beneficiarSerch = cheltuieliRepository.findAllByBeneficiarAndNumber(cheltuieli.getBeneficiar(), cheltuieli.getNumber());

        SetCheltuieli(cheltuieli);
        double sumaTVA = CheltuieliUtils.calculareTVA(cheltuieli);
        cheltuieli.setSumaTVA(sumaTVA);

        double sumaFaraTVA = CheltuieliUtils.calculareFaraTVA(cheltuieli);
        cheltuieli.setSumaFaraTVA(sumaFaraTVA);

        double sumaTVA_Achitata = CheltuieliUtils.calculareTVA_Achitata(cheltuieli);
        cheltuieli.setSumaTVA_Achitata(sumaTVA_Achitata);

        double sumaFaraTVA_Achitata = CheltuieliUtils.calculareFaraTVA_Achitata(cheltuieli);
        cheltuieli.setSumaFaraTVA_Achitata(sumaFaraTVA_Achitata);

        double rest = CheltuieliUtils.Rest(cheltuieli);
        cheltuieli.setRest(rest);

//
        SetCalcule(cheltuieli);
        CheltuieliUtils.setStare(cheltuieli);


        if(beneficiarSerch.size() < 1){
            cheltuieli = cheltuieliRepository.save(cheltuieli);
        } else{
            throw HttpError.notFound("This provider with this number exists !");
        }
        log.info("The {} has been added to the database", cheltuieli.getDetalii());
        

        return cheltuieli;
    }

    private String getUsername() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }


    @Override
    public void deleteId(long id){
        log.info("The {} has been deleted to the database");
        cheltuieliRepository.deleteById(id);
    }

    @Override
    public List<Cheltuieli> searchById(long id) {
        return cheltuieliRepository.findAllById(id);
    }

    @Override
    public double calculareSumaTotalaCuTVA(){
        log.info("Calculare suma cu TVA");
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAll();
        return cheltuieliList.stream().map(Cheltuieli::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTVA(){
        log.info("Calculare sumaTVA");
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAll();
        return cheltuieliList.stream().map(Cheltuieli::getSumaTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaFaraTVA(){
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAll();
        return cheltuieliList.stream().map(Cheltuieli::getSumaFaraTVA).reduce(0.0, Double::sum);
    }

    @Override
    public double rest(){
        log.info("RestTVA");
        return calculareSumaTVA() - incasariServiceImpl.calculateTotalTVA();
    }

    @Override
    public double culculareSumaTotalaCuTVADataMinDataMax(String data1, String data2) {

        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByData1GreaterThanEqualAndData2LessThanEqual(data1, data2);
        log.info("Between");

        return cheltuieliList.stream().map(Cheltuieli::getSumaTotala).reduce(0.0, Double::sum);
    }

    @Override
    public double calculareSumaTotalaCuTVAMonthYear(String month, String year) {
        List<Cheltuieli> cheltuieliList = cheltuieliRepository.findAllByMonthAndYear(month, year);
        log.info("Month and year");
        return cheltuieliList.stream().map(Cheltuieli::getSumaTotala).reduce(0.0, Double::sum);
    }


    @Override
    public List<Cheltuieli> searchAll() {
        log.info("Return all cheltuieli");
        return cheltuieliRepository.findAll().stream().map(i -> {
            i.setStare(CheltuieliUtils.setSearchAll_Cheltuieli(i));
            cheltuieliRepository.save(i);
            return i;
        })
                .collect(toList());
          }

    @Override
    public Cheltuieli update(long id, Cheltuieli cheltuieli) throws ParseException {
        Cheltuieli newCheltuieli = cheltuieli;
        newCheltuieli.setBy_added(getUsername());

        if(CheltuieliUtils.validareCheltuieli(cheltuieli)){
            throw HttpError.notFound("Object is null");
        }

        SetCheltuieli(cheltuieli);
        SetCalcule(cheltuieli);
        CheltuieliUtils.setStare(cheltuieli);

        long noOfCheltuieli = cheltuieliRepository.findAllByBeneficiarAndNumber(cheltuieli.getBeneficiar(),cheltuieli.getNumber()).
                stream().filter(i-> i.getId() != id).count();

        if (noOfCheltuieli > 0L){
            throw HttpError.notFound("This provider with this number exists !");
        }

        Optional<Cheltuieli> cheltuieliFound = cheltuieliRepository.findById(id);
        cheltuieliFound.ifPresent(i -> {
            cheltuieli.setId(i.getId());

            cheltuieliRepository.save(cheltuieli);
        });
        log.info("Update cheltuieli");
        return cheltuieli;
    }

    @Override
    public List<Cheltuieli> searchWithoutFunrizorAndDatesAndSum2AndStare(double sumaTotala1) {
        return cheltuieliRepository.findAllBySumaTotala1GreaterThanEqual(sumaTotala1);
    }

    @Override
    public List<Cheltuieli> searchWithoutFunrizorAndDatesAndSum1AndStare(double sumaTotala2) {
        return cheltuieliRepository.findAllBySumaTotala2LessThanEqual(sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData1AndSumsAndStare(String data2) {
        return cheltuieliRepository.findAllByData2LessThanEqual(data2);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndDatesAndSums(String stare) {
        return cheltuieliRepository.findAllByStare(stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData2AndSumsAndStare(String data1) {
        return cheltuieliRepository.findAllByData1GreaterThanEqual(data1);
    }

    @Override
    public List<Cheltuieli> searchWithoutFunrizorAndDatesAndSum2(double sumaTotala1, String stare) {
        return cheltuieliRepository.findAllBySumaTotala1GreaterThanEqualAndStare(sumaTotala1, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFunrizorAndDatesAndSum1(double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllBySumaTotala2LessThanEqualAndStare(sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData1AndSums(String data2, String stare) {
        return cheltuieliRepository.findAllByData2LessThanEqualAndStare(data2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData2AndSums(String data1, String stare) {
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndStare(data1, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData1AndSum2AndStare(String data2, double sumaTotala1) {
        return cheltuieliRepository.findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqual(data2, sumaTotala1);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData2AndSum2AndStare(String data1, double sumaTotala1) {
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqual(data1, sumaTotala1);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData1AndSum1AndStare(String data2, double sumaTotala2) {
        return cheltuieliRepository.findAllByData2LessThanEqualAndSumaTotala2LessThanEqual(data2, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData2AndSum1AndStare(String data1, double sumaTotala2) {
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndSumaTotala2LessThanEqual(data1, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndDatesAndStare(double sumaTotala1, double sumaTotala2) {
        return cheltuieliRepository.findAllBySumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutData2AndSumsAndStare(String beneficiar, String data1) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqual(beneficiar, data1);
    }

    @Override
    public List<Cheltuieli> searchWithoutData1AndSumsAndStare(String beneficiar, String data2) {
        return cheltuieliRepository.findAllByBeneficiarAndData2LessThanEqual(beneficiar, data2);
    }

    @Override
    public List<Cheltuieli> searchWithoutDatesAndSum2AndStare(String beneficiar, double sumaTotala1) {
        return cheltuieliRepository.findAllByBeneficiarAndSumaTotala1GreaterThanEqual(beneficiar, sumaTotala1);
    }

    @Override
    public List<Cheltuieli> searchWithoutDatesAndSum1AndStare(String beneficiar, double sumaTotala2) {
        return cheltuieliRepository.findAllByBeneficiarAndSumaTotala2LessThanEqual(beneficiar, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndSumAndStare(String data1, String data2) {
        log.info("without sum1, sum2, furnizor");
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndData2LessThanEqual(data1, data2);
    }

    @Override
    public List<Cheltuieli> searchWithoutDatesAndSums(String beneficiar, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndStare(beneficiar, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData1AndSum2(String data2, double sumaTotala1, String stare) {
        return cheltuieliRepository.findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(data2, sumaTotala1, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData2AndSum2(String data1, double sumaTotala1, String stare) {
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndStare(data1, sumaTotala1, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData1AndSum1(String data2, double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllByData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(data2, sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData2AndSum1(String data1, double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(data1, sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndDates(double sumaTotala1, double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllBySumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(sumaTotala1, sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutData2AndSums(String beneficiar, String data1, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndStare(beneficiar, data1, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutData1AndSums(String beneficiar, String data2, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndData2LessThanEqualAndStare(beneficiar, data2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutDatesAndSum2(String beneficiar, double sumaTotala1, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndSumaTotala1GreaterThanEqualAndStare(beneficiar, sumaTotala1, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutDatesAndSum1(String beneficiar, double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndSumaTotala2LessThanEqualAndStare(beneficiar, sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndSum(String data1, String data2, String stare) {
        log.info("without sum1, sum2, furnizor");
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndStare(data1, data2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData1AndStare(String data2, double sumaTotala1, double sumaTotala2) {
        return cheltuieliRepository.findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(data2, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData2AndStare(String data1, double sumaTotala1, double sumaTotala2) {
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(data1, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutData1AndSum2AndStare(String beneficiar, String data2, double sumaTotala1) {
        return cheltuieliRepository.findAllByBeneficiarAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(beneficiar, data2, sumaTotala1);
    }

    @Override
    public List<Cheltuieli> searchWithoutData2AndSum2AndStare(String beneficiar, String data1, double sumaTotala1) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqual(beneficiar, data1, sumaTotala1);
    }

    @Override
    public List<Cheltuieli> searchWithoutData1AndSum1AndStare(String beneficiar, String data2, double sumaTotala2) {
        return cheltuieliRepository.findAllByBeneficiarAndData2LessThanEqualAndSumaTotala2LessThanEqual(beneficiar, data2, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutData2AndSum1AndStare(String beneficiar, String data1, double sumaTotala2) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndSumaTotala2LessThanEqual(beneficiar, data1, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutDatesAndStare(String beneficiar, double sumaTotala1, double SumaTotala2) {
        log.info("without data1, data2");
        return cheltuieliRepository.findAllByBeneficiarAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(beneficiar, sumaTotala1, SumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndSum2AndStare(String data1, String data2, double sumaTotala1) {
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(data1, data2, sumaTotala1);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndSum1AndStare(String data1, String data2, double sumaTotala2) {
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqual(data1, data2, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutSumAndStare(String beneficiar, String data1, String data2) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqual(beneficiar, data1, data2);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndStare(String data1, String data2, double sumaTotala1, double sumaTotala2) {
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(data1, data2, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData1(String data2, double sumaTotala1, double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(data2, sumaTotala1, sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndData2(String data1, double sumaTotala1, double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(data1, sumaTotala1, sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutData1AndSum2(String beneficiar, String data2, double sumaTotala1, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(beneficiar, data2, sumaTotala1, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutData2AndSum2(String beneficiar, String data1, double sumaTotala1, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndStare(beneficiar, data1, sumaTotala1, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutData1AndSum1(String beneficiar, String data2, double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(beneficiar, data2, sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutData2AndSum1(String beneficiar, String data1, double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(beneficiar, data1, sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutDates(String beneficiar, double sumaTotala1, double SumaTotala2, String stare) {
        log.info("without data1, data2");
        return cheltuieliRepository.findAllByBeneficiarAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(beneficiar, sumaTotala1, SumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndSum2(String data1, String data2, double sumaTotala1, String stare) {
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(data1, data2, sumaTotala1, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizorAndSum1(String data1, String data2, double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(data1, data2, sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutFurnizor(String data1, String data2, double sumaTotala1, double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(data1, data2, sumaTotala1, sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutSum(String beneficiar, String data1, String data2, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndStare(beneficiar, data1, data2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutsumaTotala2AndStare(String beneficiar, String data1, String data2, double sumaTotala1) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(beneficiar, data1, data2, sumaTotala1);
    }

    @Override
    public List<Cheltuieli> searchWithoutsumaTotala1AndStare(String beneficiar, String data1, String data2, double sumaTotala2) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqual(beneficiar, data1, data2, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutData1AndStare(String beneficiar, String data2, double sumaTotala1, double sumaTotala2) {
        return cheltuieliRepository.findAllByBeneficiarAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(beneficiar, data2, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutData2AndStare(String beneficiar, String data1, double sumaTotala1, double sumaTotala2) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(beneficiar, data1, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchWithoutsumaTotala2(String beneficiar, String data1, String data2, double sumaTotala1, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(beneficiar, data1, data2, sumaTotala1, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutsumaTotala1(String beneficiar, String data1, String data2, double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(beneficiar, data1, data2, sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutData1(String beneficiar, String data2, double sumaTotala1, double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(beneficiar, data2, sumaTotala1, sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutData2(String beneficiar, String data1, double sumaTotala1, double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(beneficiar, data1, sumaTotala1, sumaTotala2, stare);
    }

    @Override
    public List<Cheltuieli> searchWithoutStare(String beneficiar, String data1, String data2, Double sumaTotala1, Double sumaTotala2) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(beneficiar, data1, data2, sumaTotala1, sumaTotala2);
    }

    @Override
    public List<Cheltuieli> searchAllParams(String beneficiar, String data1, String data2, Double sumaTotala1, Double sumaTotala2, String stare) {
        return cheltuieliRepository.findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(beneficiar, data1, data2, sumaTotala1, sumaTotala2, stare);
    }


}
