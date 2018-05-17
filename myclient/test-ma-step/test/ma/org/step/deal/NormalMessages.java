package test.ma.org.step.deal;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.pentaho.di.i18n.BaseMessages;

public class NormalMessages
{

	  private static final String BUNDLE_NAME = "test.ma.org.step.deal.messages.messages_zh_CH"; //$NON-NLS-1$

	  private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	  private NormalMessages() {
	  }

	  public static ResourceBundle getBundle(){
	    if(RESOURCE_BUNDLE == null){
	      RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	    }
	      
	    return RESOURCE_BUNDLE;
	  }
	  
	  public static String getString(String key) {
	    try {
	      return RESOURCE_BUNDLE.getString(key);
	    } catch (MissingResourceException e) {
	      return '!' + key + '!';
	    }
	  }
	  
	  public static String getString(String key, String param1) {
	    try {
	      Object[] args = { param1 };
	      return MessageFormat.format(getString(key), args);
	    } catch (Exception e) {
	      return '!' + key + '!';
	    }
	  }

	  public static String getString(String key, String param1, String param2) {
	    try {
	      Object[] args = { param1, param2};
	      return MessageFormat.format(getString(key), args);
	    } catch (Exception e) {
	      return '!' + key + '!';
	    }
	  }

	  public static String getString(String key, String param1, String param2, String param3) {
	    try {
	      Object[] args = { param1, param2, param3};
	      return MessageFormat.format(getString(key), args);
	    } catch (Exception e) {
	      return '!' + key + '!';
	    }
	  }

	  public static String getString(String key, String param1, String param2, String param3, String param4) {
	    try {
	      Object[] args = { param1, param2, param3, param4 };
	      return MessageFormat.format(getString(key), args);
	    } catch (Exception e) {
	      return '!' + key + '!';
	    }
	  }
	  
	  public static void main(String [] args){
		System.out.println(getString("maStepDeal.Title"));
	  }

}
