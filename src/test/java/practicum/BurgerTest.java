package practicum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Test
    public void setBunsTest() {
        Burger burger = new Burger();
        Bun bunMock = Mockito.mock(Bun.class);

        burger.setBuns(bunMock);

        Assert.assertEquals(bunMock, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredientMock = Mockito.mock(Ingredient.class);

        burger.addIngredient(ingredientMock);

        Assert.assertEquals(1, burger.ingredients.size());
        Assert.assertEquals(ingredientMock, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredientMock = Mockito.mock(Ingredient.class);
        burger.ingredients.add(ingredientMock);

        burger.removeIngredient(0);

        Assert.assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredientMock1 = Mockito.mock(Ingredient.class);
        Ingredient ingredientMock2 = Mockito.mock(Ingredient.class);
        burger.ingredients.add(ingredientMock1);
        burger.ingredients.add(ingredientMock2);

        burger.moveIngredient(0, 1);

        Assert.assertEquals(2, burger.ingredients.size());
        Assert.assertEquals(ingredientMock2, burger.ingredients.get(0));
        Assert.assertEquals(ingredientMock1, burger.ingredients.get(1));
    }

}
