package com.apsoft.conta.finance.repository;

import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Incasari;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CheltuieliRepository extends JpaRepository<Cheltuieli, Long> {

    @Transactional
    void deleteById(long id);

    List<Cheltuieli> findAllById(long id);

    List<Cheltuieli> findAllByBeneficiarAndNumber(String beneficiar, String number);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndData2LessThanEqual(String data1, String data2);

    List<Cheltuieli> findAllByMonthAndYear(String month, String year);

    List<Cheltuieli> findAllByYear(String year);

    List<Cheltuieli> findAllByStare(String stare);

    List<Cheltuieli> findAllBySumaTotala1GreaterThanEqual(double sumaTotala1);

    List<Cheltuieli> findAllBySumaTotala2LessThanEqual(double sumaTotala2);

    List<Cheltuieli> findAllByData2LessThanEqual(String data2);

    List<Cheltuieli> findAllByData1GreaterThanEqual(String data1);

    List<Cheltuieli> findAllBySumaTotala1GreaterThanEqualAndStare(double sumaTotala1, String stare);

    List<Cheltuieli> findAllBySumaTotala2LessThanEqualAndStare(double sumaTotala2, String stare);

    List<Cheltuieli> findAllByData2LessThanEqualAndStare(String data2, String stare);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndStare(String data1, String stare);

    List<Cheltuieli> findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqual(String data2, double sumaTotala1);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqual(String data1, double sumaTotala1);

    List<Cheltuieli> findAllByData2LessThanEqualAndSumaTotala2LessThanEqual(String data2, double sumaTotala2);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndSumaTotala2LessThanEqual(String data1, double sumaTotala2);

    List<Cheltuieli> findAllBySumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(double sumaTotala1, double sumaTotala2);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqual(String beneficiar, String data1);

    List<Cheltuieli> findAllByBeneficiarAndData2LessThanEqual(String beneficiar, String data2);

    List<Cheltuieli> findAllByBeneficiarAndSumaTotala1GreaterThanEqual(String beneficiar, double sumaTotala1);

    List<Cheltuieli> findAllByBeneficiarAndSumaTotala2LessThanEqual(String furnizor, double sumaTotala2);

    List<Cheltuieli> findAllByBeneficiarAndStare(String beneficiar, String stare);

    List<Cheltuieli> findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(String data2, double sumaTotala1, String stare);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndStare(String data1, double sumaTotala1, String stare);

    List<Cheltuieli> findAllByData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(String data2, double sumaTotala2, String stare);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String data1, double sumaTotala2, String stare);

    List<Cheltuieli> findAllBySumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(double sumaTotala1, double sumaTotala2, String stare);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndStare(String beneficiar, String data1, String stare);

    List<Cheltuieli> findAllByBeneficiarAndData2LessThanEqualAndStare(String beneficiar, String data2, String stare);

    List<Cheltuieli> findAllByBeneficiarAndSumaTotala1GreaterThanEqualAndStare(String beneficiar, double sumaTotala1, String stare);

    List<Cheltuieli> findAllByBeneficiarAndSumaTotala2LessThanEqualAndStare(String beneficiar, double sumaTotala2, String stare);


    List<Cheltuieli> findAllByData1GreaterThanEqualAndData2LessThanEqualAndStare(String data1, String data2, String stare);

    List<Cheltuieli> findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String data2, double sumaTotala1, double sumaTotala2);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String data1, double sumaTotala1, double sumaTotala2);

    List<Cheltuieli> findAllByBeneficiarAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(String beneficiar, String data2, double sumaTotala1);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqual(String beneficiar,  String data1, double sumaTotala1);

    List<Cheltuieli> findAllByBeneficiarAndData2LessThanEqualAndSumaTotala2LessThanEqual(String beneficiar, String data2, double sumaTotala2);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndSumaTotala2LessThanEqual(String beneficiar,String data1, double sumaTotala2 );

    List<Cheltuieli> findAllByBeneficiarAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String beneficiar, double sumaTotala1, double SumaTotala2);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(String data1, String data2, double sumaTotala1);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqual(String data1, String data2, double sumaTotala2);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqual(String beneficiar, String data1, String data2);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String data1, String data2, double sumaTotala1, double sumaTotala2);

    List<Cheltuieli> findAllByData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String data2, double sumaTotala1, double sumaTotala2, String stare);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String data1, double sumaTotala1, double sumaTotala2, String stare);

    List<Cheltuieli> findAllByBeneficiarAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(String beneficiar, String data2, double sumaTotala1, String stare);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndStare(String beneficiar, String data1, double sumaTotala1, String stare);

    List<Cheltuieli> findAllByBeneficiarAndData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(String beneficiar, String data2, double sumaTotala2, String stare);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String beneficiar, String data1, double sumaTotala2, String stare);

    List<Cheltuieli> findAllByBeneficiarAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String beneficiar, double sumaTotala1, double SumaTotala2, String stare);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(String data1, String data2, double sumaTotala1, String stare);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(String data1, String data2, double sumaTotala2, String stare);

    List<Cheltuieli> findAllByData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String data1, String data2, double sumaTotala1, double sumaTotala2, String stare);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndStare(String beneficiar, String data1, String data2, String stare);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqual(String beneficiar, String data1, String data2, double sumaTotala1);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqual(String beneficiar, String data1, String data2, double sumaTotala2);

    List<Cheltuieli> findAllByBeneficiarAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String beneficiar, String data2, double sumaTotala1, double sumaTotala2);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String beneficiar, String data1, double sumaTotala1, double sumaTotala2);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndStare(String beneficiar, String data1, String data2, double sumaTotala1, String stare);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala2LessThanEqualAndStare(String beneficiar, String data1, String data2, double sumaTotala2, String stare);

    List<Cheltuieli> findAllByBeneficiarAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String beneficiar, String data2, double sumaTotala1, double sumaTotala2, String stare);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String beneficiar, String data1, double sumaTotala1, double sumaTotala2, String stare);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqual(String beneficiar, String data1, String data2, Double sumaTotala1, Double sumaTotala2);

    List<Cheltuieli> findAllByBeneficiarAndData1GreaterThanEqualAndData2LessThanEqualAndSumaTotala1GreaterThanEqualAndSumaTotala2LessThanEqualAndStare(String beneficiar, String data1, String data2, Double sumaTotala1, Double sumaTotala2, String stare);

    long countAllByStare(String stare);

}
