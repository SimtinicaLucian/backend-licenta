package com.apsoft.conta.finance.resource;


import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.service.IncasariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/incasari")
public class IncasariResource {

    @Autowired
    private IncasariService incasariService;


    @PostMapping(value = "/add")
    public Incasari persist(@RequestBody Incasari incasari) {
        return incasariService.saveIncasari(incasari);
    }

    @GetMapping(value = "/searchAll")
    public List<Incasari> searchAll() {
        return incasariService.searchAll();
    }



    @GetMapping(value = "/search/data/{data}")
    public List<Incasari> searchByData(@PathVariable String data) {
        return incasariService.searchByData(data);
    }

    @GetMapping(value = "/search/furnizor/{furnizor}")
    public List<Incasari> searchByFurnizor(@PathVariable String furnizor) {
        return incasariService.searchByFurnizor(furnizor);
    }

    @GetMapping(value = "/search/monthandyear")
    public List<Incasari> searchByMonthAndYear(@RequestParam String month, @RequestParam String year) {
        return incasariService.searchByMonthAndYear(month, year);
    }

    @GetMapping(value = "/search/betweenData")
    public List<Incasari> searchByBetweenData(@RequestParam String firstDate, @RequestParam String secondDate) {
        return incasariService.searchByBetweenData(firstDate, secondDate);
    }

    @GetMapping(value = "/search/betweenSumaTotala")
    public List<Incasari> searchByBetweenSumaTotala(@RequestParam String firstDate, @RequestParam String secondDate, @RequestParam double firstDate1, @RequestParam double secondDate1) {
        return incasariService.searchByBetweenSumaTotala(firstDate, secondDate, firstDate1, secondDate1);
    }

    @GetMapping(value = "/search/furnizorbetweenSumaTotala")
    public List<Incasari> searchByFurnizorAndBetweenSumaTotala(@RequestParam String furnizor,@RequestParam String firstDate, @RequestParam String secondDate, @RequestParam(required = false, name = "firstDate1") double firstDate1, @RequestParam(required = false, name = "secondDate1") double secondDate1) {
        return incasariService.searchByFurnizorAndBetweenSumaTotala(furnizor,firstDate, secondDate, firstDate1, secondDate1);
    }


    @GetMapping(value = "/test")
    public List<Incasari> testMethod(@RequestParam Map<String, String> params){

        String firstDate = params.get("firstDate");
        
        if(null == params.get("furnizor")){
            return incasariService.searchByFurnizorAndBetweenSumaTotala(params.get("furnizor"), params.get("data1"), params.get("data2"), Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

        return params.get("firstDate");


    }




    @GetMapping(value = "/search/year/{year}")
    public List<Incasari> searchByYear(@PathVariable String year) {
        return incasariService.searchByYear(year);
    }


    @GetMapping(value = "/search/number/{number}")
    public List<Incasari> searchByNumar(@PathVariable int number) {
        return incasariService.searchByNumber(number);
    }


    @PutMapping(value = "/update/{number}")
    public Incasari update(@PathVariable int number, @RequestBody Incasari incasari) {
        return incasariService.update(number, incasari);
    }

    @DeleteMapping(value = "/delete/number/{number}")
    public void deleteNumber(@PathVariable int number){
        incasariService.deleteNumber(number);
    }

    @GetMapping(value = "/searchAllTVA")
    public double searchByTVA() {
        return incasariService.calculateTotalTVA();
    }

    @GetMapping(value = "/searchAllTotal")
    public double searchByTotal() {
        return incasariService.calculateSumaTotala();
    }

    @GetMapping(value = "/searchAllFaraTVA")
    public double searchByFaraTVA() {
        return incasariService.calculateSumaFaraTVA();
    }


    @GetMapping(value = "/searchByDateTVA/{data}")
    public double searchByDateTVA(@PathVariable String data) {

        return incasariService.calculateTVAByDate(data);
    }

    @GetMapping(value = "/calculateTotalByMonth/{month}")
    public double calculateTotalByMonth(@PathVariable String month) {
        return incasariService.calculateTotalByMonth(month);
    }


    @GetMapping(value = "/calculateTotalByYear/{year}")
    public double calculateTotalByYear(@PathVariable String year) {

        return incasariService.calculateTotalByYear(year);
    }
    

    @GetMapping(value = "/calculateTotalByMonthAndYear")
    public double calculateTotalByMonthAndYear(@RequestParam String firstDate, @RequestParam String secondDate ) {
        return incasariService.calculateTotalByMonthAndYear(firstDate, secondDate);
    }

    
    @GetMapping(value = "/calculateTotalByDataBetweenData")
    public double calculateTotalByDataBetweenData(@RequestParam String firstDate, @RequestParam String secondDate ) {
        return incasariService.calculateTotalByDataBetweenData(firstDate, secondDate);
    }

//    @GetMapping(value = "/calculateTotalByDataBetweenData/{data1}-{data2}")
//    public double calculateTotalByDataBetweenData(@PathVariable String data1, @PathVariable String data2 ) {
//        return incasariService.calculateTotalByDataBetweenData(data1, data2);
//    }


    @PutMapping(value = "/update")
    public Incasari updaterows(@RequestBody Incasari incasari) {

        return incasariService.updaterows(incasari);
    }

}
