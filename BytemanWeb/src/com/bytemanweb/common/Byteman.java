package com.bytemanweb.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bytemanweb.domain.dto.BTMRule;

/**
 * Class for byteman utilities
 * @author Himanshu.Gaur
 *
 */
public class Byteman {
	
	/**
	 * Method to write byteman rules to the rules file
	 * @param rules
	 * @throws IOException
	 */
	public static void writeToRulesFile(String rules) throws IOException {
		File newTextFile = new File(BytemanConstants.RULES_LOAD_TEMP_FILE);
		newTextFile.createNewFile();
		FileWriter fileWriter = new FileWriter(newTextFile);
        fileWriter.write(rules);
        fileWriter.close();
	}
	
	/**
	 * Method to re-format rules string to appropriate format.
	 * @param rulesString
	 * @return string in appropriate format
	 */
	public static String getRulesInDisplayFormat (String rulesString) {
		StringBuffer displayString = new StringBuffer();
		String[] rules = rulesString.split("ENDRULE");
		for(String rule : rules) {
			if(rule.indexOf("RULE") > -1) {
				displayString.append(rule.substring(rule.indexOf("RULE")) + "ENDRULE" + System.lineSeparator() + System.lineSeparator());
			}
		}
		return displayString.toString();
	}

	/**
	 * Method to return list of BTM rules.
	 * @param rulesString
	 * @return string in appropriate format
	 */
	public static List<BTMRule> getBTMRules (String rulesString) {
		List<BTMRule> rulesList = new ArrayList<BTMRule>();
		String[] rules = rulesString.split("ENDRULE");
		for(String rule : rules) {
			if(rule.indexOf("RULE") > -1) {
				BTMRule btmRule = new BTMRule();
				btmRule.setRuleName(rule.substring(rule.indexOf("RULE") + 5, rule.indexOf(System.lineSeparator(), rule.indexOf("RULE"))));
				btmRule.setRuleDefinition(rule.substring(rule.indexOf("RULE")) + "ENDRULE");
				rulesList.add(btmRule);
			}
		}
		return rulesList;
	}

}
