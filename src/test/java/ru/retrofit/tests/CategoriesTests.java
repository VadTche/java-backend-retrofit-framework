package ru.retrofit.tests;

import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.retrofit.dto.Category;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.retrofit.enums.CategoryEnum.FOOD;

public class CategoriesTests extends BaseTest{

    @Test
    void getFoodCategoryTest() throws IOException {
        Response<Category> response = categoryService
                .getCategory(FOOD.getId())
                .execute();
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.body().getTitle()).isEqualTo(FOOD.getTitle());
    }
}
