package test.ma.org.step.deal;

import org.pentaho.di.i18n.BaseMessages;


public class PKGMessage {
	private static Class<?> PKG = PKGMessage.class; 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = BaseMessages.getString(PKG, "maStepDeal.Title");
		System.out.println(str);
	}

}
