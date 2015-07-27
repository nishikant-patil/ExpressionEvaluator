package JsonRuleEngine.validation.model;

import JsonRuleEngine.container.AndRuleContainer;
import JsonRuleEngine.container.OrRuleContainer;
import JsonRuleEngine.model.Cart;

public class Validation {
	private String result;
	private Cart cart;
	private OrRuleContainer orRules;
	private AndRuleContainer andRules;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public OrRuleContainer getOrRules() {
		return orRules;
	}
	public void setOrRules(OrRuleContainer orRules) {
		this.orRules = orRules;
	}
	public AndRuleContainer getAndRules() {
		return andRules;
	}
	public void setAndRules(AndRuleContainer andRules) {
		this.andRules = andRules;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public String getResultIfPassed() throws Exception{
		if(null==orRules && null==andRules){
			return null;
		}
		if(null==orRules && null!=andRules){
			if(andRules.logicalAnd(cart)){
				return result;
			}
		}
		if(null!=orRules && null==andRules){
			if(orRules.logicalOr(cart)){
				return result;
			}
		}
		if(null!=orRules && null!=andRules){
			if(orRules.logicalOr(cart) && andRules.logicalAnd(cart)){
				return result;
			}
		}
		return null;
	}
}
