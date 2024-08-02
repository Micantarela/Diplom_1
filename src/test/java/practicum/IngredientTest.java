package practicum;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class IngredientTest {

    private static final Faker faker = new Faker();

    @Test
    public void getNameTest() {
        final String name = faker.food().ingredient();
        Ingredient ingredient = new Ingredient(null, name, 0.0f);

        String actualName = ingredient.getName();

        Assert.assertEquals(name, actualName);
    }

    @Test
    public void getPriceTest() {
        final float price = new Random().nextFloat();
        Ingredient ingredient = new Ingredient(null, null, price);

        float actualPrice = ingredient.getPrice();

        Assert.assertEquals(price, actualPrice, 0.000001);
    }

    @Test
    public void getIngredientType() {
        final IngredientType type = IngredientType.FILLING;
        Ingredient ingredient = new Ingredient(type, null, 0.0f);

        IngredientType actualType = ingredient.getType();

        Assert.assertEquals(type, actualType);
    }

}
