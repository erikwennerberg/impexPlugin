package impexplugin.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class ImpexConfiguration extends SourceViewerConfiguration {
	private ImpexDoubleClickStrategy doubleClickStrategy;
	private ImpexScanner scanner;
	private ColorManager colorManager;

	public ImpexConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			ImpexPartitionScanner.IMPEX_COMMENT };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new ImpexDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected ImpexScanner getImpexScanner() {
		if (scanner == null) {
			scanner = new ImpexScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(ImpexColorConstants.DEFAULT))));
		}
		return scanner;
	}


	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();


		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getImpexScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr =
			new NonRuleBasedDamagerRepairer(
				new TextAttribute(
					colorManager.getColor(ImpexColorConstants.IMPEX_COMMENT)));
		reconciler.setDamager(ndr, ImpexPartitionScanner.IMPEX_COMMENT);
		reconciler.setRepairer(ndr, ImpexPartitionScanner.IMPEX_COMMENT);

		return reconciler;
	}

}