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
    public List<Incasari> searchAll(){
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
    public List<Incasari> searchByNumber(int number){

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



        return incasariRepository.save(search);

    }

    @Override
    public void deleteNumber(int number) {
        incasariRepository.deleteByNumber(number);
    }


    public double calculateTotalTVA(){
        List<Incasari> incasariList = incasariRepository.findAll();
        return incasariList.stream().map(Incasari::getSumaTVA).reduce(0.0,Double::sum);

    }

    public double calculateTVAByDate(String date){
        List<Incasari> incasariListByDate = incasariRepository.findAllByData(date);
        return incasariListByDate.stream().map(Incasari::getSumaTVA).reduce(0.0,Double::sum);


    }




}
