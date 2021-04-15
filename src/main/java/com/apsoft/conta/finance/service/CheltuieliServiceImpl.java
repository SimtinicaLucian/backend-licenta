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
        return cheltuieliRepository.findAll();
    }

}
