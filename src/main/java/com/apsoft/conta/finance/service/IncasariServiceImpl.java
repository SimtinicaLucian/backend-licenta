package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.repository.IncasariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncasariServiceImpl implements IncasariService {

    @Autowired
    private IncasariRepository incasariRepository;


    @Override
    public Incasari saveIncasari(Incasari incasari) {
        return incasariRepository.save(incasari);
    }


    @Override
    public void delete(int numar) {
        incasariRepository.deleteByNumar(numar);
    }


    @Override
    public List<Incasari> searchAll(){
        List<Incasari> incasariList = incasariRepository.findAll();
        if(incasariList.size() >0 ){
            return incasariList;
        }else{
            return null;
        }
    }


    @Override
    public List<Incasari> searchByData(String data) {
        List<Incasari> searchData = incasariRepository.findByData(data);
        return searchData;
    }

    @Override
    public List<Incasari> searchByFurnizor(String furnizor) {
        List<Incasari> searchFurnizor = incasariRepository.findByFurnizor(furnizor);
        return searchFurnizor;
    }

    @Override
    public List<Incasari> searchByNumar(int numar){
        List<Incasari> searchNumar = incasariRepository.findByNumar(numar);
        return searchNumar;
    }

    @Override
    public Incasari update(int numar, Incasari incasari) {
        List<Incasari> numarSearch = incasariRepository.findByNumar(numar);
        Incasari search = numarSearch.get(0);
        search.setData(incasari.getData());
        search.setFurnizor(incasari.getFurnizor());
        search.setNumar(incasari.getNumar());
        search.setDetalii(incasari.getDetalii());
        search.setSumaTotala(incasari.getSumaTotala());
        search.setSumaFaraTVA(incasari.getSumaFaraTVA());
        search.setSumaTVA(incasari.getSumaTVA());


        return incasariRepository.save(search);

    }


}
