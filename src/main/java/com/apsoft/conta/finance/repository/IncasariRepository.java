package com.apsoft.conta.finance.repository;

import com.apsoft.conta.finance.persistence.Incasari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncasariRepository extends JpaRepository<Incasari, String> {

    List<Incasari> findByData(String data);
    List<Incasari> findByNumar(int number);
    void deleteByNumar(int number);
    List<Incasari> findByFurnizor(String furnizor);


}



//public interface ServiceRepository extends MongoRepository<Service, String> {
//    List<Service> findByData(String data);
//    List<Service> findByNumber(int number);
//    void deleteByNumber(int number);
//    List<Service> findByFurnizor(String furnizor);
//    List<Service> findByAuto(String auto);
//
//}