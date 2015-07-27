package JsonRuleEngine;

import java.lang.reflect.Method;

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

	public boolean apply(MyObject myObj) throws Exception {
		Method m = myObj.getClass().getMethod("get" + ruleField);
		Object value =  m.invoke(myObj);
		return ruleEqualsValue.equals(value.toString());
	}	
}
