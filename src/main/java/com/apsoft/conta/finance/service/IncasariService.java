package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.repository.IncasariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IncasariService {

    Incasari saveIncasari(Incasari incasari);
    void delete(long id);
    List<Incasari> viewAllIncasari();
}