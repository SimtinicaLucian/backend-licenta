package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Salariu;

import java.text.ParseException;
import java.util.List;

public interface SalariuService {
    List<Salariu> searchAll() throws ParseException;

    Salariu saveSalariu(Salariu salariu) throws ParseException;

    void deleteId(long id);

    Salariu update(long id, Salariu salariu) throws ParseException;

    List<Salariu> searchById(long id);
}
