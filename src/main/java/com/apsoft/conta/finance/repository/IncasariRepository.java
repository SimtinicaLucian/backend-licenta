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

    //data1, data2
    List<Incasari> findAllByData1GreaterThanEqualAndData2LessThanEqual(String data1, String data2);

    //furnizor, sum1, sum2
    List<Incasari> findAllByFurnizorAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String furnizor, double sumaTotala1, double sumaTotala2);
    //

    //furnizor, data1, data2
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqual(String furnizor, String data1, String data2 );

    //suma1, sum2
    List<Incasari> findAllBySumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(double sumaTotala1, double sumaTotala2);

    //data1, data2, sum1, sum2
    List<Incasari> findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String data1, String data2, double sumaTotala1, double sumaTotala2);

    //furnizor, data1, data1, sum1, sum2
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String furnizor,String data1, String data2, double sumaTotala1, double sumaTotala2);



    List<Incasari> findAllByNumber(String number);

    List<Incasari> findAllById(String id);


    List<Incasari> findAllByFurnizor(String furnizor);



    @Transactional
    void deleteByNumber(String number);


}

