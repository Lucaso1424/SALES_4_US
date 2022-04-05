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

    //Llistem tots els productes al fitxer html
    @GetMapping("/crud_product")
    public String inici(Model model) {
        List<Product> listProducts = productService.listProduct();
        model.addAttribute("listProducts", listProducts);
        return "crud_list_product";
    }

    //Borrem el producte seleccionat que li passem amb id
    @GetMapping("/delete/product/{id}")
    public String deleteProduct(Product product) {
        productService.deleteProduct(product);
        return "redirect:/crud_product";
    }

    //Formulari per crear un producte
    @GetMapping("/formProduct")
    public String createProductForm(Model model) {
        //Llistem totes les categories i proveidors per a poder asignar un s'aquest al nou producte
        List<Provider> providers = providerService.listProviders();
        List<Category> categories = categoryService.listCategories();
        //Pasarem per atributs els proveidors, les categories i els productes
        model.addAttribute("product", new Product());
        model.addAttribute("listProviders", providers);
        model.addAttribute("listCategories", categories);
        return "formProduct";
    }

    //Metode per guardar un producte unicament quan es crea
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

        //El primer cop que creem el producte li posarem en l'atribut pathImage el nom per evitar problemes amb la gestio de les fotos
        product.setPathImage(product.getName());
        String fileName;
        //Indiquem la ruta d'on es guardara la nova imatge del producte
        String uploadDir = "./src/main/resources/static/images/product-image/" + product.getPathImage();
        //Si en el formulari no s'ha posat cap fotografia guardarem una per defecte
        if (multipartFile.getOriginalFilename() == null || multipartFile.isEmpty()) {
            fileName = "logo.png";
            //Guardarem a la base de dades el nom de la imatge
            product.setImage(fileName);
            Path uploadPath = Paths.get(uploadDir);
            //Si encara no existeix la carpeta on guardem les fotos del producte la crearem
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            //Agafarem la foto per defecte que esta en el notre projecte i la guardarem dins de la carpeta del producte
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("./static/images/logo.png")) {
                Path filePath = uploadPath.resolve(fileName);
                assert inputStream != null;
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioException) {
                throw new IOException("Could not save img " + fileName);
            }
        } else {
            //Si en el formulari han enviat una foto guardarem a la base de dades el nom de la imatge
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            product.setImage(fileName);
            Path uploadPath = Paths.get(uploadDir);
            //Si encara no existeix la carpeta on guardem les fotos del producte la crearem
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            //Agafarem la imatge pasada pel formulari i la guardarem en la carpeta del producte
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioException) {
                throw new IOException("Could not save img " + fileName);
            }
        }

        Provider providerToAdd;
        Category categoryToAdd;
        //Comprovarem que el proveidor que li volem posar no ha estat assignat previament per evitar duplicats
        for (Provider provider : product.getProviders()) {
            providerToAdd = providerService.findProvider(provider);
            if (!product.getProviders().contains(providerToAdd)) {
                product.getProviders().add(providerToAdd);
            }
        }
        //Comprovarem que la categoria que li volem posar no ha estat assignada previament per evitar duplicats
        for (Category category : product.getCategories()) {
            categoryToAdd = categoryService.findCategory(category);
            if (!product.getCategories().contains(categoryToAdd)) {
                product.getCategories().add(categoryToAdd);
            }
        }
        //Guardem el producte a la base de dades
        productService.addProduct(product);
        return "redirect:/crud_product";
    }

    //S'utilitzara unicament si volem editar un producte existen
    @PostMapping("/saveEditProduct")
    public String saveEditProduct(@ModelAttribute(name = "product") Product product,
                              Errors errors,
                              @RequestParam("fileImage") MultipartFile multipartFile
    ) throws IOException {

        if (errors.hasErrors()) {
            System.out.println(errors);
            return "formProduct";
        }
        Product savedProduct = productService.findProduct(product);
        String fileName;
        //Si en el formulari s'ha posat una imatge es guardara seguint els mateixos passos anteriors
        if (multipartFile.getOriginalFilename() != null && !multipartFile.isEmpty()) {
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            product.setImage(fileName);
            product.setPathImage(savedProduct.getPathImage());
            String uploadDir = "./src/main/resources/static/images/product-image/" + product.getPathImage();
            Path uploadPath = Paths.get(uploadDir);
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioException) {
                throw new IOException("Could not save img " + fileName);
            }
        } else {
            //Si no s'ha posat cap imatge en el formulari al editar, es mantindran el nom de las imatges que estaven a la BD
            product.setPathImage(savedProduct.getPathImage());
            product.setImage(savedProduct.getImage());
        }

        Provider providerToAdd;
        Category categoryToAdd;
        //Comprovarem que el proveidor que li volem posar no ha estat assignat previament per evitar duplicats
        for (Provider provider : product.getProviders()) {
            providerToAdd = providerService.findProvider(provider);
            if (!product.getProviders().contains(providerToAdd)) {
                product.getProviders().add(providerToAdd);
            }
        }
        //Comprovarem que la categoria que li volem posar no ha estat assignada previament per evitar duplicats
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
        //Imprimirem a la pàgina els detalls del producte mes tots els proveidor i categories per si volem modificar els proveidors o categories
        Product p = productService.findProduct(product);
        List<Category> categories = categoryService.listCategories();
        List<Provider> providers = providerService.listProviders();
        model.addAttribute("product", p);
        model.addAttribute("listCategories", categories);
        model.addAttribute("listProviders", providers);
        return "formEditProduct";
    }

}
