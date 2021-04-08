package com.apsoft.conta.finance.persistence;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cheltuieli")
public class Cheltuieli{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;




//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
//    private String id;

    // Date date = new Date();
    //    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
    //    String dateCurrent = formatter.format(date);

    @Column(name = "data")
    private String data;

    @Column(name = "beneficiar")
    private String beneficiar;

    @Column(name = "number")
    private String number;

    @Column(name = "detalii")
    private String detalii;

    @Column(name = "sumaTotala")
    private double sumaTotala;

    @Column(name = "sumaFaraTVA")
    private double sumaFaraTVA;

    @Column(name = "sumaTVA")
    private double sumaTVA;

    @Column(name = "sumaTotala_Achitata")
    private double sumaTotala_Achitata;

    @Column(name = "sumaFaraTVA_Achitata")
    private double sumaFaraTVA_Achitata;

    @Column(name = "sumaTVA_Achitata")
    private double sumaTVA_Achitata;

    @Column(name = "rest")
    private double rest;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "data1")
    private String data1;

    @Column(name = "data2")
    private String data2;

    @Column(name = "sumaTotala1")
    private double sumaTotala1;

    @Column(name = "sumaTotala2")
    private double sumaTotala2;

    @Column(name = "stare")
    private String stare;

    @Column(name = "by_added")
    private String by_added;


}
