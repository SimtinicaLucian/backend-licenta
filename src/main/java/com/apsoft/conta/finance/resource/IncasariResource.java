package com.apsoft.conta.finance.resource;


import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.service.IncasariService;
import com.apsoft.conta.finance.service.IncasariUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
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


//    @PostMapping(value = "/add")
//    public Incasari add(@RequestParam Map<String, String> params, @RequestBody Incasari incasari ){
//        return incasariService.save(params, incasari);
//    }


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




//-------------------------------------------------------------------------------------------------------------------------------------------------

//    @GetMapping(value = "/test")
//    public List<Incasari> testMethod(@RequestParam Map<String, String> params) {
//
//        if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala2")) {
//            System.out.println("without furnizor, data1, data2, sum2");
//            return incasariService
//                    .searchWithoutFunrizorAndDatesAndSum2(Double.valueOf(params.get("sumaTotala1")));
//        }
////-----------------------
//        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala1")) {
//            System.out.println("without furnizor, data1, data2, sum1");
//            return incasariService
//                    .searchWithoutFunrizorAndDatesAndSum1(Double.valueOf(params.get("sumaTotala2")));
//        }
////-----------------------
//         else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("furnizor") || params.get("furnizor").isEmpty())) {
//            System.out.println(params.get("furnizor"));
//            return incasariService
//                    .searchWithoutFurnizorAndDates(Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
//         }
//
//         else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("sumaTotala2"))) {
//             System.out.println("without data1, data2, sum2");
//             return incasariService
//                     .searchWithoutDatesAndSum2(params.get("furnizor"), Double.valueOf(params.get("sumaTotala1")));
//         }
////---------------------
//        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("sumaTotala1"))) {
//            System.out.println("without data1, data2, sum1");
//            return incasariService
//                    .searchWithoutDatesAndSum1(params.get("furnizor"), Double.valueOf(params.get("sumaTotala2")));
//        }
////---------------------
//
//         else if (null == params.get("sumaTotala1") && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala2")) {
//             System.out.println("without sum1, sum2, furnizor");
//             return incasariService
//                     .searchWithoutFurnizorAndSum(params.get("data1"), params.get("data2"));
//         }
//
//         else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty())) {
//            System.out.println("without data1, data2");
//            return incasariService
//                    .searchWithoutDates(params.get("furnizor"), Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
//         }
//
//         else if ((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala2")) {
//             System.out.println("without sum2, furnizor");
//             return incasariService
//                     .searchWithoutFurnizorAndSum2(params.get("data1"), params.get("data2"),Double.valueOf(params.get("sumaTotala1")));
//         }
////-------------------------
//        else if ((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala1")) {
//            System.out.println("without sum1, furnizor");
//            return incasariService
//                    .searchWithoutFurnizorAndSum1(params.get("data1"), params.get("data2"),Double.valueOf(params.get("sumaTotala2")));
//        }
////-------------------------
//
//         else if (null == params.get("furnizor") || params.get("furnizor").isEmpty()) {
//            System.out.println("without furnizor");
//            return incasariService
//                    .searchWithoutFurnizor(params.get("data1"), params.get("data2"),
//                            Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
//        }
//
//         else if (null == params.get("sumaTotala1") && null == params.get("sumaTotala2")) {
//            System.out.println("without sum1, sum2");
//            return incasariService
//                    .searchWithoutSum(params.get("furnizor"), params.get("data1"), params.get("data2"));
//        }
//
//         else if (null == params.get("sumaTotala2")) {
//            System.out.println("without sumaTotala2");
//            return incasariService
//                    .searchWithoutsumaTotala2(params.get("furnizor"), params.get("data1"), params.get("data2"), Double.valueOf(params.get("sumaTotala1")));
//        }
////------------------------------------
//        else if (null == params.get("sumaTotala1")) {
//            System.out.println("without sumaTotala1");
//            return incasariService
//                    .searchWithoutsumaTotala1(params.get("furnizor"), params.get("data1"), params.get("data2"), Double.valueOf(params.get("sumaTotala2")));
//        }
////-------------------------------------
//
//
//        return incasariService.searchAllParams(params.get("furnizor"), params.get("data1"), params.get("data2"), Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
//    }



//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    @GetMapping(value = "/test")
    public List<Incasari> testMethod(@RequestParam Map<String, String> params) throws ParseException {

        if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without furnizor, data1, data2, sum2");
            return incasariService
                    .searchWithoutFunrizorAndDatesAndSum2(Double.valueOf(params.get("sumaTotala1")));
        }
        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without furnizor, data1, data2, sum1");
            return incasariService
                    .searchWithoutFunrizorAndDatesAndSum1(Double.valueOf(params.get("sumaTotala2")));
        }


//        -------------------------------------------------aici
        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1") && null == params.get("sumaTotala2")){
            System.out.println("without furnizor, data1, sum1, sum2");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return incasariService
                    .searchWithoutFurnizorAndData1AndSums(format2);
        }

        // data2


        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1") && null == params.get("sumaTotala2")){
            System.out.println("without furnizor, data2, sum1, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutFurnizorAndData2AndSums(format1);
        }

//        -------------------------------------------------pana aici

        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala2")){
            System.out.println("without furnizor, data1, sum2");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutFurnizorAndData1AndSum2(format2, Double.valueOf(params.get("sumaTotala1")));
        }
    //data2
        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("furnizor") || params.get("furnizor").isEmpty())){
            System.out.println("without furnizor, data2, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutFurnizorAndData2AndSum2(format1, Double.valueOf(params.get("sumaTotala1")));
        }
//        ---------------------------------------------------
        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1")){
            System.out.println("without furnizor, data1, sum1");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutFurnizorAndData1AndSum1(format2, Double.valueOf(params.get("sumaTotala2")));
        }
    //data2
        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1")){
            System.out.println("without furnizor, data2, sum1");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutFurnizorAndData2AndSum1(format1, Double.valueOf(params.get("sumaTotala2")));
        }
//        ---------------------------------------------------

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("furnizor") || params.get("furnizor").isEmpty())) {
            System.out.println(params.get("furnizor"));
            return incasariService
                    .searchWithoutFurnizorAndDates(Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

//        aici(22.02.2021)
        else if (((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("sumaTotala2"))){
            System.out.println("without data2, sum1, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutData2AndSums(params.get("furnizor"), format1);
        }
        else if(((null == params.get("data1")) || params.get("data1").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("sumaTotala2"))){
            System.out.println("without data1, sum1, sum2");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutData1AndSums(params.get("furnizor"), format2);
        }

//        pana aici(22.02.2021)

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("sumaTotala2"))) {
            System.out.println("without data1, data2, sum2");
            return incasariService
                    .searchWithoutDatesAndSum2(params.get("furnizor"), Double.valueOf(params.get("sumaTotala1")));
        }
        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("sumaTotala1"))) {
            System.out.println("without data1, data2, sum1");
            return incasariService
                    .searchWithoutDatesAndSum1(params.get("furnizor"), Double.valueOf(params.get("sumaTotala2")));
        }

        else if (null == params.get("sumaTotala1") && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without sum1, sum2, furnizor");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            System.out.println(format1);
            System.out.println(format2);

            return incasariService
                    .searchWithoutFurnizorAndSum(format1, format2);
        }

//        ----------------------------------------------------
        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty())){
            System.out.println("without furnizor, data1");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutFurnizorAndData1(format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }
    //data2
        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty())){
            System.out.println("without furnizor, data2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));


            return incasariService
                    .searchWithoutFurnizorAndData2(format1, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }
//        ----------------------------------------------------
        else if((null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without data1. sum2");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return incasariService
                    .searchWithoutData1AndSum2(params.get("furnizor"), format2, Double.valueOf(params.get("sumaTotala1")));
        }
    //data2
        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without data2, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutData2AndSum2(params.get("furnizor"), format1, Double.valueOf(params.get("sumaTotala1")));
        }
//        ------------------------------------------------------
        else if((null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without data1. sum1");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutData1AndSum1(params.get("furnizor"), format2, Double.valueOf(params.get("sumaTotala2")));
        }
    //data2
        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without data2, sum1");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutData2AndSum1(params.get("furnizor"), format1, Double.valueOf(params.get("sumaTotala2")));
        }
//        ------------------------------------------------------



        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty())) {
            System.out.println("without data1, data2");
            return incasariService
                    .searchWithoutDates(params.get("furnizor"), Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

        else if ((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without sum2, furnizor");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutFurnizorAndSum2(format1, format2,Double.valueOf(params.get("sumaTotala1")));
        }
        else if ((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without sum1, furnizor");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutFurnizorAndSum1(format1, format2,Double.valueOf(params.get("sumaTotala2")));
        }

        else if (null == params.get("furnizor") || params.get("furnizor").isEmpty()) {
            System.out.println("without furnizor");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            System.out.println(format1);
            System.out.println(format2);

            return incasariService
                    .searchWithoutFurnizor(format1, format2,
                            Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

        else if (null == params.get("sumaTotala1") && null == params.get("sumaTotala2")) {
            System.out.println("without sum1, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return incasariService
                    .searchWithoutSum(params.get("furnizor"), format1, format2);
        }

        else if (null == params.get("sumaTotala2")) {
            System.out.println("without sumaTotala2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutsumaTotala2(params.get("furnizor"), format1, format2, Double.valueOf(params.get("sumaTotala1")));
        }

        else if (null == params.get("sumaTotala1")) {
            System.out.println("without sumaTotala1");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutsumaTotala1(params.get("furnizor"), format1, format2, Double.valueOf(params.get("sumaTotala2")));
        }

//        ---------------------------------------------------------------------

        else if (null == params.get("data1") || params.get("data1").isEmpty()){
            System.out.println("without data1");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return incasariService
                    .searchWithoutData1(params.get("furnizor"), format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }
    //data2

        else if (null == params.get("data2") || params.get("data2").isEmpty()){
            System.out.println("without data2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));


            return incasariService
                    .searchWithoutData2(params.get("furnizor"), format1, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }
//        ----------------------------------------------------------------------

        String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
        String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

        return incasariService.searchAllParams(params.get("furnizor"), format1, format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
    }



    @GetMapping(value = "/search/year/{year}")
    public List<Incasari> searchByYear(@PathVariable String year) {
        return incasariService.searchByYear(year);
    }


    @GetMapping(value = "/search/number/{number}")
    public List<Incasari> searchByNumar(@PathVariable String number) {
        return incasariService.searchByNumber(number);
    }


    @PutMapping(value = "/update/{id}")
    public Incasari update(@PathVariable long id, @RequestBody Incasari incasari) {
        return incasariService.update(id, incasari);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteId(@PathVariable long id) {
        incasariService.deleteId(id);
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
    public double calculateTotalByMonthAndYear(@RequestParam String firstDate, @RequestParam String secondDate) {
        return incasariService.calculateTotalByMonthAndYear(firstDate, secondDate);
    }


    @GetMapping(value = "/calculateTotalByDataBetweenData")
    public double calculateTotalByDataBetweenData(@RequestParam String firstDate, @RequestParam String secondDate) {
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
