package impexplugin.editors;

import org.eclipse.jface.text.rules.IWordDetector;

public class ImpexWordDetector implements IWordDetector{

	@Override
	public boolean isWordPart(char c) {
		return (Character.isLetterOrDigit(c) | 
	    		c == '$' | c=='=' |Character.isWhitespace(c));
	}

	@Override
	public boolean isWordStart(char c) {
		return (Character.isLetterOrDigit(c) | 
	    		c == '$' | c=='=' |Character.isWhitespace(c));
	}

}
