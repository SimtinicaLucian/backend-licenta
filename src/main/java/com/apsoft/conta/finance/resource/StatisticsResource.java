package com.apsoft.conta.finance.resource;


import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.service.CheltuieliService;
import com.apsoft.conta.finance.service.IncasariUtils;
import com.apsoft.conta.finance.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/statistics")
public class StatisticsResource {

    @Autowired
    private StatisticsService statisticsService;

    String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));


    @GetMapping(value = "/incasari/calculareSumaTotalaCuTVA")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaCuTVA_Incasari(){
        return statisticsService.calculareSumaTotalaCuTVA_Incasari();
    }


    @GetMapping(value = "/cheltuieli/calculareSumaTotalaCuTVA")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaCuTVA_Cheltuieli(){
        return statisticsService.calculareSumaTotalaCuTVA_Cheltuieli();
    }

    @GetMapping(value = "/incasari/calculareSumaTotalaFaraTVA")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaFaraTVA_Incasari(){
        return statisticsService.calculareSumaTotalaFaraTVA_Incasari();
    }

    @GetMapping(value = "/cheltuieli/calculareSumaTotalaFaraTVA")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaFaraTVA_Cheltuieli(){
        return statisticsService.calculareSumaTotalaFaraTVA_Cheltuieli();
    }

    @GetMapping(value = "/incasari/calculareSumaTotalaTVA")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaTVA_Incasari(){
        return statisticsService.calculareSumaTotalaTVA_Incasari();
    }

    @GetMapping(value = "/cheltuieli/calculareSumaTotalaTVA")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaTVA_Cheltuieli(){
        return statisticsService.calculareSumaTotalaTVA_Cheltuieli();
    }

    @GetMapping(value = "/incasari/calculareSumaTotalaCuTVADataMinDataMax")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaCuTVADataMinDataMax_Incasari(@RequestParam Map<String, String> params) throws ParseException {
        String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
        String format2 = IncasariUtils.changeDateFormat(params.get("data2"));

        System.out.println(format1);

        return statisticsService.calculareSumaTotalaCuTVADataMinDataMax_Incasari(format1, format2);
    }

    @GetMapping(value = "/cheltuieli/calculareSumaTotalaCuTVADataMinDataMax")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaCuTVADataMinDataMax_Cheltuieli(@RequestParam Map<String, String> params) throws ParseException {
        String format1 = IncasariUtils.changeDateFormat(params.get("data1"));
        String format2 = IncasariUtils.changeDateFormat(params.get("data2"));
        return statisticsService.calculareSumaTotalaCuTVADataMinDataMax_Cheltuieli(format1, format2);
    }

    @GetMapping(value = "/incasari/calculareSumaTotalaFaraTVADataMinDataMax")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaFaraTVADataMinDataMax_Incasari(@RequestParam String data1, @RequestParam String data2) {
        return statisticsService.calculareSumaTotalaFaraTVADataMinDataMax_Incasari(data1, data2);
    }

    @GetMapping(value = "/cheltuieli/calculareSumaTotalaFaraTVADataMinDataMax")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaFaraTVADataMinDataMax_Cheltuieli(@RequestParam String data1, @RequestParam String data2) {
        return statisticsService.calculareSumaTotalaFaraTVADataMinDataMax_Cheltuieli(data1, data2);
    }

    @GetMapping(value = "/incasari/calculareSumaTotalaTVADataMinDataMax")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaTVADataMinDataMax_Incasari(@RequestParam String data1, @RequestParam String data2) {
        return statisticsService.calculareSumaTotalaTVADataMinDataMax_Incasari(data1, data2);
    }

    @GetMapping(value = "/cheltuieli/calculareSumaTotalaTVADataMinDataMax")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaTVADataMinDataMax_Cheltuieli(@RequestParam String data1, @RequestParam String data2) {
        return statisticsService.calculareSumaTotalaTVADataMinDataMax_Cheltuieli(data1, data2);
    }

    @GetMapping(value = "/incasari/calculareSumaTotalaCuTVAMonthAndYear")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaCuTVAMonthAndYear_Incasari(@RequestParam Map<String, String> params) {
        return statisticsService.calculareSumaTotalaCuTVAMonthAndYear_Incasari(params.get("month"), params.get("year"));
    }

    @GetMapping(value = "/cheltuieli/calculareSumaTotalaCuTVAMonthAndYear")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaCuTVAMonthAndYear_Cheltuieli(@RequestParam Map<String, String> params) {
        return statisticsService.calculareSumaTotalaCuTVAMonthAndYear_Cheltuieli(params.get("month"), params.get("year"));
    }

    @GetMapping(value = "/incasari/calculareSumaTotalaFaraTVAMonthAndYear")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaFaraTVAMonthAndYear_Incasari(@RequestParam String month, @RequestParam String year) {
        return statisticsService.calculareSumaTotalaFaraTVAMonthAndYear_Incasari(month, year);
    }

    @GetMapping(value = "/cheltuieli/calculareSumaTotalaFaraTVAMonthAndYear")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaFaraTVAMonthAndYear_Cheltuieli(@RequestParam String month, @RequestParam String year) {
        return statisticsService.calculareSumaTotalaFaraTVAMonthAndYear_Cheltuieli(month, year);
    }

    @GetMapping(value = "/incasari/calculareSumaTotalaTVAMonthAndYear")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaTVAMonthAndYear_Incasari(@RequestParam String month, @RequestParam String year) {
        return statisticsService.calculareSumaTotalaTVAMonthAndYear_Incasari(month, year);
    }

    @GetMapping(value = "/cheltuieli/calculareSumaTotalaTVAMonthAndYear")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaTVAMonthAndYear_Cheltuieli(@RequestParam String month, @RequestParam String year) {
        return statisticsService.calculareSumaTotalaTVAMonthAndYear_Cheltuieli(month, year);
    }

    @GetMapping(value = "/incasari/calculareSumaTotalaCuTVAPerYear")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaCuTVAPerYear_Incasari(@RequestParam Map<String, String> params) {
        return statisticsService.calculareSumaTotalaCuTVAPerYear_Incasari(params.get("year"));
    }

    @GetMapping(value = "/cheltuieli/calculareSumaTotalaCuTVAPerYear")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaCuTVAPerYear_Cheltuieli(@RequestParam Map<String, String> params) {
        return statisticsService.calculareSumaTotalaCuTVAPerYear_Cheltuieli(params.get("year"));
    }

    @GetMapping(value = "/incasari/calculareSumaTotalaFaraTVAPerYear")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaFaraTVAPerYear_Incasari(@RequestParam String year) {
        return statisticsService.calculareSumaTotalaFaraTVAPerYear_Incasari(year);
    }

    @GetMapping(value = "/cheltuieli/calculareSumaTotalaFaraTVAPerYear")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaFaraTVAPerYear_Cheltuieli(@RequestParam String year) {
        return statisticsService.calculareSumaTotalaFaraTVAPerYear_Cheltuieli(year);
    }

    @GetMapping(value = "/incasari/calculareSumaTotalaTVAPerYear")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaar_Incasari(@RequestParam String year) {
        return statisticsService.calculareSumaTotalaTVAPerYear_Incasari(year);
    }

    @GetMapping(value = "/cheltuieli/calculareSumaTotalaTVAPerYear")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double calculareSumaTotalaTVAPerYear_Cheltuieli(@RequestParam String year) {
        return statisticsService.calculareSumaTotalaTVAPerYear_Cheltuieli(year);
    }


    @GetMapping(value = "/profit_Total")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double Profit_Total(){
        return statisticsService.Profit_Total();
    }

    @GetMapping(value = "/profit_Lunar")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double Profit_Lunar(@RequestParam Map<String, String> params){
        return statisticsService.Profit_Lunar(params.get("month"), params.get("year"));
    }

    @GetMapping(value = "/profit_Anual")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double Profit_Anual(@RequestParam Map<String, String> params){
        return statisticsService.Profit_Anual(params.get("year"));
    }

    @GetMapping(value = "/sold")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double sold(){
        return statisticsService.sold();
    }

    @GetMapping(value = "/cifraAfaceri")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double CifraAfaceri(@RequestParam Map<String, String> params){
        return statisticsService.CifraAfaceri(params.get("year"));
    }

    @GetMapping(value = "/incasari/intarziate")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public long Incasari_Intarziate(){
        return statisticsService.Incasari_Intarziate();
    }

    @GetMapping(value = "/cheltuieli/intarziate")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public long Cheltuieli_Intarziate(){
        return statisticsService.Cheltuieli_Intarziate();
    }

    @GetMapping(value = "/salariu/intarziate")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public long Salarii_Intarziate(){
        return statisticsService.Salarii_Intarziate();
    }

    @GetMapping(value = "/incasari/intarziate/Rest_DeIncasat")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double Incasari_Intarziate_Rest_DeIncasat(){
        return statisticsService.Incasari_Intarziate_Rest_DeIncasat();
    }

    @GetMapping(value = "/cheltuieli/intarziate/Rest_DeAchitat")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double Cheltuieli_Intarziate_Rest_DeAchitat(){
        return statisticsService.Cheltuieli_Intarziate_Rest_DeAchitat();
    }

    @GetMapping(value = "/salariu/intarziate/Rest_DeAchitat")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double Salarii_Intarziate_Rest_DeAchitat(){
        return statisticsService.Salarii_Intarziate_Rest_DeAchitat();
    }

    @GetMapping(value = "/BugetulDeStat_TVA")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double BugetulDeStat_TVA(@RequestParam Map<String, String> params){
        return statisticsService.BugetulDeStat_TVA(params.get("month"), params.get("year"));
    }

    @GetMapping(value = "/test1")
    public double calculareSumaTVAMonthAndYear_LaIncasare_Incasari(@RequestParam Map<String, String> params){
        return statisticsService.calculareSumaTVAMonthAndYear_LaIncasare_Incasari(params.get("month"), params.get("year"));
    }

    @GetMapping(value = "/BugetulDeStat_TVAIncasare")
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public double BugetulDeStat_TVA_LaIncasare(@RequestParam Map<String, String> params){
        return statisticsService.BugetulDeStat_TVAIncasare(params.get("month"), params.get("year"));
    }



//    @GetMapping(value = "/BugetulDeStat_TVAIncasare")
//    public double BugetulDeStat_TVAIncasare(@RequestParam Map<String, String> params, @RequestBody Incasari incasari){
//        return statisticsService.BugetulDeStat_TVAIncasare(params.get("month"), params.get("year"), incasari);
//    }





}
