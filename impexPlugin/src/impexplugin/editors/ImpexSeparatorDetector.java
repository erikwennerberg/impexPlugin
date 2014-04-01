package impexplugin.editors;

import org.eclipse.jface.text.rules.IWordDetector;

public class ImpexSeparatorDetector implements IWordDetector{

	@Override
	public boolean isWordPart(char c) {
		 return (c == ';' | c == '[' | c==']');
	}

	@Override
	public boolean isWordStart(char c) {
		return (c == ';' | c == '[' | c==']');
	}
	

}
