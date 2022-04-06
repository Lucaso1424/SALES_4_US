package com.copernic.cat.erp.sales_4_us.controllers;

import com.copernic.cat.erp.sales_4_us.models.CartItem;
import com.copernic.cat.erp.sales_4_us.models.Product;
import com.copernic.cat.erp.sales_4_us.models.Purchase;
import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.service.ProductService;
import com.copernic.cat.erp.sales_4_us.service.PurchaseService;
import com.copernic.cat.erp.sales_4_us.service.ShoppingCartService;
import com.copernic.cat.erp.sales_4_us.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private PurchaseService purchaseService;

    //Aquest controlador mostra la pàgina del cistell de compra
    @GetMapping("/cart")
    public String showShoppingCart(Model model) {
        //Recuperem l'usuari loguejat
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = userService.findUserByEmail(authentication.getName());
        //Mostrem tots els productes(cartItems) que te l'usuari en el cistell de la compra
        List<CartItem> cartItems = shoppingCartService.listCartItems(u);
        List<CartItem> actualCartItems = new ArrayList<>();
        //el totalQuantity ens servira per imprimir en la pàgina la quantitat total de productes que hi ha
        int totalQuantity = 0;
        //totalPrize ens servira per imprimir el preu total de la compra
        double totalPrize = 0.0;
        for(CartItem c: cartItems){
            //Sumarem el preu i la quantitat de tots els items del cistell
            if (c.getVisible()){
                actualCartItems.add(c);
                totalQuantity += c.getQuantity();
                totalPrize += (c.getQuantity() * c.getProduct().getPrize());
            }

        }
        //Passarem els valors obtinguts al fitxer html per imprimir-los
        model.addAttribute("listcartItems", actualCartItems);
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("totalPrize", totalPrize);
        model.addAttribute("purchase", new Purchase());
        return "cart";
    }

    //En aquest controlador modificarem la quantitat de l'item que l'usuari ha modificat en la pàgina
    @GetMapping("/updateCart/{id}/{quantity}")
    public String updateCart(CartItem cartItem, Errors errors){
        if (errors.hasErrors()) {
            System.out.println(errors);
            return "cart";
        }
        //Recuperem el cartItem que l'usuari ha modificat
        CartItem savedItem = shoppingCartService.searchCartItem(cartItem);
        //Comprovem que la quantitat del item modificat no sigui inferior a 1 i guardem la quantitat
        if (savedItem.getQuantity() != cartItem.getQuantity() && cartItem.getQuantity() > 0){
            savedItem.setQuantity(cartItem.getQuantity());
            shoppingCartService.addCartItem(savedItem);
        }
        return "redirect:/cart";
    }

    //Borrarem l'item que l'usuari ha seleccionat desde la pàgina
   @GetMapping("/delete/cartItem/{id}")
    public String deleteCart(CartItem cartItem){
        shoppingCartService.deleteCart(cartItem);
       return "redirect:/cart";
   }

   //Afegirem el producte seleccionat per l'usuari al seu cistell de compra
    @GetMapping("/addItem/{id}")
    public String addCartItem(Product product){
        //Recuperem l'usuari loguejat
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = userService.findUserByEmail(authentication.getName());
        //Generem un nou cartItem
        CartItem itemToAdd = new CartItem();
        //Li assignem el cartItem al usuari loguejat
        itemToAdd.setUser(u);
        //Per defecte, quan s'afegeix un producte al cistell sempre es fara amb la quantitat de 1
        itemToAdd.setQuantity(1);
        itemToAdd.setVisible(true);
        //Busquem el producte que l'usuari ens ha pasat per parametre de l'url
        Product productToAdd = productService.findProduct(product);
        //Llistem tots els cartItems que te l'usari actualment
        List<CartItem> items = shoppingCartService.listCartItems(u);
        List<Product> addedProducts = new ArrayList<>();
        //Li afegim al cartItem generat el producte que ha demanat l'usuari
        itemToAdd.setProduct(productToAdd);
        for (CartItem c : items){
            //Recuperem la llista de tots els productes que l'usuari te en la seva cistella de compra
            if (c.getVisible()){
                addedProducts.add(c.getProduct());
            }
        }
        //Si el producte que ha afegit l'usuari ja el tenia posat en el cistell previament, no es tornara a afegir
        if (!addedProducts.contains(productToAdd)){
            shoppingCartService.addCartItem(itemToAdd);
        }
        return "redirect:/cart";
    }

    @PostMapping("/purchase_success")
    public String finishPurchase(@ModelAttribute(name="purchase") Purchase purchase){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = userService.findUserByEmail(authentication.getName());
        List<CartItem> items = shoppingCartService.listCartItems(u);
        List<CartItem> activeItems = new ArrayList<>();
        for (CartItem c : items) {
            if (c.getVisible()){
                activeItems.add(c);
                c.setVisible(false);
                shoppingCartService.addCartItem(c);
            }
        }

        Purchase purchaseToAdd = new Purchase();
        purchaseToAdd.setCartItems(activeItems);
        purchaseToAdd.setTotalPrize(purchase.getTotalPrize());
        purchaseToAdd.setDate(new Date(System.currentTimeMillis()));
        purchaseService.addPurchase(purchaseToAdd);

        return "purchase_success";
    }

}
