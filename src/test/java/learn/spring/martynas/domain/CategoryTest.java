package learn.spring.martynas.domain;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }

    @Test
    void getId() {

        Long value = 4L;

        category.setId(value);

        assertEquals(value, category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipeSet() {
    }
}