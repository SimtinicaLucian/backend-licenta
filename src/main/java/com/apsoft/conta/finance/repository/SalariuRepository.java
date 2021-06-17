package com.apsoft.conta.finance.repository;

import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Salariu;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface SalariuRepository extends JpaRepository<Salariu, Long> {

    @Transactional
    void deleteById(long id);

    List<Salariu> findAllByNumeAndNumber(String nume, String number);

    List<Salariu> findAllById(long id);

    List<Salariu> findAllByStare(String stare);

    List<Salariu> findAllByMonthAndYear(String month, String year);

    List<Salariu> findAllByYear(String year);

    long countAllByStare(String stare);
}
