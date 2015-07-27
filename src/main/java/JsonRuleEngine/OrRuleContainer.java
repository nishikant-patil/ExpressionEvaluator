package JsonRuleEngine;

import java.util.ArrayList;
import java.util.List;

public class OrRuleContainer {
	private List<JsonRule> rules = new ArrayList<>();
	public List<JsonRule> getRules() {
		return rules;
	}
	public void setRules(List<JsonRule> rules) {
		this.rules = rules;
	}
	public boolean logicalOr(MyObject myObj) throws Exception{
		boolean ret = false;
		for(JsonRule rule : rules){
			ret = ret || rule.apply(myObj);
		}
		return ret;
	}
}
