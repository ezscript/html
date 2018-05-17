package test.ma.org.step.deal;

import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;

public class DealPluginData extends BaseStepData implements StepDataInterface {

	public RowMetaInterface outputRowMeta;

	public DealPluginData() {
	}
}
