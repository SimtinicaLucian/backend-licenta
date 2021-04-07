package com.apsoft.conta.finance.repository;

import com.apsoft.conta.finance.persistence.Incasari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IncasariRepository extends JpaRepository<Incasari, Long> {

    List<Incasari> findAllByData(String data);

    List<Incasari> findAllByYear(String year);

    List<Incasari> findByMonth(String month);
    
    List<Incasari> findAllByFurnizorAndNumber(String furnizor, String number);

    List<Incasari> findAllByMonthAndYear(String month, String year);

    //data1, data2
    List<Incasari> findAllByData1GreaterThanEqualAndData2LessThanEqual(String data1, String data2);
    List<Incasari> findAllByData1GreaterThanEqualAndData2LessThanEqualAndStare(String data1, String data2, String stare);

    //furnizor, sum1, sum2
    List<Incasari> findAllByFurnizorAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String furnizor, double sumaTotala1, double sumaTotala2);
    List<Incasari> findAllByFurnizorAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String furnizor, double sumaTotala1, double sumaTotala2, String stare);

    //

    //furnizor, data1, data2
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqual(String furnizor, String data1, String data2 );
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndStare(String furnizor, String data1, String data2, String stare );

    //suma1, sum2
    List<Incasari> findAllBySumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(double sumaTotala1, double sumaTotala2);
    List<Incasari> findAllBySumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(double sumaTotala1, double sumaTotala2, String stare);


    //data1, data2, sum1, sum2
    List<Incasari> findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String data1, String data2, double sumaTotala1, double sumaTotala2);
    List<Incasari> findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String data1, String data2, double sumaTotala1, double sumaTotala2, String stare);

    //furnizor, data1, data1, sum1, sum2
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String furnizor,String data1, String data2, double sumaTotala1, double sumaTotala2);
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String furnizor,String data1, String data2, double sumaTotala1, double sumaTotala2, String stare);

    // aici!!!!!!!!!!!!
//    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndDateCurrentAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String furnizor, String data1, String dateCurrent, double sumaTotala1, double sumaTotala2);
// aici!!!!!!!!!!!!

        //List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqual
    // SUMATOTALA2
    // without sumaTotala2
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(String furnizor,String data1, String data2, double sumaTotala1);
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(String furnizor,String data1, String data2, double sumaTotala1, String stare);


    // withouw data1, data2, sum2
    List<Incasari> findAllByFurnizorAndSumaTotala1GreaterThanEqual(String furnizor, double sumaTotala1);
    List<Incasari> findAllByFurnizorAndSumaTotala1GreaterThanEqualAndStare(String furnizor, double sumaTotala1, String stare);


    // without furnizor, sum2
    List<Incasari> findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(String data1, String data2, double sumaTotala1);
    List<Incasari> findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(String data1, String data2, double sumaTotala1, String stare);

    // without furnizor, data1, data2, sum2
    List<Incasari> findAllBySumaTotala1GreaterThanEqual(double sumaTotala1);
    List<Incasari> findAllBySumaTotala1GreaterThanEqualAndStare(double sumaTotala1, String stare);



    // SUMATOTALA1
    // without sumaTotala1
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqual(String furnizor, String data1, String data2, double sumaTotala2);
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(String furnizor, String data1, String data2, double sumaTotala2, String stare);


    // without Data1, data2, sum1
    List<Incasari> findAllByFurnizorAndSumaTotala2LessThanEqual(String furnizor, double sumaTotala2);
    List<Incasari> findAllByFurnizorAndSumaTotala2LessThanEqualAndStare(String furnizor, double sumaTotala2, String stare);


    // without furnizor, sum1
    List<Incasari> findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqual(String data1, String data2, double sumaTotala2);
    List<Incasari> findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(String data1, String data2, double sumaTotala2, String stare);

    // without furnizor, data1, data2, sum1
    List<Incasari> findAllBySumaTotala2LessThanEqual(double sumaTotala2);
    List<Incasari> findAllBySumaTotala2LessThanEqualAndStare(double sumaTotala2, String stare);




    //DATA1
    //without data1
    List<Incasari> findAllByFurnizorAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String furnizor, String data2, double sumaTotala1, double sumaTotala2, String stare);

    //without data1, stare
    List<Incasari> findAllByFurnizorAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String furnizor, String data2, double sumaTotala1, double sumaTotala2);
    //without data1, sumaTotala1
    List<Incasari> findAllByFurnizorAndData2LessThanEqualAndSumaTotala2LessThanEqual(String furnizor, String data2, double sumaTotala2);
    List<Incasari> findAllByFurnizorAndData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(String furnizor, String data2, double sumaTotala2, String stare);


    //without data1, sumaTotala2
    List<Incasari> findAllByFurnizorAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(String furnizor, String data2, double sumaTotala1);
    List<Incasari> findAllByFurnizorAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(String furnizor, String data2, double sumaTotala1, String stare);

    //without furnizor, data1
    List<Incasari> findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String data2, double sumaTotala1, double sumaTotala2);
    List<Incasari> findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String data2, double sumaTotala1, double sumaTotala2, String stare);


    //without furnizor, data1, sumaTotala1
    List<Incasari> findAllByData2LessThanEqualAndSumaTotala2LessThanEqual(String data2, double sumaTotala2);
    List<Incasari> findAllByData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(String data2, double sumaTotala2, String stare);


    //without furnizor, data1, sumaTotala2
    List<Incasari> findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqual(String data2, double sumaTotala1);
    List<Incasari> findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(String data2, double sumaTotala1, String stare);

    //without furnizor, data1, sum1, sum2
    List<Incasari> findAllByData2LessThanEqual(String data2);
    List<Incasari> findAllByData2LessThanEqualAndStare(String data2, String stare);

    // without data1, sum1, sum2
    List<Incasari> findAllByFurnizorAndData2LessThanEqual(String furnizor, String data2);
    List<Incasari> findAllByFurnizorAndData2LessThanEqualAndStare(String furnizor, String data2, String stare);



    //DATA2
    //without data2
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String furnizor, String data1, double sumaTotala1, double sumaTotala2);
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String furnizor, String data1, double sumaTotala1, double sumaTotala2, String stare);

    //without data2, sumaTotala1
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala2LessThanEqual(String furnizor, String data1, double sumaTotala2);
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String furnizor, String data1, double sumaTotala2, String stare);

    //without data2, sumaTotala2
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqual(String furnizor, String data1, double sumaTotala1);
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndStare(String furnizor, String data1, double sumaTotala1, String stare);

    //without furnizor, data2
    List<Incasari> findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String data1, double sumaTotala1, double sumaTotala2);
    List<Incasari> findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String data1, double sumaTotala1, double sumaTotala2, String stare);

    //without furnizor, data2, sumaTotala1
    List<Incasari> findAllByData1GreaterThanEqualAndSumaTotala2LessThanEqual(String data1, double sumaTotala2);
    List<Incasari> findAllByData1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String data1, double sumaTotala2, String stare);

    //without furnizor, data2, sumaTotala2
    List<Incasari> findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqual(String data1, double sumaTotala1);
    List<Incasari> findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndStare(String data1, double sumaTotala1, String stare);

    //without furnizor, data2, sum1, sum2
    List<Incasari> findAllByData1GreaterThanEqual(String data1);
    List<Incasari> findAllByData1GreaterThanEqualAndStare(String data1, String stare);

    // without data2, sum1, sum2
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqual(String furnizor, String data1);
    List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndStare(String furnizor, String data1, String stare);

    List<Incasari> findAllByStare(String stare);
    List<Incasari> findAllByFurnizorAndStare(String furnizor, String stare);

//    without stare
    //List<Incasari> findAllByFurnizorAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String furnizor, String data1, String data2, double sumaTotala1, double sumaTotala2);

    List<Incasari> findAllByNumber(String number);

    List<Incasari> findAllById(long id);

    List<Incasari> findAllByFurnizor(String furnizor);


//    long countByFurnizor(String furnizor);
//
//    long countAll();

    long count();







    @Transactional
    void deleteById(long id);



    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cheltuieli SELECT * FROM incasari where detalii = 'achitat'", nativeQuery=true)
    void addFact(String detalii);



    @Modifying
    @Transactional
    @Query(value = "DELETE FROM incasari where detalii = 'achitat'" , nativeQuery=true)
    void deleteFact(String detalii);



    //INSERT INTO persons_table SELECT * FROM customer_table WHERE person_name = 'tom';



}

