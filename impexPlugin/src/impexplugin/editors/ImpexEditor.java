package impexplugin.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class ImpexEditor extends TextEditor {

	private ColorManager colorManager;

	public ImpexEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new ImpexConfiguration(colorManager));
		setDocumentProvider(new ImpexDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
