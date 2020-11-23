package com.apsoft.conta.finance.repository;

import com.apsoft.conta.finance.persistence.Incasari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IncasariRepository extends JpaRepository<Incasari, Long> {

    List<Incasari> findAllByData(String data);

    List<Incasari> findAByYear(String year);

    List<Incasari> findByMonth(String month);

    List<Incasari> findAllByMonthAndYear(String month, String year);

    List<Incasari> findAllById(long id);

    List<Incasari> findAllByNumber(int number);

    List<Incasari> findAllByFurnizor(String furnizor);






    @Transactional
    void deleteByNumber(int number);


}

