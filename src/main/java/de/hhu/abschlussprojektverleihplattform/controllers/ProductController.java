package de.hhu.abschlussprojektverleihplattform.controllers;

import de.hhu.abschlussprojektverleihplattform.model.ProductEntity;
import de.hhu.abschlussprojektverleihplattform.model.UserEntity;
import de.hhu.abschlussprojektverleihplattform.repository.ProductRepository;
import de.hhu.abschlussprojektverleihplattform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {

    // PostMapping fehlt noch

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/addproduct")
    public String getAddProduct(Model model) {
        return "addproduct";
    }

    @GetMapping("/editproduct")
    public String getEditProduct(Model model) {
        return "editproduct";
    }

    @GetMapping("/removeproduct")
    public String getRemoveProduct(Model model) {
        return "removeproduct";
    }

    @GetMapping("/productdetail/{id}")
    public String getProductDetails(Model model, @PathVariable Long id) {
        ProductEntity product = productRepository.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "productdetailedview";
        }
        return "redirect:/";
    }

    @GetMapping("/myproducts")
    public String getMyProducts(Model model, Authentication auth) {
        UserEntity user = (UserEntity) auth.getPrincipal();
        Long id = user.getUserId();
        List<ProductEntity> myProducts = productRepository.getProductsWhereOwnerEqualsUser(id);
        boolean gotNoProducts = myProducts.isEmpty();
        model.addAttribute("myProducts", myProducts);
        model.addAttribute("gotNoProducts", gotNoProducts);
        return "myproducts";
    }

    //TODO: GetMappings+Views to see all Product and start a request
    // (Request itself is in ProductLendingRequestController)
}

