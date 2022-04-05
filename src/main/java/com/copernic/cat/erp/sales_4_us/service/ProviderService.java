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

    //Llistem tots els proveidors
    @Transactional(readOnly = true)
    public List<Provider> listProviders(){
        return providerRepository.findAll();
    }

    //Eliminem el proveidor seleccionat
    @Transactional
    public void deleteProvider(Provider provider) {
        providerRepository.delete(provider);
    }

    //Guardem un proveidor a la base de dades
    @Transactional
    public void addProvider(Provider provider) {
        providerRepository.save(provider);
    }

    //Busquem un proveidor per id
    @Transactional(readOnly = true)
    public Provider findProvider(Provider provider) {
        return providerRepository.findById(provider.getId()).orElse(null);
    }
}
