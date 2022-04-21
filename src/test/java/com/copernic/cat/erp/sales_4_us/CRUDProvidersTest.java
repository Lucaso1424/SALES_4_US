package com.copernic.cat.erp.sales_4_us;

import com.copernic.cat.erp.sales_4_us.models.Provider;
import com.copernic.cat.erp.sales_4_us.repository.ProviderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CRUDProvidersTest {

    @Autowired
    private ProviderRepository providerRepository;

    @Test
    public void createProvider() {
        Provider provider = new Provider();

        provider.setName("Lucas");
        provider.setDirection("Terrassa, Barcelona");
        provider.setEmail("padilla.hidalgo.lucas@alumnat.copernic.cat");
        provider.setId(2);
        provider.setPhone(648789528);
        provider.setWebsite("copernic.cat");

        Provider savedProvider = providerRepository.save(provider);
        Assert.notNull(savedProvider, "El provider s'ha desat correctament");
    }

    @Test
    public void findProviderById() {
        Integer id = 2;
        Optional<Provider> provider = providerRepository.findById(id);

        System.out.println(provider);

        Assert.isTrue(provider.get().getId().equals(id));
    }

    @Test
    public void listAllProviders() {
        List<Provider> providerList = (List<Provider>) providerRepository.findAll();

        for (Provider provider : providerList) {
            System.out.println(provider);
        }

        Assert.notEmpty(providerList, "Hi han providers a la BBDD.");
    }

    @Test
    public void deleteProvider() {
        Integer id = 2;

        providerRepository.deleteById(2);

        boolean deletedProvider = providerRepository.findById(id).isEmpty();

        Assert.isTrue(deletedProvider, "S'ha eliminat el provider.");
    }

}