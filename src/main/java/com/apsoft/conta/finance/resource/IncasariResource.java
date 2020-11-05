package com.apsoft.conta.finance.resource;


import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.service.IncasariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/incasari")
public class IncasariResource {

    @Autowired
    private IncasariService incasariService;


    @PostMapping(value = "/add")
    public Incasari persist(@RequestBody final  Incasari incasari){
        return incasariService.saveIncasari(incasari);


    }

    @GetMapping(value = "/searchAll")
    public List<Incasari> searchAll(){
        return incasariService.searchAll();
    }

    @GetMapping(value = "/search/data/{data}")
    public List<Incasari> searchByData(@PathVariable  String data){
        return incasariService.searchByData(data);
    }

    @GetMapping(value = "/search/furnizor/{furnizor}")
    public List<Incasari> searchByFurnizor(@PathVariable  String furnizor){
        return incasariService.searchByFurnizor(furnizor);
    }

    @GetMapping(value ="/search/number/{number}")
    public List<Incasari> searchByNumar(@PathVariable  int number){
        return incasariService.searchByNumar(number);
    }

    @PutMapping(value ="/update/{number}")
    public Incasari update(@PathVariable int number, @RequestBody Incasari incasari){
        return incasariService.update(number, incasari);

    }
}
