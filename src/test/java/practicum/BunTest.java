package practicum;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;

import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class BunTest {

    private static final Faker faker = new Faker();

    @Test
    public void getNameTest() {
        final String name = faker.funnyName().name();
        Bun bun = new Bun(name, 0.0f);

        String actualName = bun.getName();

        Assert.assertEquals(name, actualName);
    }

    @Test
    public void getPriceTest() {
        final float price = new Random().nextFloat();
        Bun bun = new Bun(null, price);

        float actualPrice = bun.getPrice();

        Assert.assertEquals(price, actualPrice, 0.000001);
    }
}
