package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.models.Provider;
import com.copernic.cat.erp.sales_4_us.repository.ProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("providerService")
@Slf4j
public class ProviderService {
    @Autowired
    private ProviderRepository providerRepository;

    @Transactional
    public List<Provider> listProviders(){
        return (List<Provider>) providerRepository.findAll();
    }

    @Transactional
    public void deleteProvider(Provider provider) {
        providerRepository.delete(provider);
    }

    @Transactional
    public void addProvider(Provider provider) {
        providerRepository.save(provider);
    }

    @Transactional(readOnly = true)
    public Provider findProvider(Provider provider) {
        return providerRepository.findById(provider.getId()).orElse(null);
    }
}
