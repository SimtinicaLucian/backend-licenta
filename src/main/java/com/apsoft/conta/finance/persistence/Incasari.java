package com.apsoft.conta.finance.persistence;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "incasari")
public class Incasari {

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

    @Column(name = "furnizor")
    private String furnizor;

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

    @Column(name = "sumaTotala_Incasata")
    private double sumaTotala_Incasata;

    @Column(name = "sumaFaraTVA_Incasata")
    private double sumaFaraTVA_Incasata;

    @Column(name = "sumaTVA_Incasata")
    private double sumaTVA_Incasata;

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

    @Column(name = "data_Plata")
    private String data_Plata;


    @Column(name = "sumaTotala1")
    private double sumaTotala1;

    @Column(name = "sumaTotala2")
    private double sumaTotala2;

    @Column(name = "stare")
    private String stare;

    @Column(name = "by_added")
    private String by_added;




    public Incasari(IncasariBilder builder){
        this.data = builder.data;
        this.furnizor = builder.furnizor;
        this.number = builder.number;
        this.detalii = builder.detalii;
        this.sumaTotala = builder.sumaTotala;
        this.sumaFaraTVA = builder.sumaFaraTVA;
        this.sumaTVA = builder.sumaTVA;
        this.month = builder.month;
        this.year = builder.year;
        this.data1 = builder.data1;
        this.data2 = builder.data2;
        this.sumaTotala1 = builder.sumaTotala1;
        this.sumaTotala2 = builder.sumaTotala2;
    }



    public static class IncasariBilder{

        private String data;
        private String furnizor;
        private String number;
        private String detalii;
        private double sumaTotala;
        private double sumaFaraTVA;
        private double sumaTVA;
        private String month;
        private String year;
        private String data1;
        private String data2;
        private double sumaTotala1;
        private double sumaTotala2;




        public IncasariBilder(String data, String furnizor, String number, String detalii, double sumaTotala, double sumaFaraTVA, double sumaTVA, String month, String year, String data1, String data2, double sumaTotala1, double sumaTotala2) {
            this.data = data;
            this.furnizor = furnizor;
            this.number = number;
            this.detalii = detalii;
            this.sumaTotala = sumaTotala;
            this.sumaFaraTVA = sumaFaraTVA;
            this.sumaTVA = sumaTVA;
            this.month = month;
            this.year = year;
            this.data1 = data1;
            this.data2 = data2;
            this.sumaTotala1 = sumaTotala1;
            this.sumaTotala2 = sumaTotala2;



        }

        public IncasariBilder(){

        }

        public IncasariBilder data(String data) {
            this.data = data;
            return this;
        }

        public IncasariBilder furnizor(String furnizor) {
            this.furnizor = furnizor;
            return this;
        }

        public IncasariBilder number(String number) {
            this.number = number;
            return this;
        }

        public IncasariBilder detalii(String detalii) {
            this.detalii = detalii;
            return this;
        }

        public IncasariBilder sumaTotala(double sumaTotala) {
            this.sumaTotala = sumaTotala;
            return this;
        }

        public IncasariBilder sumaFaraTVA(double sumaFaraTVA) {
            this.sumaFaraTVA = sumaFaraTVA;
            return this;
        }

        public IncasariBilder sumaTVA(double sumaTVA) {
            this.sumaTVA = sumaTVA;
            return this;
        }


        public IncasariBilder month(String month) {
            this.month = month;
            return this;
        }

        public IncasariBilder year(String year) {
            this.year = year;
            return this;
        }

        public IncasariBilder data1(String data1) {
            this.data1 = data1;
            return this;
        }

        public IncasariBilder data2(String data2) {
            this.data2 = data2;
            return this;
        }

        public IncasariBilder sumaTotala1(double sumaTotala1) {
            this.sumaTotala1 = sumaTotala1;
            return this;
        }

        public IncasariBilder sumaTotala2(double sumaTotala2) {
            this.sumaTotala2 = sumaTotala2;
            return this;
        }



        public Incasari build(){
            Incasari incasari = new Incasari(this);
            return incasari;
        }

    }



}
