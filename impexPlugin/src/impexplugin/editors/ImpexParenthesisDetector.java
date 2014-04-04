package impexplugin.editors;

import org.eclipse.jface.text.rules.IWordDetector;

public class ImpexParenthesisDetector implements IWordDetector{

	@Override
	public boolean isWordPart(char c) {
		 return (c == '(' | c==')');
	}

	@Override
	public boolean isWordStart(char c) {
		return (c == '(' | c==')');
	}
	

}
