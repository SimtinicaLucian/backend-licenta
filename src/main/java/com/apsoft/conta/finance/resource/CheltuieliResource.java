package com.apsoft.conta.finance.resource;


import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.service.CheltuieliService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

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
}
