package JsonRuleEngine.container;

import java.util.ArrayList;
import java.util.List;

import JsonRuleEngine.model.Cart;
import JsonRuleEngine.rule.JsonRule;

public class OrRuleContainer {
	private List<JsonRule> rules = new ArrayList<>();
	public List<JsonRule> getRules() {
		return rules;
	}
	public void setRules(List<JsonRule> rules) {
		this.rules = rules;
	}
	public boolean logicalOr(Cart cart) throws Exception{
		boolean ret = false;
		for(JsonRule rule : rules){
			ret = ret || rule.apply(cart);
		}
		return ret;
	}
}
