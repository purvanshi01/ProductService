package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductDtoToProduct (FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());
        product.setTitle(fakeStoreProductDto.getTitle());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
    @Override
    public Product getProductById(Long id) {
        // call the fake store api to get the product
        // this is not the good way of creating object of RestTemplate because you will need it
        // many places why to create it again and again
        // so it can behave like a singleton
        // we need to ask spring to create a single bean (object) of RestTemplate
        // these beans will be stored in spring context or application context or spring container
        // for this you need to create a configuration class
        // RestTemplate restTemplate = new RestTemplate();

        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        /* the above code where you call the url will return a product json but
        * there should be something in my system to convert that json to something that
        * we can use in java
        * serialization and deserialization we dont have to worry about it
        * it will happen in spring automatically by a library called jaxon
        * can i map this json product object as it is with my product object
        * as you see category is mapped as string parameter and not as an object
        * because in my product object is a class and here it is a string
        * we can create a dto now
        * */

        if (fakeStoreProductDto == null)
            return null;
        // convert fakeStoreProductDto to product object
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        FakeStoreProductDto[] fakeStoreProductArray = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        if (fakeStoreProductArray != null) {
            List<FakeStoreProductDto> fakeStoreProductList = Arrays.asList(fakeStoreProductArray);
            for (FakeStoreProductDto i : fakeStoreProductList) {
                products.add(convertFakeStoreProductDtoToProduct(i));
            }
        }
        return products;
    }

    @Override
    public Product updateProduct() {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        // PUT method
        // Replace the product with given id with the input product
        // and return the updated product in the output

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        if (fakeStoreProductDto == null)
            return null;
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product createProduct() {
        return null;
    }

    @Override
    public void deleteProduct() {

    }
}
