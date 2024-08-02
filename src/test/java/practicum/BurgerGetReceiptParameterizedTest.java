package practicum;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Random;

@RunWith(Parameterized.class)
public class BurgerGetReceiptParameterizedTest {

    private static final Faker faker = new Faker();
    private final Burger burger;
    private final String expectedReceipt;


    public BurgerGetReceiptParameterizedTest(Burger burger, String expectedReceipt) {
        this.burger = burger;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters
    public static Object[][] getParams() {
        final float price = new Random().nextFloat();
        final String bunName = faker.funnyName().name();
        final IngredientType ingredientType1 = IngredientType.FILLING;
        final String ingredientName1 = faker.food().ingredient();
        final IngredientType ingredientType2 = IngredientType.SAUCE;
        final String ingredientName2 = faker.food().ingredient();

        return new Object[][]{
            {getBurgerWithoutIngredients(bunName, price), formExpectedReceipt(bunName, price)},
            {getBurgerWithOneIngredient(bunName, ingredientType1, ingredientName1, price), formExpectedReceipt(bunName, ingredientType1, ingredientName1, price)},
            {getBurgerWithSeveralIngredients(bunName, ingredientType1, ingredientName1, ingredientType2, ingredientName2, price), formExpectedReceipt(bunName, ingredientType1,
                ingredientName1, ingredientType2, ingredientName2, price)},
        };
    }

    @Test
    public void getReceiptTest() {
        String actualReceipt = burger.getReceipt();

        Assert.assertEquals(expectedReceipt, actualReceipt);

        Mockito.verify(burger, Mockito.times(1)).getPrice();
    }

    private static Burger getBurgerWithoutIngredients(String bunName, float price) {
        Burger burgerWithoutIngredients = new Burger();
        burgerWithoutIngredients.setBuns(new Bun(bunName, 0.0f));
        Burger burgerStub = Mockito.spy(burgerWithoutIngredients);
        Mockito.when(burgerStub.getPrice()).thenReturn(price);
        return burgerStub;
    }

    private static Burger getBurgerWithOneIngredient(String bunName, IngredientType ingredientType, String ingredientName, float price) {
        Burger burgerWithOneIngredient = new Burger();
        burgerWithOneIngredient.setBuns(new Bun(bunName, 0.0f));
        burgerWithOneIngredient.addIngredient(new Ingredient(ingredientType, ingredientName, 0.0f));
        Burger burgerStub = Mockito.spy(burgerWithOneIngredient);
        Mockito.when(burgerStub.getPrice()).thenReturn(price);
        return burgerStub;
    }

    private static Burger getBurgerWithSeveralIngredients(String bunName, IngredientType ingredientType1, String ingredientName1, IngredientType ingredientType2, String ingredientName2,
                                                          float price) {
        Burger burgerWithSeveralIngredients = new Burger();
        burgerWithSeveralIngredients.setBuns(new Bun(bunName, 0.0f));
        burgerWithSeveralIngredients.addIngredient(new Ingredient(ingredientType1, ingredientName1, 0.0f));
        burgerWithSeveralIngredients.addIngredient(new Ingredient(ingredientType2, ingredientName2, 0.0f));
        Burger burgerStub = Mockito.spy(burgerWithSeveralIngredients);
        Mockito.when(burgerStub.getPrice()).thenReturn(price);
        return burgerStub;
    }

    private static String formExpectedReceipt(String bunName, float price) {
        return String.format("(==== %s ====)%n(==== %s ====)%n%nPrice: %f%n", bunName, bunName, price);
    }

    private static String formExpectedReceipt(String bunName, IngredientType ingredientType, String ingredientName, float price) {
        return String.format("(==== %s ====)%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n", bunName, ingredientType.name().toLowerCase(), ingredientName, bunName, price);
    }

    private static String formExpectedReceipt(String bunName, IngredientType ingredientType1, String ingredientName1, IngredientType ingredientType2, String ingredientName2, float price) {
        return String.format("(==== %s ====)%n= %s %s =%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n", bunName, ingredientType1.name().toLowerCase(), ingredientName1,
            ingredientType2.name().toLowerCase(), ingredientName2,
            bunName, price);
    }

}
