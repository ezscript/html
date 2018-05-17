package test.ma.org.step.txul;

import org.pentaho.ui.xul.XulComponent;
import org.pentaho.ui.xul.XulException;
import org.pentaho.ui.xul.components.XulMessageBox;
import org.pentaho.ui.xul.containers.XulDialog;
import org.pentaho.ui.xul.containers.XulRoot;
import org.pentaho.ui.xul.containers.XulWindow;
import org.pentaho.ui.xul.impl.AbstractXulEventHandler;

public class DataHandler extends AbstractXulEventHandler {

	public void testOpenData() {
		org.pentaho.ui.database.event.DataHandler f;
		showMessage("这是消息!", true);

	}

	protected void showMessage(String message, boolean scroll) {
		try {
			XulMessageBox box = (XulMessageBox) document
					.createElement("messagebox"); //$NON-NLS-1$
			box.setTitle("消息");
			box.setMessage(message);
			box.setModalParent(((XulRoot) document
					.getElementById("baidubaike_daima_lizhi")).getRootObject());
			if (scroll) {
				box.setScrollable(true);
				box.setWidth(500);
				box.setHeight(400);
			}
			box.open();
		} catch (XulException e) {
			System.out.println("Error creating messagebox " + e.getMessage());
		}
	}

	public void onCancel() {
		close();
	}

	public void onOK() {
		close();
	}
	
	public void getSQL() {
		System.out.println("getSQL");
	}
	
	public void exploreDatabase() {
		System.out.println("exploreDatabase");
	}
	

	private void close() {
		XulComponent window = document.getElementById("baidubaike_daima_lizhi"); //$NON-NLS-1$

		if (window == null) { // window must be root
			window = document.getRootElement();
		}
		if (window instanceof XulDialog) {
			((XulDialog) window).hide();
		} else if (window instanceof XulWindow) {
			((XulWindow) window).close();
		}
	}
}
