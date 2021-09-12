package ru.retrofit.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.retrofit.dto.Category;
import ru.retrofit.service.CategoryService;
import ru.retrofit.utils.RetrofitUtils;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesTests extends BaseTest{


    @ParameterizedTest
    @EnumSource(value = ru.retrofit.enums.Category.class)
    void getFoodCategoryTest(ru.retrofit.enums.Category category) throws IOException {
        Response<Category> response = categoryService
                .getCategory(category.getId())
                .execute();
        assertThat(response.body().getTitle()).isEqualTo(category.getName());
        response.
                body().
                getProducts()
                .forEach(e-> assertThat(e.getCategoryTitle()).isEqualTo(category.getName()));
//        try {
////            System.out.println(response.body().string());
//        }
//        catch (NullPointerException e){
////            System.out.println(response.errorBody().string());
//        }

    }
}
