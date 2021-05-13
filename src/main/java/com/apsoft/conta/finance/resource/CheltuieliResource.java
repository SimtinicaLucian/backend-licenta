package com.apsoft.conta.finance.resource;


import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.service.CheltuieliService;
import com.apsoft.conta.finance.service.IncasariUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/cheltuieli")
public class CheltuieliResource {

    @Autowired
    private CheltuieliService cheltuieliService;



    @PostMapping(value = "/add")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public Cheltuieli cheltuieli(@RequestBody Cheltuieli cheltuieli) throws ParseException {
        return cheltuieliService.saveCheltuieli(cheltuieli);
    }
    //1
//    @GetMapping(value = "/calculareSumaTotalaCuTVA")
//    public double calculareSumaTotalaCuTVA(){
//        return cheltuieliService.calculareSumaTotalaCuTVA();
//    }

    @GetMapping(value = "/rest")
    public double rest(){
        return cheltuieliService.rest();
    }

    //4
//    @GetMapping(value = "/culculareSumaTotalaCuTVADataMinDataMax")
//    public double culculareSumaTotalaCuTVADataMinDataMax(@RequestParam String data1, @RequestParam String data2) {
//        return cheltuieliService.culculareSumaTotalaCuTVADataMinDataMax(data1, data2);
//    }

    //5
//    @GetMapping(value = "/calculareSumaTotalaCuTVAMonthYear")
//    public double calculareSumaTotalaCuTVAMonthYear(@RequestParam String month, @RequestParam String year) {
//        return cheltuieliService.calculareSumaTotalaCuTVAMonthYear(month, year);
//    }


    @GetMapping(value = "/searchAll")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Cheltuieli> searchAll() {
        return cheltuieliService.searchAll();
    }


    @DeleteMapping(value = "/delete/{id}")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void deleteId(@PathVariable long id) {
        cheltuieliService.deleteId(id);
    }

    @PutMapping(value = "/update/{id}")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public Cheltuieli update(@PathVariable long id, @RequestBody Cheltuieli cheltuieli) throws ParseException {
        return cheltuieliService.update(id, cheltuieli);
    }

    @GetMapping(value = "/search/id/{id}")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Cheltuieli> searchById(@PathVariable long id) {
        return cheltuieliService.searchById(id);
    }

    @GetMapping(value = "/filtrare")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Cheltuieli> testMethod(@RequestParam Map<String, String> params) {

        if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without beneficiar, data1, data2, sum2, stare");
            return cheltuieliService
                    .searchWithoutFunrizorAndDatesAndSum2AndStare(Double.valueOf(params.get("sumaTotala1")));
        }
        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && null == params.get("sumaTotala1")&& (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without beneficiar, data1, data2, sum1, stare");
            return cheltuieliService
                    .searchWithoutFunrizorAndDatesAndSum1AndStare(Double.valueOf(params.get("sumaTotala2")));
        }

        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1") && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without beneficiar, data1, sum1, sum2, stare");
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));
            return cheltuieliService
                    .searchWithoutFurnizorAndData1AndSumsAndStare(format2);
        }

        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1") && null == params.get("sumaTotala2")){
            System.out.print("without beneficiar, data1, data2, sum1, sum2");

            return cheltuieliService.
                    searchWithoutFurnizorAndDatesAndSums(params.get("stare"));
        }

        // data2


        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1") && null == params.get("sumaTotala2")&& (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without beneficiar, data2, sum1, sum2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return cheltuieliService
                    .searchWithoutFurnizorAndData2AndSumsAndStare(format1);
        }

//        De aici
        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without beneficiar, data1, data2, sum2");
            return cheltuieliService
                    .searchWithoutFunrizorAndDatesAndSum2(Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without beneficiar, data1, data2, sum1");
            return cheltuieliService
                    .searchWithoutFunrizorAndDatesAndSum1(Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }
        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1") && null == params.get("sumaTotala2")){
            System.out.println("without beneficiar, data1, sum1, sum2");
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));
            return cheltuieliService
                    .searchWithoutFurnizorAndData1AndSums(format2, params.get("stare"));
        }

        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1") && null == params.get("sumaTotala2")){
            System.out.println("without beneficiar, data2, sum1, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return cheltuieliService
                    .searchWithoutFurnizorAndData2AndSums(format1, params.get("stare"));
        }
//        pana aici

        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without beneficiar, data1, sum2, stare");
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));
            return cheltuieliService
                    .searchWithoutFurnizorAndData1AndSum2AndStare(format2, Double.valueOf(params.get("sumaTotala1")));
        }
        //data2
        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without beneficiar, data2, sum2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return cheltuieliService
                    .searchWithoutFurnizorAndData2AndSum2AndStare(format1, Double.valueOf(params.get("sumaTotala1")));
        }

        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1") && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without beneficiar, data1, sum1, stare");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutFurnizorAndData1AndSum1AndStare(format2, Double.valueOf(params.get("sumaTotala2")));
        }
        //data2
        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1") && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without beneficiar, data2, sum1, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return cheltuieliService
                    .searchWithoutFurnizorAndData2AndSum1AndStare(format1, Double.valueOf(params.get("sumaTotala2")));
        }


        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println(params.get("data1, data2, beneficiar, stare"));
            return cheltuieliService
                    .searchWithoutFurnizorAndDatesAndStare(Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

//        aici(22.02.2021)
        else if (((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("sumaTotala2")) && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without data2, sum1, sum2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return cheltuieliService
                    .searchWithoutData2AndSumsAndStare(params.get("beneficiar"), format1);
        }
        else if(((null == params.get("data1")) || params.get("data1").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("sumaTotala2")) && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without data1, sum1, sum2, stare");
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));
            return cheltuieliService
                    .searchWithoutData1AndSumsAndStare(params.get("beneficiar"), format2);
        }

//        pana aici(22.02.2021)

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("sumaTotala2")) && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data1, data2, sum2, stare");
            return cheltuieliService
                    .searchWithoutDatesAndSum2AndStare(params.get("beneficiar"), Double.valueOf(params.get("sumaTotala1")));
        }
        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data1, data2, sum1, stare");
            return cheltuieliService
                    .searchWithoutDatesAndSum1AndStare(params.get("beneficiar"), Double.valueOf(params.get("sumaTotala2")));
        }

        else if (null == params.get("sumaTotala1") && (null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without sum1, sum2, beneficiar, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            System.out.println(format1);
            System.out.println(format2);

            return cheltuieliService
                    .searchWithoutFurnizorAndSumAndStare(format1, format2);
        }

        else if((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("sumaTotala2"))){
            System.out.println("without data1, data2, sum1,sum2");

            return cheltuieliService.
                    searchWithoutDatesAndSums(params.get("beneficiar"), params.get("stare"));
        }


        //        DE AICIIIIIII

        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala2")){
            System.out.println("without beneficiar, data1, sum2");
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));
            return cheltuieliService
                    .searchWithoutFurnizorAndData1AndSum2(format2, Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("beneficiar") || params.get("beneficiar").isEmpty())){
            System.out.println("without beneficiar, data2, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return cheltuieliService
                    .searchWithoutFurnizorAndData2AndSum2(format1, Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1")){
            System.out.println("without beneficiar, data1, sum1");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutFurnizorAndData1AndSum1(format2, Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1")){
            System.out.println("without beneficiar, data2, sum1");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return cheltuieliService
                    .searchWithoutFurnizorAndData2AndSum1(format1, Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("beneficiar") || params.get("beneficiar").isEmpty())) {
            System.out.println(params.get("data1, data2, beneficiar"));
            return cheltuieliService
                    .searchWithoutFurnizorAndDates(Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if (((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("sumaTotala2"))){
            System.out.println("without data2, sum1, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return cheltuieliService
                    .searchWithoutData2AndSums(params.get("beneficiar"), format1, params.get("stare"));
        }

        else if(((null == params.get("data1")) || params.get("data1").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("sumaTotala2"))){
            System.out.println("without data1, sum1, sum2");
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));
            return cheltuieliService
                    .searchWithoutData1AndSums(params.get("beneficiar"), format2, params.get("stare"));
        }

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("sumaTotala2"))) {
            System.out.println("without data1, data2, sum2");
            return cheltuieliService
                    .searchWithoutDatesAndSum2(params.get("beneficiar"), Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without data1, data2, sum1");
            return cheltuieliService
                    .searchWithoutDatesAndSum1(params.get("beneficiar"), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if (null == params.get("sumaTotala1") && (null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without sum1, sum2, beneficiar");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            System.out.println(format1);
            System.out.println(format2);

            return cheltuieliService
                    .searchWithoutFurnizorAndSum(format1, format2, params.get("stare"));
        }
//        PANA AIIICIIII



        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without beneficiar, data1, stare");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutFurnizorAndData1AndStare(format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }
        //data2
        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without beneficiar, data2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));


            return cheltuieliService
                    .searchWithoutFurnizorAndData2AndStare(format1, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

        else if((null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data1. sum2, stare");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return cheltuieliService
                    .searchWithoutData1AndSum2AndStare(params.get("beneficiar"), format2, Double.valueOf(params.get("sumaTotala1")));
        }
        //data2
        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data2, sum2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return cheltuieliService
                    .searchWithoutData2AndSum2AndStare(params.get("beneficiar"), format1, Double.valueOf(params.get("sumaTotala1")));
        }

        else if((null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data1. sum1, stare");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutData1AndSum1AndStare(params.get("beneficiar"), format2, Double.valueOf(params.get("sumaTotala2")));
        }
        //data2
        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data2, sum1, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return cheltuieliService
                    .searchWithoutData2AndSum1AndStare(params.get("beneficiar"), format1, Double.valueOf(params.get("sumaTotala2")));
        }
//        ------------------------------------------------------



        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data1, data2, stare");
            return cheltuieliService
                    .searchWithoutDatesAndStare(params.get("beneficiar"), Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

        else if ((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without sum2, beneficiar, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutFurnizorAndSum2AndStare(format1, format2,Double.valueOf(params.get("sumaTotala1")));
        }
        else if ((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && null == params.get("sumaTotala1") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without sum1, beneficiar, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutFurnizorAndSum1AndStare(format1, format2,Double.valueOf(params.get("sumaTotala2")));
        }


        else if (null == params.get("sumaTotala1") && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without sum1, sum2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return cheltuieliService
                    .searchWithoutSumAndStare(params.get("beneficiar"), format1, format2);
        }

        else if (null == params.get("beneficiar") || params.get("beneficiar").isEmpty() && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without beneficiar, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            System.out.println(format1);
            System.out.println(format2);

            return cheltuieliService
                    .searchWithoutFurnizorAndStare(format1, format2,
                            Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

//        DE AICIIII
        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty())){
            System.out.println("without beneficiar, data1");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutFurnizorAndData1(format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty())){
            System.out.println("without beneficiar, data2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));


            return cheltuieliService
                    .searchWithoutFurnizorAndData2(format1, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if((null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without data1. sum2");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return cheltuieliService
                    .searchWithoutData1AndSum2(params.get("beneficiar"), format2, Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without data2, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return cheltuieliService
                    .searchWithoutData2AndSum2(params.get("beneficiar"), format1, Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if((null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without data1. sum1");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutData1AndSum1(params.get("beneficiar"), format2, Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without data2, sum1");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return cheltuieliService
                    .searchWithoutData2AndSum1(params.get("beneficiar"), format1, Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty())) {
            System.out.println("without data1, data2");
            return cheltuieliService
                    .searchWithoutDates(params.get("beneficiar"), Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if ((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without sum2, beneficiar");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutFurnizorAndSum2(format1, format2,Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if ((null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without sum1, beneficiar");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutFurnizorAndSum1(format1, format2,Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }
        else if (null == params.get("beneficiar") || params.get("beneficiar").isEmpty()) {
            System.out.println("without beneficiar");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            System.out.println(format1);
            System.out.println(format2);

            return cheltuieliService
                    .searchWithoutFurnizor(format1, format2,
                            Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if (null == params.get("sumaTotala1") && null == params.get("sumaTotala2")) {
            System.out.println("without sum1, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return cheltuieliService
                    .searchWithoutSum(params.get("beneficiar"), format1, format2, params.get("stare"));
        }

//        PANA AICIIIII

        else if (null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without sumaTotala2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutsumaTotala2AndStare(params.get("beneficiar"), format1, format2, Double.valueOf(params.get("sumaTotala1")));
        }

        else if (null == params.get("sumaTotala1") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without sumaTotala1, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutsumaTotala1AndStare(params.get("beneficiar"), format1, format2, Double.valueOf(params.get("sumaTotala2")));
        }

//        ---------------------------------------------------------------------

        else if (null == params.get("data1") || params.get("data1").isEmpty() && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without data1, stare");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return cheltuieliService
                    .searchWithoutData1AndStare(params.get("beneficiar"), format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }
        //data2

        else if ((null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without data2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));


            return cheltuieliService
                    .searchWithoutData2AndStare(params.get("beneficiar"), format1, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

//        DE AICIIIIII !!!
        else if (null == params.get("sumaTotala2") ) {
            System.out.println("without sumaTotala2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutsumaTotala2(params.get("beneficiar"), format1, format2, Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }


        else if (null == params.get("sumaTotala1")) {
            System.out.println("without sumaTotala1");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService
                    .searchWithoutsumaTotala1(params.get("beneficiar"), format1, format2, Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if (null == params.get("data1") || params.get("data1").isEmpty()){
            System.out.println("without data1");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return cheltuieliService
                    .searchWithoutData1(params.get("beneficiar"), format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if (null == params.get("data2") || params.get("data2").isEmpty()){
            System.out.println("without data2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));


            return cheltuieliService
                    .searchWithoutData2(params.get("beneficiar"), format1, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }
//        PANA AIIICIIII !!!

        else if (null == params.get("stare") || params.get("stare").isEmpty()){
            System.out.println("without stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return cheltuieliService.
                    searchWithoutStare(params.get("beneficiar"), format1, format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

        String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
        String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

        return cheltuieliService.searchAllParams(params.get("beneficiar"), format1, format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
    }

}
