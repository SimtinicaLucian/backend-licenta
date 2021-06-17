package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.exception.HttpError;
import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Salariu;
import com.apsoft.conta.finance.repository.SalariuRepository;
import com.apsoft.conta.security.service.IAuthenticationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class SalariuServiceImpl implements SalariuService {

    @Autowired
    private SalariuRepository salariuRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;


    private void Set(Salariu salariu) {
        salariu.setMonth(salariu.getData().substring(5, 7));
        salariu.setYear(salariu.getData().substring(0, 4));


        salariu.setData1(salariu.getData().substring(0, 10));
        salariu.setData2(salariu.getData().substring(0, 10));
    }

    public void SetCalcule(Salariu salariu){
        double salariu_net = SalariuUtils.calculareSalariu_net(salariu);
        salariu.setSalariu_net(salariu_net);

        double rest = SalariuUtils.Rest(salariu);
        salariu.setRest(rest);
    }

    @Override
    public List<Salariu> searchAll() throws ParseException {
        log.info("Return all incasari");
        return salariuRepository.findAll().stream()
                .map(i -> {
                    i.setStare(SalariuUtils.setSearchAll_salariu(i));
                    salariuRepository.save(i);
                    return i;
                })
                .collect(toList());
    }

    @Override
    public Salariu saveSalariu(Salariu salariu) throws ParseException {
        Salariu newSalariu = salariu;
        newSalariu.setBy_added(getUsername());

        Set(salariu);
        SetCalcule(salariu);
        SalariuUtils.setStare(salariu);

        salariu = salariuRepository.save(salariu);

        log.info("Salariu adaugat", salariu.getDetalii());

    return salariu;
    }

    private String getUsername() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }


    @Override
    public void deleteId(long id) {
        log.info("delete");
        salariuRepository.deleteById(id);
    }



    @Override
    public Salariu update(long id, Salariu salariu) throws ParseException {
        Salariu newSalariu = salariu;
        newSalariu.setBy_added(getUsername());

//        if (IncasariUtils.validateIncasari(incasari)) {
//            throw HttpError.notFound("Object is null");
//        }

        Set(salariu);

        SetCalcule(salariu);

        SalariuUtils.setStare(salariu);

        long noOfSalariu = salariuRepository.findAllByNumeAndNumber(salariu.getNume(), salariu.getNumber()).stream()
                .filter(i -> i.getId() != id)
                .count();

        if (noOfSalariu > 0L) {
            throw HttpError.notFound("Acest angajat cu acest numar exista !");
        }


        Optional<Salariu> salariuFound = salariuRepository.findById(id);
        salariuFound.ifPresent(i -> {
            salariu.setId(i.getId());


            salariuRepository.save(salariu);
        });
        log.info("Update salariu");
        return salariu;
    }


    @Override
    public List<Salariu> searchById(long id) {
        return salariuRepository.findAllById(id);
    }
}
