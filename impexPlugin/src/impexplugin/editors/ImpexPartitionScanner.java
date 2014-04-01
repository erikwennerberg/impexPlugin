package impexplugin.editors;

import org.eclipse.jface.text.rules.*;

public class ImpexPartitionScanner extends RuleBasedPartitionScanner {
	public final static String IMPEX_COMMENT = "__impex_comment";

	public ImpexPartitionScanner() {

		IToken impexComment = new Token(IMPEX_COMMENT);
		

		IPredicateRule[] rules = new IPredicateRule[1];

		rules[0] = new EndOfLineRule("#", impexComment);

		setPredicateRules(rules);
	}
}
