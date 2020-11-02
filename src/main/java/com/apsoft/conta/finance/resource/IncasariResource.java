package com.apsoft.conta.finance.resource;


import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.service.IncasariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/incasari/")
public class IncasariResource {

    @Autowired
    private IncasariService incasariService;


    @PostMapping(value = "/add")
    public Incasari persist(@RequestBody final  Incasari incasari){
        return incasariService.saveIncasari(incasari);


    }
}
