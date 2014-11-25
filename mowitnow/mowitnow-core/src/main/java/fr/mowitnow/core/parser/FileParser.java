package fr.mowitnow.core.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public abstract class FileParser {

	public List<String> parseFile(File file) throws IOException {
		
		@SuppressWarnings("unchecked")
		List<String> lines=FileUtils.readLines(file);

		return lines;
	}
}
