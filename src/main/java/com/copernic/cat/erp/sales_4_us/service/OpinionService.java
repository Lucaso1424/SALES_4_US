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

    @Autowired
    private OpinionRepository opinionRepository;

    @Transactional(readOnly = true)
    public List<Opinion> listOpinion() {
        return opinionRepository.findAll();
    }

    public void addOpinion(Opinion opinion) {
        opinionRepository.save(opinion);
    }
}