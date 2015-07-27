package JsonRuleEngine.rule;

import java.lang.reflect.Method;

import JsonRuleEngine.model.Cart;

public class JsonRule {
	private String ruleField;
	private String ruleEqualsValue;
	public String getRuleField() {
		return ruleField;
	}

	public void setRuleField(String ruleField) {
		this.ruleField = ruleField;
	}

	public String getRuleEqualsValue() {
		return ruleEqualsValue;
	}

	public void setRuleEqualsValue(String ruleEqualsValue) {
		this.ruleEqualsValue = ruleEqualsValue;
	}

	public boolean apply(Cart cart) throws Exception {
		Method m = null;
		Object obj = cart;
		for(String token : ruleField.split("\\.")){
			m = obj.getClass().getMethod("get" + token);
			obj = m.invoke(obj);
		}
		return ruleEqualsValue.equals(obj.toString());
	}	
}
