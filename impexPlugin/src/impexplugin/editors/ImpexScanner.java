package impexplugin.editors;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class ImpexScanner extends RuleBasedScanner {

	public ImpexScanner(ColorManager manager) {
		
		
		IToken variable = new Token(new TextAttribute(manager.getColor(ImpexColorConstants.VARIABLE)));
		IToken string = new Token(new TextAttribute(manager.getColor(ImpexColorConstants.STRING)));
		IToken separator = new Token(new TextAttribute(manager.getColor(ImpexColorConstants.SEPARATOR)));
		IToken modifier = new Token(new TextAttribute(manager.getColor(ImpexColorConstants.MODIFIER)));
		IToken table = new Token(new TextAttribute(manager.getColor(ImpexColorConstants.TABLE)));
		IToken parenthesis = new Token(new TextAttribute(manager.getColor(ImpexColorConstants.PARENTHESIS)));
		IToken brackets = new Token(new TextAttribute(manager.getColor(ImpexColorConstants.BRACKET)));
		
		IRule[] rules = new IRule[18];
		//Add rule for processing instructions
		//rules[0] = new WordPatternRule(new ImpexWordDetector(), "$", "", variable);
		
		// Add generic whitespace rule.
		rules[0] = new WhitespaceRule(new ImpexWhitespaceDetector());
		//Add string rule
		rules[1] = new SingleLineRule("\"", "\"", string);
		rules[2] = new SingleLineRule("'", "'", string);
		//Add variables rule
		rules[3] = new WordPatternRule(new ImpexWordDetector(), "$", "", variable);
		//Add separator rules
		rules[4] = new WordPatternRule(new ImpexSeparatorDetector(), ";", "", separator);
		rules[5] = new WordPatternRule(new ImpexBracketDetector(), "[", "", brackets);
		rules[6] = new WordPatternRule(new ImpexBracketDetector(), "]", "", brackets);
		rules[7] = new WordPatternRule(new ImpexParenthesisDetector(), "(", "", parenthesis);
		rules[8] = new WordPatternRule(new ImpexParenthesisDetector(), ")", "", parenthesis);
		
		// rule for impex table using regex: (?<=INSERT_UPDATE)\s\w*(?=;)
		//rules[7]= new RegexRule("(?<=INSERT_UPDATE)\\s\\w*(?=;)", table);
		
		rules[9] = new WordPatternRule(new ImpexWordDetector(), "INSERT_UPDATE ", "", table);
		rules[10] = new WordPatternRule(new ImpexWordDetector(), "UPDATE ", "", table);
		rules[11] = new WordPatternRule(new ImpexWordDetector(), "REMOVE ", "", table);
		
		rules[12] = new WordPatternRule(new ImpexWordDetector(), "processor", "", modifier);
		rules[13] = new WordPatternRule(new ImpexWordDetector(), "cacheUnique", "", modifier);
		rules[14] = new WordPatternRule(new ImpexWordDetector(), "batchmode", "", modifier);
		rules[15] = new WordPatternRule(new ImpexWordDetector(), "default", "", modifier);
		rules[16] = new WordPatternRule(new ImpexWordDetector(), "mode", "", modifier);
		rules[17] = new WordPatternRule(new ImpexWordDetector(), "unique", "", modifier);
		
		setRules(rules);
	}
}
