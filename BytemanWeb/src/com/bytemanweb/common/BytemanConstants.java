package com.bytemanweb.common;

import java.io.File;

public interface BytemanConstants {
	
	public static final String COMMAND = "command";
	public static final String LOAD_RULES = "loadRules";
	public static final String UNLOAD_RULES = "unloadRules";
	public static final String ATTACH_COMMAND = "attach";
	public static final boolean IS_WINDOWS_OS = System.getProperty("os.name").toLowerCase().indexOf("windows") != -1;
	public static final String FILE_EXTENSION = IS_WINDOWS_OS ? ".bat" :".sh";
	public static final String INSTALL_SCRIPT = "bin"+File.separator+"bminstall"+FILE_EXTENSION;
	public static final String RULE_SUBMIT_SCRIPT = "bin"+File.separator+"bmsubmit"+FILE_EXTENSION;
	public static final String RULE_UNLOAD_SCRIPT = RULE_SUBMIT_SCRIPT + " -u";
	public static final String RULE_DISLAY_SCRIPT = RULE_SUBMIT_SCRIPT + " -l";
	public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	public static final String RULES_LOAD_TEMP_FILE =  TEMP_DIR+ File.separator+"loadRule.btm";
	public static final String RULES_DIR = "rules";
	public static final String OPEN_LOG_RULE_FILE = "openLogFileRule.btm";

}
