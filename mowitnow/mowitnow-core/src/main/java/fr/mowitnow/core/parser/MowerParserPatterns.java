package fr.mowitnow.core.parser;

public interface MowerParserPatterns {
	static final String PATTERN_INSTRUCTIONS = "^([GAD])+$";
	static final String PATTERN_LAWN = "^(\\d+)\\s(\\d+)$";
	static final String PATTERN_MOWER = "^(\\d+)\\s(\\d+)\\s([WENS])$";
}