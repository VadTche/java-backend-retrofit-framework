package ru.retrofit.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;
import ru.retrofit.dto.Category;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesTests extends BaseTest {


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
                .forEach(e -> assertThat(e.getCategoryTitle()).isEqualTo(category.getName()));
    }
}
