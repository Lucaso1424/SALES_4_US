package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.Category;
import com.copernic.cat.erp.sales_4_us.models.Product;
import com.copernic.cat.erp.sales_4_us.models.Provider;
import com.copernic.cat.erp.sales_4_us.service.CategoryService;
import com.copernic.cat.erp.sales_4_us.service.ProductService;
import com.copernic.cat.erp.sales_4_us.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class CRUDProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/crud_product")
    public String inici(Model model) {
        List<Product> listProducts = productService.listProduct();
        model.addAttribute("listProducts", listProducts);
        return "crud_list_product";
    }

    @GetMapping("/delete/product/{id}")
    public String deleteProduct(Product product) {
        productService.deleteProduct(product);
        return "redirect:/crud_product";
    }

    @GetMapping("/formProduct")
    public String createProductForm(Model model) {
        List<Provider> providers = providerService.listProviders();
        List<Category> categories = categoryService.listCategories();
        model.addAttribute("product", new Product());
        model.addAttribute("listProviders", providers);
        model.addAttribute("listCategories", categories);
        return "formProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute(name = "product") Product product,
            Errors errors,
            RedirectAttributes msg,
            @RequestParam("fileImage") MultipartFile multipartFile
    ) throws IOException {

        if (errors.hasErrors()) {
            System.out.println(errors);
            return "formProduct";
        }

        String fileName = null;
        if (multipartFile.getOriginalFilename() == null) {
            fileName = "default_product.png";
        } else {
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        }
        product.setImage(fileName);

        String uploadDir = "./src/main/resources/static/images/product-image/" + product.getName();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioException) {
            throw new IOException("Could not save img " + fileName);
        }

        Provider providerToAdd;
        Category categoryToAdd;
        for (Provider provider : product.getProviders()) {
            providerToAdd = providerService.findProvider(provider);
            if (!product.getProviders().contains(providerToAdd)) {
                product.getProviders().add(providerToAdd);
            }
        }
        for (Category category : product.getCategories()) {
            categoryToAdd = categoryService.findCategory(category);
            if (!product.getCategories().contains(categoryToAdd)) {
                product.getCategories().add(categoryToAdd);
            }
        }
        productService.addProduct(product);
        return "redirect:/crud_product";
    }

    @PostMapping("/saveEditProduct")
    public String saveEditProduct(@ModelAttribute(name = "product") Product product,
                              Errors errors,
                              @RequestParam("fileImage") MultipartFile multipartFile
    ) throws IOException {

        if (errors.hasErrors()) {
            System.out.println(errors);
            return "formProduct";
        }

        String fileName = null;
        if (multipartFile.getOriginalFilename() != null || !multipartFile.isEmpty()) {
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            product.setImage(fileName);
            String uploadDir = "./src/main/resources/static/images/product-image/" + product.getName();
            Path uploadPath = Paths.get(uploadDir);
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioException) {
                throw new IOException("Could not save img " + fileName);
            }
        }

        Provider providerToAdd;
        Category categoryToAdd;
        for (Provider provider : product.getProviders()) {
            providerToAdd = providerService.findProvider(provider);
            if (!product.getProviders().contains(providerToAdd)) {
                product.getProviders().add(providerToAdd);
            }
        }
        for (Category category : product.getCategories()) {
            categoryToAdd = categoryService.findCategory(category);
            if (!product.getCategories().contains(categoryToAdd)) {
                product.getCategories().add(categoryToAdd);
            }
        }
        productService.addProduct(product);
        return "redirect:/crud_product";
    }

    @GetMapping("/edit/product/{id}")
    public String editProduct(Product product, Model model) {
        Product p = productService.findProduct(product);
        List<Category> categories = categoryService.listCategories();
        List<Provider> providers = providerService.listProviders();
        model.addAttribute("product", p);
        model.addAttribute("listCategories", categories);
        model.addAttribute("listProviders", providers);
        return "formEditProduct";
    }

}
