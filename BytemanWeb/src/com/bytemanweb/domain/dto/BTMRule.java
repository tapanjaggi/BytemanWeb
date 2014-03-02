/**
 * 
 */
package com.bytemanweb.domain.dto;

/**
 * DTO class to store all rules.
 * @author Himanshu.Gaur
 *
 */
public class BTMRule {

	private String ruleName;
	
	private String ruleDefinition;

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleDefinition() {
		return ruleDefinition;
	}

	public void setRuleDefinition(String ruleDefinition) {
		this.ruleDefinition = ruleDefinition;
	}
	
	
}
