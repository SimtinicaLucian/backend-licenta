package com.apsoft.conta.finance.service;

import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.finance.repository.IncasariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncasariServiceImpl implements IncasariService {

    @Autowired
    private IncasariRepository incasariRepository;


    @Override
    public Incasari saveIncasari(Incasari incasari) {
        return incasariRepository.save(incasari);
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Incasari> viewAllIncasari() {
        return incasariRepository.findAll();
    }
}
