package com.copernic.cat.erp.sales_4_us;

import com.copernic.cat.erp.sales_4_us.controllers.CRUDProviderController;
import com.copernic.cat.erp.sales_4_us.models.Provider;
import com.copernic.cat.erp.sales_4_us.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.copernic.cat.erp.sales_4_us.controllers.CRUDClientController;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class CRUDProvidersTest {

    private CRUDProviderController providerTest;

    @Test
    public void createUser(Provider provider, Errors errors) {
        provider.setName("Lucas");
        provider.setDirection("Terrassa, Barcelona");
        provider.setEmail("padilla.hidalgo.lucas@alumnat.copernic.cat");
        provider.setId(1);
        provider.setPhone(648789528);
        provider.setWebsite("copernic.cat");

        providerTest.saveProvider(provider, errors);
        Assert.notNull(provider, "El proveïdor s'ha creat correctament.");
    }

    @Test
    public void deleteUser() {
    }

}
