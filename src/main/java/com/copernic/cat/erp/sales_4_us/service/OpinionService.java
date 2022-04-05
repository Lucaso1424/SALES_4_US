package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.models.Opinion;
import com.copernic.cat.erp.sales_4_us.repository.OpinionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author lucas
 */
@Service("opinionService")
@Slf4j
public class OpinionService {

    // Importació d'OpinionRepository
    @Autowired
    private OpinionRepository opinionRepository;

    // Mètode @Transactional, que retorna una llista de totes les opinions
    // d'un producte
    @Transactional(readOnly = true)
    public List<Opinion> listOpinion() {
        return opinionRepository.findAll();
    }

    // Mètode per afegir una opinió al OpinionRepository, realitzant un save()
    public void addOpinion(Opinion opinion) {
        opinionRepository.save(opinion);
    }
}