package JsonRuleEngine;

import java.util.ArrayList;
import java.util.List;

public class AndRuleContainer {
	private List<JsonRule> rules = new ArrayList<>();
	public List<JsonRule> getRules() {
		return rules;
	}
	public void setRules(List<JsonRule> rules) {
		this.rules = rules;
	}
	public boolean logicalAnd(MyObject myObj) throws Exception{
		boolean ret = true;
		for(JsonRule rule : rules){
			ret = ret && rule.apply(myObj);
		}
		return ret;
	}
}
