package test.ma.org.step;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.Props;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.CentralLogStore;
import org.pentaho.di.core.variables.Variables;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.ui.core.PropsUI;
import org.pentaho.di.ui.spoon.Spoon;

import test.ma.org.step.deal.DealPluginDialog;
import test.ma.org.step.deal.DealPluginMeta;

public class TestDialog {

	public static void main(String []args){
		 try {
		      KettleEnvironment.init();
		    } catch (KettleException e) {
		      e.printStackTrace();
		      System.exit(1);
		    }
		  showDialog();
	}

	private static void showDialog() {
		Variables v = new Variables();
		String filePath = "F:/code/kettle/trans/one.ktr";
		v.setVariable("myTableName", "data_im_chat");
		Trans trans =null;
		try {
			trans = new Trans(v, null, "one", "/",
					filePath);
		} catch (KettleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TransMeta meta =  trans.getTransMeta();


		DealPluginDialog d = new DealPluginDialog(null,new DealPluginMeta(),meta,"name");
	
		

		d.open();
	}
}
