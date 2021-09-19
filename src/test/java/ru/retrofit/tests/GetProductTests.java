package ru.retrofit.tests;

import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.retrofit.dto.Product;

import java.io.IOException;

public class GetProductTests extends BaseTest{

    @Test
    void GetAllProductsTest() throws IOException {

        Response<Product> response = productService
                .getProducts()
                .execute();
        try {
            System.out.println(response.body().getId());
        } catch (NullPointerException e) {
            System.out.println(response.errorBody().string());
        }
    }
}
