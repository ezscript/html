package test.ma.org.trans;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.pentaho.di.core.Const;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.logging.LogLevel;
import org.pentaho.di.core.logging.LoggingObjectType;
import org.pentaho.di.core.logging.SimpleLoggingObject;
import org.pentaho.di.core.parameters.UnknownParamException;
import org.pentaho.di.core.variables.Variables;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransAdapter;
import org.pentaho.di.trans.TransExecutionConfiguration;
import org.pentaho.di.trans.TransListener;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.TransMeta.TransformationType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import test.ma.org.init.TestInit;

public class TestTrans {

	
	private static TransListener adapter = new TransAdapter() {
		public void transStarted(Trans trans) throws KettleException {
		    
	    }
	  
		public void transActive(Trans trans) {
		}

		public void transIdle(Trans trans) {
		}

		public void transFinished(Trans trans) {
		//	checkTransEnded();
		//	checkErrorVisuals();
		//	stopRedrawTimer();
			System.out.println("end" + trans.getName());
			//  .sig
		}
	};

	/**
	 * @param args
	 * @throws KettleXMLException
	 */
	public static void main(String[] args) throws KettleXMLException {
		// 初始化
		TestInit.initEnv();

		//文件位置
		String filePath = "F:/code/kettle/trans/one.ktr";
		
		// exeConfig
		TransExecutionConfiguration exeConfig = new TransExecutionConfiguration();
		exeConfig.setLogLevel(LogLevel.BASIC);
		
		//Const.NVL(paramMap.get(key), "")
		
		
		//add variables
		Variables v = new Variables();
		v.setVariable("myTableName", "data_im_chat");
		
		
		try {
			Trans trans = new Trans(v, null, "one", "/",
					filePath);

	//		trans.setTransformationType();
			String spoonLogObjectId = UUID.randomUUID().toString();
			
			SimpleLoggingObject spoonLoggingObject = new SimpleLoggingObject(
					"SPOON", LoggingObjectType.SPOON, null);
			spoonLoggingObject.setContainerObjectId(spoonLogObjectId);
			spoonLoggingObject.setLogLevel(exeConfig.getLogLevel());
			
			trans.setParent(spoonLoggingObject);

			trans.setLogLevel(exeConfig.getLogLevel());
			trans.setReplayDate(exeConfig.getReplayDate());
			trans.setRepository(exeConfig.getRepository());
			trans.setMonitored(true);
			
			//

			TransMeta innerMeta =  trans.getTransMeta();
			
			//执行方案
			innerMeta.setTransformationType(TransformationType.SerialSingleThreaded);
			
			//print variables
			printVariables(innerMeta);
			
			//执行 trans
			trans.setSafeModeEnabled(exeConfig.isSafeModeEnabled());

			trans.prepareExecution(new String[0]);
			
		//	boolean halted = trans.hasHaltedSteps();
			
			if (trans.isReadyToStart()) {
			
				trans.addTransListener(adapter);
				trans.execute(null);
			}

		} catch (KettleException e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main-over");

	}

	private static void printVariables(TransMeta transMeta) {
		String [] vs = transMeta.listVariables();
		System.out.println("---------------");
		System.out.println("---------------");
		
		System.out.println(vs.length);
		for(int i = 0; i< vs.length ; i++){
			String key = vs[i];
			System.out.println(key + "=" + transMeta.getVariable(key));
		}
		System.out.println(vs);
		
		System.out.println("---------------");
		System.out.println("---------------");
		
	}

}
