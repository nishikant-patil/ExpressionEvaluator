package JsonRuleEngine;

import org.codehaus.jackson.map.ObjectMapper;

public class Validator {
	public static void main(String... args) throws Exception{
		String jsonRule = "{\"result\":\"jhakass!!!\", \"orRules\": {\"rules\":[{\"ruleField\":\"Value\", \"ruleEqualsValue\":\"1337\"},"
				+ "{\"ruleField\":\"Value\", \"ruleEqualsValue\":\"1338\"}]}, "
				+ "\"andRules\": {\"rules\":[{\"ruleField\":\"Value\", \"ruleEqualsValue\":\"1337\"}"
				+ ", {\"ruleField\":\"Value\", \"ruleEqualsValue\":\"1337\"}]}}";
		ObjectMapper mapper = new ObjectMapper();
		Validation val = mapper.readValue(jsonRule, Validation.class);
		MyObject myObj = new MyObject();
		myObj.setValue(1337);
		val.setMyObj(myObj);
		System.out.println(val.getResultIfPassed());
	}
}
