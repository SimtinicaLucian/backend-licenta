package com.apsoft.conta.finance.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "salariu")
public class Salariu {


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

    @Column(name = "nume")
    private String nume;

    @Column(name = "number")
    private String number;

    @Column(name = "functie")
    private String functie;

    @Column(name = "detalii")
    private String detalii;

    @Column(name = "salariu_brut")
    private double salariu_brut;


    @Column(name = "salariu_net")
    private double salariu_net;

    @Column(name = "salariu_net_achitat")
    private double salariu_net_achitat;

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

    @Column(name = "data_Scadenta")
    private String data_Scadenta;

    @Column(name = "stare")
    private String stare;

    @Column(name = "by_added")
    private String by_added;


}

