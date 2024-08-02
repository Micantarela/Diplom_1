package practicum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

@RunWith(Parameterized.class)
public class BurgerGetPriceParameterizedTest {

    private final Burger burger;
    private final float expectedPrice;

    public BurgerGetPriceParameterizedTest(Burger burger, float expectedPrice) {
        this.burger = burger;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] getParams() {
        return new Object[][]{
            {getBurgerWithoutIngredients(), 20f},
            {getBurgerWithOneIngredient(), 21f},
            {getBurgerWithSeveralIngredients(), 43f},
        };
    }

    @Test
    public void getPriceTest() {
        float actualPrice = burger.getPrice();

        Assert.assertEquals(expectedPrice, actualPrice, 0.00000001);
    }

    private static Burger getBurgerWithoutIngredients() {
        Bun bunMock = Mockito.mock(Bun.class);
        Mockito.when(bunMock.getPrice()).thenReturn(10f);
        Burger burgerWithoutIngredients = new Burger();
        burgerWithoutIngredients.setBuns(bunMock);
        return burgerWithoutIngredients;
    }

    private static Burger getBurgerWithOneIngredient() {
        Bun bunMock = Mockito.mock(Bun.class);
        Mockito.when(bunMock.getPrice()).thenReturn(8f);
        Ingredient ingredientMock = Mockito.mock(Ingredient.class);
        Mockito.when(ingredientMock.getPrice()).thenReturn(5f);
        Burger burgerWithOneIngredient = new Burger();
        burgerWithOneIngredient.addIngredient(ingredientMock);
        burgerWithOneIngredient.setBuns(bunMock);
        return burgerWithOneIngredient;
    }

    private static Burger getBurgerWithSeveralIngredients() {
        Bun bunMock = Mockito.mock(Bun.class);
        Mockito.when(bunMock.getPrice()).thenReturn(15f);
        Ingredient ingredientMock1 = Mockito.mock(Ingredient.class);
        Mockito.when(ingredientMock1.getPrice()).thenReturn(6f);
        Ingredient ingredientMock2 = Mockito.mock(Ingredient.class);
        Mockito.when(ingredientMock2.getPrice()).thenReturn(7f);
        Burger burgerWithSeveralIngredients = new Burger();
        burgerWithSeveralIngredients.setBuns(bunMock);
        burgerWithSeveralIngredients.addIngredient(ingredientMock1);
        burgerWithSeveralIngredients.addIngredient(ingredientMock2);
        return burgerWithSeveralIngredients;
    }

}
