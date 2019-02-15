package de.hhu.abschlussprojektverleihplattform.repository;

import de.hhu.abschlussprojektverleihplattform.model.AddressEntity;
import de.hhu.abschlussprojektverleihplattform.model.ProductEntity;
import de.hhu.abschlussprojektverleihplattform.model.UserEntity;
import de.hhu.abschlussprojektverleihplattform.utils.RandomTestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;



    @Test
    public void getAllProducsFromDatabase() {
        boolean savedProductsExists = false;
        UserEntity user1 = RandomTestData.newRandomTestUser();
        AddressEntity address1 = RandomTestData.newRandomTestAddress();
        userRepository.saveUser(user1);
        UserEntity loadedUser = userRepository.getUserByFirstname(user1.getFirstname());
        ProductEntity product = RandomTestData.newRandomTestProduct(loadedUser, address1);
        productRepository.saveProduct(product);
        List<ProductEntity> allProducts = productRepository.getAllProducts();

        for (ProductEntity productElement : allProducts) {
            if (productElement.getTitle().equals(product.getTitle()) &&
                    productElement.getDescription().equals(product.getDescription()) &&
                    productElement.getSurety() == product.getSurety() &&
                    productElement.getCost() == product.getCost() &&
                    productElement.getLocation().getStreet().equals(product.getLocation().getStreet())) {
                savedProductsExists = true;
            }
        }

        Assert.assertTrue(savedProductsExists);
    }

    @Test
    public void getOneProductByTitleAndId() {
        UserEntity user2 = RandomTestData.newRandomTestUser();
        AddressEntity address2 = RandomTestData.newRandomTestAddress();
        userRepository.saveUser(user2);
        UserEntity loadedUser = userRepository.getUserByFirstname(user2.getFirstname());
        ProductEntity product = RandomTestData.newRandomTestProduct(loadedUser, address2);

        productRepository.saveProduct(product);
        ProductEntity loadedProductViaTitle = productRepository.getProductByTitlel(product.getTitle());
        ProductEntity loadedProductViaId = productRepository.getProductById(loadedProductViaTitle.getId());

        Assert.assertSame(loadedProductViaTitle.getId(), loadedProductViaId.getId());
        Assert.assertSame(product.getTitle(), loadedProductViaId.getTitle());
    }


}