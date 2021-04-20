package com.apsoft.conta.finance.repository;

import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Incasari;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CheltuieliRepository extends JpaRepository<Cheltuieli, Long> {

    @Transactional
    void deleteById(long id);

    List<Cheltuieli> findAllByBeneficiarAndNumber(String beneficiar, String number);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndData2LessThanEqual(String data1, String data2);

    List<Cheltuieli> findAllByMonthAndYear(String month, String year);

    List<Cheltuieli> findAllByYear(String year);

    List<Cheltuieli> findAllByStare(String stare);

    long countAllByStare(String stare);

}
