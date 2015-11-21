package RuleEngine;

import RuleEngine.model.Cart;
import RuleEngine.model.Item;
import RuleEngine.util.Evaluator;
import RuleEngine.util.PostFixConvertor;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Nishikant on 9/6/2015.
 * Sample class to test the concept.
 */
public class Validator {

    private static Cart cart = new Cart();
    static{
        Item item = new Item();
        item.setName("Jack Daniels");
        item.setType("Whiskey");
        cart.setItem(item);
        cart.setCartValue(200);
    }
    public static void main(String... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String expression="Item.Name=Jack Daniels & (CartValue=200.0 & Item.Type=Whiskey)";
        System.out.println(Evaluator.evaluate(cart, PostFixConvertor.convertToPostFix(expression)));
    }
}
