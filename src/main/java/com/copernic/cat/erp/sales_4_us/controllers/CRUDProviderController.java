package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.Provider;
import com.copernic.cat.erp.sales_4_us.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CRUDProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/crud_provider")
    public String inici(Model model){
        List<Provider> listProviders = providerService.listProviders();
        model.addAttribute("listProviders", listProviders);
        return ("crud_list_provider");
    }



    @GetMapping("/delete/provider{id}")
    public String deleteClient(Provider provider){
        providerService.deleteProvider(provider);
        return "redirect:/crud_provider";
    }


    @GetMapping("/formProvider")
    public String createProviderForm(Model model) {
        model.addAttribute("provider", new Provider());
        return "formProvider";
    }

    @PostMapping("/saveProvider")
    public String saveProvider(Provider provider, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "formProvider";
        }
        providerService.addProvider(provider);
        providerService.addProvider(provider);
        return "redirect:/crud_provider";
    }

    @GetMapping("/edit/provider{id}")
    public String editProvider(Provider provider, Model model) {
        Provider p = providerService.findProvider(provider);
        model.addAttribute("provider", p);
        return "formProvider";
    }
}
