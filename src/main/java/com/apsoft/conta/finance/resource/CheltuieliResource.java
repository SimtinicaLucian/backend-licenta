package com.apsoft.conta.finance.resource;


import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.service.CheltuieliService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/cheltuieli")
public class CheltuieliResource {

    @Autowired
    private CheltuieliService cheltuieliService;


    @PostMapping(value = "/add")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public Cheltuieli cheltuieli(@RequestBody Cheltuieli cheltuieli){
        return cheltuieliService.saveCheltuieli(cheltuieli);
    }

    @GetMapping(value = "/calculareSumaTotalaCuTVA")
    public double calculareSumaTotalaCuTVA(){
        return cheltuieliService.calculareSumaTotalaCuTVA();
    }

    @GetMapping(value = "/rest")
    public double rest(){
        return cheltuieliService.rest();
    }

    @GetMapping(value = "/culculareSumaTotalaCuTVADataMinDataMax")
    public double culculareSumaTotalaCuTVADataMinDataMax(@RequestParam String data1, @RequestParam String data2) {
        return cheltuieliService.culculareSumaTotalaCuTVADataMinDataMax(data1, data2);
    }


    @GetMapping(value = "/calculareSumaTotalaCuTVAMonthYear")
    public double calculareSumaTotalaCuTVAMonthYear(@RequestParam String month, @RequestParam String year) {
        return cheltuieliService.calculareSumaTotalaCuTVAMonthYear(month, year);
    }
}
