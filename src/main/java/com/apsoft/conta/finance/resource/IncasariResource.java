package com.apsoft.conta.finance.resource;


import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.service.IncasariService;
import com.apsoft.conta.finance.service.IncasariServiceImpl;
import com.apsoft.conta.finance.service.IncasariUtils;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private IncasariServiceImpl incasariServiceImpl;


    @PostMapping(value = "/add")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public Incasari persist(@RequestBody Incasari incasari) {
        return incasariService.saveIncasari(incasari);
    }


//    @PostMapping(value = "/add")
//    public Incasari add(@RequestParam Map<String, String> params, @RequestBody Incasari incasari ){
//        return incasariService.save(params, incasari);
//    }


    @GetMapping(value = "/searchAll")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Incasari> searchAll() {
        return incasariService.searchAll();
    }


    @GetMapping(value = "/search/data/{data}")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Incasari> searchByData(@PathVariable String data) {
        return incasariService.searchByData(data);
    }

    @GetMapping(value = "/search/furnizor/{furnizor}")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Incasari> searchByFurnizor(@PathVariable String furnizor) {
        return incasariService.searchByFurnizor(furnizor);
    }

    @GetMapping(value = "/search/monthandyear")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Incasari> searchByMonthAndYear(@RequestParam String month, @RequestParam String year) {
        return incasariService.searchByMonthAndYear(month, year);
    }

    @GetMapping(value = "/search/betweenData")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Incasari> searchByBetweenData(@RequestParam String firstDate, @RequestParam String secondDate) {
        return incasariService.searchByBetweenData(firstDate, secondDate);
    }

    @GetMapping(value = "/search/betweenSumaTotala")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Incasari> searchByBetweenSumaTotala(@RequestParam String firstDate, @RequestParam String secondDate, @RequestParam double firstDate1, @RequestParam double secondDate1) {
        return incasariService.searchByBetweenSumaTotala(firstDate, secondDate, firstDate1, secondDate1);
    }



    @GetMapping(value = "/test")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Incasari> testMethod(@RequestParam Map<String, String> params) throws ParseException {

        if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without furnizor, data1, data2, sum2, stare");
            return incasariService
                    .searchWithoutFunrizorAndDatesAndSum2AndStare(Double.valueOf(params.get("sumaTotala1")));
        }
        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala1")&& (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without furnizor, data1, data2, sum1, stare");
            return incasariService
                    .searchWithoutFunrizorAndDatesAndSum1AndStare(Double.valueOf(params.get("sumaTotala2")));
        }

        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1") && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without furnizor, data1, sum1, sum2, stare");
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));
            return incasariService
                    .searchWithoutFurnizorAndData1AndSumsAndStare(format2);
        }

        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1") && null == params.get("sumaTotala2")){
            System.out.print("without furnizor, data1, data2, sum1, sum2");

            return incasariService.
                    searchWithoutFurnizorAndDatesAndSums(params.get("stare"));
        }

        // data2


        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1") && null == params.get("sumaTotala2")&& (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without furnizor, data2, sum1, sum2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutFurnizorAndData2AndSumsAndStare(format1);
        }

//        De aici
        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without furnizor, data1, data2, sum2");
            return incasariService
                    .searchWithoutFunrizorAndDatesAndSum2(Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without furnizor, data1, data2, sum1");
            return incasariService
                    .searchWithoutFunrizorAndDatesAndSum1(Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }
        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1") && null == params.get("sumaTotala2")){
            System.out.println("without furnizor, data1, sum1, sum2");
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));
            return incasariService
                    .searchWithoutFurnizorAndData1AndSums(format2, params.get("stare"));
        }

        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1") && null == params.get("sumaTotala2")){
            System.out.println("without furnizor, data2, sum1, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutFurnizorAndData2AndSums(format1, params.get("stare"));
        }
//        pana aici

        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without furnizor, data1, sum2, stare");
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));
            return incasariService
                    .searchWithoutFurnizorAndData1AndSum2AndStare(format2, Double.valueOf(params.get("sumaTotala1")));
        }
    //data2
        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without furnizor, data2, sum2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutFurnizorAndData2AndSum2AndStare(format1, Double.valueOf(params.get("sumaTotala1")));
        }

        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1") && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without furnizor, data1, sum1, stare");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutFurnizorAndData1AndSum1AndStare(format2, Double.valueOf(params.get("sumaTotala2")));
        }
    //data2
        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1") && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without furnizor, data2, sum1, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutFurnizorAndData2AndSum1AndStare(format1, Double.valueOf(params.get("sumaTotala2")));
        }


        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println(params.get("data1, data2, furnizor, stare"));
            return incasariService
                    .searchWithoutFurnizorAndDatesAndStare(Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

//        aici(22.02.2021)
        else if (((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("sumaTotala2")) && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without data2, sum1, sum2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutData2AndSumsAndStare(params.get("furnizor"), format1);
        }
        else if(((null == params.get("data1")) || params.get("data1").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("sumaTotala2")) && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without data1, sum1, sum2, stare");
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));
            return incasariService
                    .searchWithoutData1AndSumsAndStare(params.get("furnizor"), format2);
        }

//        pana aici(22.02.2021)

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("sumaTotala2")) && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data1, data2, sum2, stare");
            return incasariService
                    .searchWithoutDatesAndSum2AndStare(params.get("furnizor"), Double.valueOf(params.get("sumaTotala1")));
        }
        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data1, data2, sum1, stare");
            return incasariService
                    .searchWithoutDatesAndSum1AndStare(params.get("furnizor"), Double.valueOf(params.get("sumaTotala2")));
        }

        else if (null == params.get("sumaTotala1") && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without sum1, sum2, furnizor, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            System.out.println(format1);
            System.out.println(format2);

            return incasariService
                    .searchWithoutFurnizorAndSumAndStare(format1, format2);
        }

        else if((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("sumaTotala2"))){
            System.out.println("without data1, data2, sum1,sum2");

            return incasariService.
                    searchWithoutDatesAndSums(params.get("furnizor"), params.get("stare"));
        }


        //        DE AICIIIIIII

        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala2")){
            System.out.println("without furnizor, data1, sum2");
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));
            return incasariService
                    .searchWithoutFurnizorAndData1AndSum2(format2, Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("furnizor") || params.get("furnizor").isEmpty())){
            System.out.println("without furnizor, data2, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutFurnizorAndData2AndSum2(format1, Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1")){
            System.out.println("without furnizor, data1, sum1");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutFurnizorAndData1AndSum1(format2, Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1")){
            System.out.println("without furnizor, data2, sum1");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutFurnizorAndData2AndSum1(format1, Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && ((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("furnizor") || params.get("furnizor").isEmpty())) {
            System.out.println(params.get("data1, data2, furnizor"));
            return incasariService
                    .searchWithoutFurnizorAndDates(Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if (((null == params.get("data2")) || params.get("data2").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("sumaTotala2"))){
            System.out.println("without data2, sum1, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutData2AndSums(params.get("furnizor"), format1, params.get("stare"));
        }

        else if(((null == params.get("data1")) || params.get("data1").isEmpty()) && (null == params.get("sumaTotala1")) && (null == params.get("sumaTotala2"))){
            System.out.println("without data1, sum1, sum2");
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));
            return incasariService
                    .searchWithoutData1AndSums(params.get("furnizor"), format2, params.get("stare"));
        }

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("sumaTotala2"))) {
            System.out.println("without data1, data2, sum2");
            return incasariService
                    .searchWithoutDatesAndSum2(params.get("furnizor"), Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without data1, data2, sum1");
            return incasariService
                    .searchWithoutDatesAndSum1(params.get("furnizor"), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if (null == params.get("sumaTotala1") && (null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without sum1, sum2, furnizor");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            System.out.println(format1);
            System.out.println(format2);

            return incasariService
                    .searchWithoutFurnizorAndSum(format1, format2, params.get("stare"));
        }
//        PANA AIIICIIII



        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without furnizor, data1, stare");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutFurnizorAndData1AndStare(format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }
    //data2
        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without furnizor, data2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));


            return incasariService
                    .searchWithoutFurnizorAndData2AndStare(format1, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

        else if((null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data1. sum2, stare");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return incasariService
                    .searchWithoutData1AndSum2AndStare(params.get("furnizor"), format2, Double.valueOf(params.get("sumaTotala1")));
        }
    //data2
        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data2, sum2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutData2AndSum2AndStare(params.get("furnizor"), format1, Double.valueOf(params.get("sumaTotala1")));
        }

        else if((null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data1. sum1, stare");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutData1AndSum1AndStare(params.get("furnizor"), format2, Double.valueOf(params.get("sumaTotala2")));
        }
    //data2
        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data2, sum1, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutData2AndSum1AndStare(params.get("furnizor"), format1, Double.valueOf(params.get("sumaTotala2")));
        }
//        ------------------------------------------------------



        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without data1, data2, stare");
            return incasariService
                    .searchWithoutDatesAndStare(params.get("furnizor"), Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

        else if ((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without sum2, furnizor, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutFurnizorAndSum2AndStare(format1, format2,Double.valueOf(params.get("sumaTotala1")));
        }
        else if ((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala1") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without sum1, furnizor, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutFurnizorAndSum1AndStare(format1, format2,Double.valueOf(params.get("sumaTotala2")));
        }


        else if (null == params.get("sumaTotala1") && null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without sum1, sum2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return incasariService
                    .searchWithoutSumAndStare(params.get("furnizor"), format1, format2);
        }

        else if (null == params.get("furnizor") || params.get("furnizor").isEmpty() && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without furnizor, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            System.out.println(format1);
            System.out.println(format2);

            return incasariService
                    .searchWithoutFurnizorAndStare(format1, format2,
                            Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

//        DE AICIIII
        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data1") || params.get("data1").isEmpty())){
            System.out.println("without furnizor, data1");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutFurnizorAndData1(format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty())){
            System.out.println("without furnizor, data2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));


            return incasariService
                    .searchWithoutFurnizorAndData2(format1, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if((null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without data1. sum2");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return incasariService
                    .searchWithoutData1AndSum2(params.get("furnizor"), format2, Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without data2, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutData2AndSum2(params.get("furnizor"), format1, Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if((null == params.get("data1") || params.get("data1").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without data1. sum1");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutData1AndSum1(params.get("furnizor"), format2, Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if((null == params.get("data2") || params.get("data2").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without data2, sum1");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));

            return incasariService
                    .searchWithoutData2AndSum1(params.get("furnizor"), format1, Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if ((null == params.get("data1") || params.get("data1").isEmpty()) && (null == params.get("data2") || params.get("data2").isEmpty())) {
            System.out.println("without data1, data2");
            return incasariService
                    .searchWithoutDates(params.get("furnizor"), Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if ((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala2")) {
            System.out.println("without sum2, furnizor");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutFurnizorAndSum2(format1, format2,Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }

        else if ((null == params.get("furnizor") || params.get("furnizor").isEmpty()) && null == params.get("sumaTotala1")) {
            System.out.println("without sum1, furnizor");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutFurnizorAndSum1(format1, format2,Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }
        else if (null == params.get("furnizor") || params.get("furnizor").isEmpty()) {
            System.out.println("without furnizor");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            System.out.println(format1);
            System.out.println(format2);

            return incasariService
                    .searchWithoutFurnizor(format1, format2,
                            Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if (null == params.get("sumaTotala1") && null == params.get("sumaTotala2")) {
            System.out.println("without sum1, sum2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return incasariService
                    .searchWithoutSum(params.get("furnizor"), format1, format2, params.get("stare"));
        }

//        PANA AICIIIII

        else if (null == params.get("sumaTotala2") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without sumaTotala2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutsumaTotala2AndStare(params.get("furnizor"), format1, format2, Double.valueOf(params.get("sumaTotala1")));
        }

        else if (null == params.get("sumaTotala1") && (null == params.get("stare") || params.get("stare").isEmpty())) {
            System.out.println("without sumaTotala1, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutsumaTotala1AndStare(params.get("furnizor"), format1, format2, Double.valueOf(params.get("sumaTotala2")));
        }

//        ---------------------------------------------------------------------

        else if (null == params.get("data1") || params.get("data1").isEmpty() && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without data1, stare");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return incasariService
                    .searchWithoutData1AndStare(params.get("furnizor"), format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }
    //data2

        else if ((null == params.get("data2") || params.get("data2").isEmpty()) && (null == params.get("stare") || params.get("stare").isEmpty())){
            System.out.println("without data2, stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));


            return incasariService
                    .searchWithoutData2AndStare(params.get("furnizor"), format1, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

//        DE AICIIIIII !!!
        else if (null == params.get("sumaTotala2") ) {
            System.out.println("without sumaTotala2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutsumaTotala2(params.get("furnizor"), format1, format2, Double.valueOf(params.get("sumaTotala1")), params.get("stare"));
        }


        else if (null == params.get("sumaTotala1")) {
            System.out.println("without sumaTotala1");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService
                    .searchWithoutsumaTotala1(params.get("furnizor"), format1, format2, Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if (null == params.get("data1") || params.get("data1").isEmpty()){
            System.out.println("without data1");

            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));


            return incasariService
                    .searchWithoutData1(params.get("furnizor"), format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }

        else if (null == params.get("data2") || params.get("data2").isEmpty()){
            System.out.println("without data2");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));


            return incasariService
                    .searchWithoutData2(params.get("furnizor"), format1, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
        }
//        PANA AIIICIIII !!!

        else if (null == params.get("stare") || params.get("stare").isEmpty()){
            System.out.println("without stare");

            String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
            String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

            return incasariService.
                    searchWithoutStare(params.get("furnizor"), format1, format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")));
        }

        String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
        String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

        return incasariService.searchAllParams(params.get("furnizor"), format1, format2, Double.valueOf(params.get("sumaTotala1")), Double.valueOf(params.get("sumaTotala2")), params.get("stare"));
    }



    @GetMapping(value = "/search/year/{year}")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Incasari> searchByYear(@PathVariable String year) {
        return incasariService.searchByYear(year);
    }


    @GetMapping(value = "/search/number/{number}")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Incasari> searchByNumar(@PathVariable String number) {
        return incasariService.searchByNumber(number);
    }


    @PutMapping(value = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public Incasari update(@PathVariable long id, @RequestBody Incasari incasari) {
        return incasariService.update(id, incasari);
    }

//    @GetMapping(value = "/countAll/{furnizor}")
//    public long countFurnizor(@PathVariable String furnizor){
//        return incasariService.countFurnizor(furnizor);
//    }
//
//    @GetMapping(value = "/countAl")
//    public long countAll(){
//        return incasariService.countAll();
//    }

    @GetMapping(value = "/countAll")
    public long count(){
        return incasariService.count();
    }



    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void deleteId(@PathVariable long id) {
        incasariService.deleteId(id);
    }

        //3
//    @GetMapping(value = "/searchAllTVA")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
//    public double searchByTVA() {
//        return incasariService.calculateTotalTVA();
//    }

        //1
//    @GetMapping(value = "/searchAllTotal")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
//    public double searchByTotal() {
//        return incasariService.calculateSumaTotala();
//    }

        //2
//    @GetMapping(value = "/searchAllFaraTVA")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
//    public double searchByFaraTVA() {
//        return incasariService.calculateSumaFaraTVA();
//    }


    @GetMapping(value = "/searchByDateTVA/{data}")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double searchByDateTVA(@PathVariable String data) {

        return incasariService.calculateTVAByDate(data);
    }

    @GetMapping(value = "/calculateTotalByMonth/{month}")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculateTotalByMonth(@PathVariable String month) {
        return incasariService.calculateTotalByMonth(month);
    }


    @GetMapping(value = "/calculateTotalByYear/{year}")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculateTotalByYear(@PathVariable String year) {

        return incasariService.calculateTotalByYear(year);
    }

        //5
//    @GetMapping(value = "/calculateTotalByMonthAndYear")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
//    public double calculateTotalByMonthAndYear(@RequestParam String month, @RequestParam String year) {
//        return incasariService.calculateTotalByMonthAndYear(month, year);
//    }

        //4
//    @GetMapping(value = "/calculateTotalByDataBetweenData")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
//    public double calculateTotalByDataBetweenData(@RequestParam String firstDate, @RequestParam String secondDate) {
//        return incasariService.calculateTotalByDataBetweenData(firstDate, secondDate);
//    }

//    @GetMapping(value = "/calculateTotalByDataBetweenData/{data1}-{data2}")
//    public double calculateTotalByDataBetweenData(@PathVariable String data1, @PathVariable String data2 ) {
//        return incasariService.calculateTotalByDataBetweenData(data1, data2);
//    }


    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public Incasari updaterows(@RequestBody Incasari incasari) {

        return incasariService.updaterows(incasari);
    }

    @PostMapping("/addFact/{detalii}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addFact(@PathVariable String detalii){
        incasariServiceImpl.addFact(detalii);
        incasariServiceImpl.deleteFact(detalii);
    }


}
