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
        //Llistem tots els proveidors i el pasem per atribut al crud_list_provider.html
        List<Provider> listProviders = providerService.listProviders();
        model.addAttribute("listProviders", listProviders);
        return ("crud_list_provider");
    }


    //Borrem el proveidor que li pasem per id
    @GetMapping("/delete/provider{id}")
    public String deleteClient(Provider provider){
        providerService.deleteProvider(provider);
        return "redirect:/crud_provider";
    }

    //Formulari per crear un nou proveidor
    @GetMapping("/formProvider")
    public String createProviderForm(Model model) {
        model.addAttribute("provider", new Provider());
        return "formProvider";
    }

    //Agafa l'informacio del formulari i el guarda a la base de dades
    @PostMapping("/saveProvider")
    public String saveProvider(Provider provider, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "formProvider";
        }
        providerService.addProvider(provider);
        return "redirect:/crud_provider";
    }

    //Agafa el proveidor seleccionat i mostra la seva informacio en el fitxer html
    @GetMapping("/edit/provider{id}")
    public String editProvider(Provider provider, Model model) {
        Provider p = providerService.findProvider(provider);
        model.addAttribute("provider", p);
        return "formProvider";
    }
}
