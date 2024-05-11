package com.example.productservice.services;

import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductServiceePurvi")
//@Primary
public class SelfProductService implements  ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    SelfProductService (ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        // Fetch product with the given id from DB.
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            // throw a product not found exception
            return null;
        }

        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        // Fetch all the products from db
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException();
        }
        if (product == null) throw new RuntimeException("Invalid input exception to update method");
        Product currentProduct = optionalProduct.get();

        if (product.getTitle() != null) {
            currentProduct.setTitle(product.getTitle());
        }

        if (product.getDescription() != null) {
            currentProduct.setDescription(product.getDescription());
        }

        /*if (product.getPrice() != null) {
            currentProduct.setPrice(product.getPrice());
        }*/

        return productRepository.save(currentProduct);
    }

    @Override
    public Product replaceProduct(Long id, ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();
        /*if (category.getId() == null) {
            // first save category in the db
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }
        we have commented this code because in the product model we are using
         @ManyToOne(cascade = {CascadeType.ALL})
         which means if we save the product the category would be saved if the payload has data
         if we delete the product then the category will also be deleted etc
         so this piece of code is not required then and hence commenting.
        */
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct() {

    }
}
