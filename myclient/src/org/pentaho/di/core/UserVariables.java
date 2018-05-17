package org.pentaho.di.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.variables.Variables;
import org.pentaho.di.core.xml.XMLHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class UserVariables {
	private static UserVariables userVariables = new UserVariables(); 
	
	private Variables variables = new Variables();
	
	public static UserVariables getInstance() {
		return userVariables;
	}
	
	public VariableSpace getVariable(){
		return variables;
	}
	
	public static void init() throws KettleException {
		String path = System.getProperty("user.dir") + File.separator + Const.KETTLE_USER_VARIABLES_FILE;
		InputStream inputStream = null;
		try {
			File f = new File(path);
			if(f.exists()){
				inputStream = new FileInputStream(path);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (inputStream==null) {
			userVariables.getClass().getResourceAsStream(Const.KETTLE_VARIABLES_FILE);
		}
		if (inputStream==null) {
			inputStream =  userVariables.getClass().getResourceAsStream("/"+Const.KETTLE_VARIABLES_FILE);
		}
		if (inputStream==null) {
			System.out.println("there is no user-variables.xml");
			return;
		}
		try {
			Document doc = XMLHandler.loadXMLFile(inputStream, null, false, false);
			Node varsNode = XMLHandler.getSubNode(doc, "kettle-variables");
			int nrVars = XMLHandler.countNodes(varsNode, "kettle-variable");
			for (int i=0;i<nrVars;i++) {
				Node varNode = XMLHandler.getSubNodeByNr(varsNode, "kettle-variable", i);
		//		String description = XMLHandler.getTagValue(varNode, "description");
				String variable = XMLHandler.getTagValue(varNode, "variable");
                String value = XMLHandler.getTagValue(varNode, "value");
				
                userVariables.variables.setVariable(variable , value);
			}
			
		} catch(Exception e) {
			throw new KettleException("Unable to read file '"+Const.KETTLE_VARIABLES_FILE+"'", e);
		}
	}
	
	
	
}
