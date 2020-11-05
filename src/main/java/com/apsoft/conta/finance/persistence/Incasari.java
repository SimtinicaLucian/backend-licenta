package com.apsoft.conta.finance.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "incasari")
public class Incasari {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;

    @Column(name = "data")
    private String data;

    @Column(name = "furnizor")
    private String furnizor;

    @Column(name = "numar")
    private int numar;

    @Column(name = "detalii")
    private String detalii;

    @Column(name = "sumaTotala")
    private double sumaTotala;

    @Column(name = "sumaFaraTVA")
    private double sumaFaraTVA;

    @Column(name = "sumaTVA")
    private double sumaTVA;


}
