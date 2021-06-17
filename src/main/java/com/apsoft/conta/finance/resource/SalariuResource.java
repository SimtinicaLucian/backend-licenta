package com.apsoft.conta.finance.resource;


import com.apsoft.conta.finance.persistence.Cheltuieli;
import com.apsoft.conta.finance.persistence.Salariu;
import com.apsoft.conta.finance.service.SalariuService;
import com.apsoft.conta.finance.service.SalariuServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/salariu")
public class SalariuResource {

    @Autowired
    private SalariuService salariuService;

    @Autowired
    private SalariuServiceImpl salariuServiceImpl;

    @PostMapping(value = "/add")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public Salariu persist(@RequestBody Salariu salariu) throws ParseException {
        return salariuService.saveSalariu(salariu);
    }

    @GetMapping(value = "/searchAll")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Salariu> searchAll() throws ParseException {
        return salariuService.searchAll();
    }

    @DeleteMapping(value = "/delete/{id}")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void deleteId(@PathVariable long id) {
        salariuService.deleteId(id);
    }


    @PutMapping(value = "/update/{id}")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public Salariu update(@PathVariable long id, @RequestBody Salariu salariu) throws ParseException {
        return salariuService.update(id, salariu);
    }

    @GetMapping(value = "/search/id/{id}")
//    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Salariu> searchById(@PathVariable long id) {
        return salariuService.searchById(id);
    }
}
