package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Incasari;

import java.util.List;

public interface IncasariService {

    Incasari saveIncasari(Incasari incasari);

    void delete(int numar);

    List<Incasari> searchAll();

    List<Incasari> searchByData(String data);

    List<Incasari> searchByFurnizor(String furnizor);

    List<Incasari> searchByNumar(int numbar);

    Incasari update(int numar, Incasari incasari);
}