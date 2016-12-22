package ExpressionEvaluator;

import ExpressionEvaluator.model.Cart;
import ExpressionEvaluator.model.Item;
import ExpressionEvaluator.util.Calculator;
import ExpressionEvaluator.util.Evaluator;
import ExpressionEvaluator.util.PostFixConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<String> tokens = PostFixConverter.convertToPostFix("2*10+2/2-3*6/2+(CartValue*20-33)+(CartValue*10/100)-(CartValue*10/100)*(CartValue*10/100)+(CartValue*10/100)-(CartValue*10/100)-(CartValue*10/100)");
        long time = System.currentTimeMillis();
        for (int i = 0; i != 900000; ++i) {
            Calculator.calculate(cart, tokens);
        }
        time = System.currentTimeMillis() - time;
        System.out.println(time / 1000.0);
        Map<String, String> map = new HashMap<>();
        map.put("CartValue", "200");
        time = System.currentTimeMillis();
        for (int i = 0; i != 900000; ++i) {
            Calculator.calculate(map, tokens);
        }
        time = System.currentTimeMillis() - time;
        System.out.println(time / 1000.0);
    }

    private static void testValidator() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String expression = "Item.Name=Jack Daniels&(CartValue=200.0&Item.Type=Whiskey)";
        System.out.println(Evaluator.evaluate(cart, PostFixConverter.convertToPostFix(expression)));
    }
}

