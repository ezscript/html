package test.ma.org.step.txul;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.test.ui.database.DatabaseDialogHarness;
import org.pentaho.ui.database.DatabaseConnectionDialog;
import org.pentaho.ui.database.Messages;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulException;
import org.pentaho.ui.xul.containers.XulDialog;
import org.pentaho.ui.xul.containers.XulRoot;
import org.pentaho.ui.xul.containers.XulWindow;
import org.pentaho.ui.xul.swt.SwtXulLoader;

public class TxulDialog {

	public static final String DIALOG_DEFINITION_FILE = "test/ma/org/step/txul/txul.xul";
	private static final String BUNDLE_NAME = "test.ma.org.step.txul.dialog"; 
	
	private Map<String, String> extendedClasses = new HashMap<String, String>();

	public TxulDialog() {
	}

	public void registerClass(String key, String className) {
		extendedClasses.put(key, className);
	}

	public XulDomContainer getSwtInstance(Shell shell) throws XulException {

		XulDomContainer container = null;
		SwtXulLoader loader = new SwtXulLoader();

		Iterable<String> keyIterable = extendedClasses.keySet();
		for (Object key : keyIterable) {
			loader.register((String) key, extendedClasses.get(key));
		}
		loader.setOuterContext(shell);
		container = loader
				.loadXul(DIALOG_DEFINITION_FILE, ResourceBundle.getBundle(BUNDLE_NAME));
		container.initialize();
		return container;
	}

	public static void main(String[] args) {
		XulDomContainer container = null;
		try {
			TxulDialog dcDialog = new TxulDialog();
			container = dcDialog.getSwtInstance(new Shell(SWT.NONE));
		} catch (XulException e) {
			e.printStackTrace();
		}

		XulRoot root = (XulRoot) container.getDocumentRoot().getRootElement();
		if (root instanceof XulDialog) {
			((XulDialog) root).show();
		}
		if (root instanceof XulWindow) {
			((XulWindow) root).open();
		}

		
/*
		String message = "abc";
		Shell shell = new Shell(SWT.DIALOG_TRIM);
		shell.setLayout(new RowLayout());
		Label label = new Label(shell, SWT.NONE);
		label.setText(message);
		Button button = new Button(shell, SWT.NONE);
		button.setText("Edit Database ..."); //$NON-NLS-1$

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				try {
				//	showDialog();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch()) {
				shell.getDisplay().sleep();
			}
		}*/
	}
}
