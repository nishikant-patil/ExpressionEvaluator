package JsonRuleEngine;

public class Validation {
	private String result;
	private MyObject myObj;
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
	public MyObject getMyObj() {
		return myObj;
	}
	public void setMyObj(MyObject myObj) {
		this.myObj = myObj;
	}
	
	public String getResultIfPassed() throws Exception{
		if(null==orRules && null==andRules){
			return null;
		}
		if(null==orRules && null!=andRules){
			if(andRules.logicalAnd(myObj)){
				return result;
			}
		}
		if(null!=orRules && null==andRules){
			if(orRules.logicalOr(myObj)){
				return result;
			}
		}
		if(null!=orRules && null!=andRules){
			if(orRules.logicalOr(myObj) && andRules.logicalAnd(myObj)){
				return result;
			}
		}
		return null;
	}
}
