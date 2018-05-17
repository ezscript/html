package test.ma.org.init.detail;

import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;

public class DetailOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initStep();
	//	src/kettle-steps.xml
		
	}

	private static void initStep() {
		PluginRegistry.addPluginType(StepPluginType.getInstance());
		
		org.pentaho.di.trans.step.BaseStep sum;
		//description -> TypeLongDesc.CheckSum
		//category    -> Category.Transform
		//tooltip     -> TypeTooltipDesc.CheckSum
		org.pentaho.di.trans.steps.checksum.CheckSumMeta meta =null;
		
		String dialogClassName = meta.getDialogClassName();
		
		org.pentaho.di.ui.trans.steps.checksum.CheckSumDialog f;
		
		/*
		 src/kettle-steps.xml :
		 <steps>
		 	 ...
			 <step id="CheckSum">
			    <description>i18n:org.pentaho.di.trans.step:BaseStep.TypeLongDesc.CheckSum</description>
			    <classname>org.pentaho.di.trans.steps.checksum.CheckSumMeta</classname>
			    <category>i18n:org.pentaho.di.trans.step:BaseStep.Category.Transform</category>
			    <tooltip>i18n:org.pentaho.di.trans.step:BaseStep.TypeTooltipDesc.CheckSum</tooltip>
			    <iconfile>ui/images/CSM.png</iconfile>
			  </step>
		</steps>		  
		 */
	}

}
