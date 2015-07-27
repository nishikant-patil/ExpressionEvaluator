package JsonRuleEngine;

import org.codehaus.jackson.map.ObjectMapper;

import JsonRuleEngine.model.Cart;
import JsonRuleEngine.model.Item;
import JsonRuleEngine.validation.model.Validation;

public class Validator {
	public static void main(String... args) throws Exception {
		String jsonRule1 = "{\"result\":\"5% discount\", \"orRules\": {\"rules\":[{\"ruleField\":\"CartValue\", \"ruleEqualsValue\":\"1337.0\"},"
				+ "{\"ruleField\":\"Item.Name\", \"ruleEqualsValue\":\"Apple\"}]}}";
		String jsonRule2 = "{\"result\":\"15% discount\", "
				+ "\"andRules\": {\"rules\":[{\"ruleField\":\"CartValue\", \"ruleEqualsValue\":\"1337.0\"}"
				+ ", {\"ruleField\":\"Item.Name\", \"ruleEqualsValue\":\"Bourbon\"}, {\"ruleField\":\"Item.Type\", \"ruleEqualsValue\":\"Jack Daniels\"}]}}";
		
		validate(jsonRule1);
		validate(jsonRule2);
	}

	private static void validate(String jsonRule) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Validation val = mapper.readValue(jsonRule, Validation.class);
		Cart cart = new Cart();
		Item item = new Item();
		item.setName("Bourbon");
		item.setType("Jack Daniels");
		cart.setItem(item);
		cart.setCartValue(1337);
		val.setCart(cart);
		System.out.println(val.getResultIfPassed());
	}
}
