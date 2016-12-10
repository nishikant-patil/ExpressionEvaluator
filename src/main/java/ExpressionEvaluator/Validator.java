package ExpressionEvaluator;

import ExpressionEvaluator.model.Cart;
import ExpressionEvaluator.model.Item;
import ExpressionEvaluator.util.Calculator;
import ExpressionEvaluator.util.Evaluator;
import ExpressionEvaluator.util.PostFixConvertor;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Nishikant on 9/6/2015.
 * Sample class to test the concept.
 */
public class Validator {

    private static Cart cart = new Cart();

    static {
        Item item = new Item();
        item.setName("Jack Daniels");
        item.setType("Whiskey");
        cart.setItem(item);
        cart.setCartValue(200);
    }

    public static void main(String... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        testValidator();
        testCalculator();
    }

    private static void testCalculator() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        System.out.println(Calculator.calculate(cart, PostFixConvertor.convertToPostFix("2*10+2/2-3*6/2+(CartValue*20-33)+(CartValue*10/100)")));
    }

    private static void testValidator() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String expression = "Item.Name=Jack Daniels&(CartValue=200.0&Item.Type=Whiskey)";
        System.out.println(Evaluator.evaluate(cart, PostFixConvertor.convertToPostFix(expression)));
    }
}

