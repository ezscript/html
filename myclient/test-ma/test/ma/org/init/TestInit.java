package test.ma.org.init;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettlePluginException;

public class TestInit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initEnv();
		org.pentaho.ui.xul.swt.tags.SwtWindow f;
		
		
	//	org.pentaho.di.core.plugins.PluginRegistry.init(); //²å¼þ³õÊ¼»¯
		
	}

	public static void initEnv() {
		try {
			KettleEnvironment.init();
		} catch (KettleException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

}
