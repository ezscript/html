package test.ma.org.step.deal;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.row.RowDataUtil;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStep;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;

// Referenced classes of package be.ibridge.kettle.dummy:
//			DummyPluginMeta, DummyPluginData

public class DealPlugin extends BaseStep
	implements StepInterface
{

	private DealPluginData data;
	private DealPluginMeta meta;

	public DealPlugin(StepMeta s, StepDataInterface stepDataInterface, int c, TransMeta t, Trans dis)
	{
		super(s, stepDataInterface, c, t, dis);
	}

	public boolean processRow(StepMetaInterface smi, StepDataInterface sdi)
		throws KettleException
	{
		meta = (DealPluginMeta)smi;
		data = (DealPluginData)sdi;
		Object r[] = getRow();
		if (r == null)
		{
			setOutputDone();
			return false;
		}
		if (first)
		{
			first = false;
			data.outputRowMeta = getInputRowMeta().clone();
			meta.getFields(data.outputRowMeta, getStepname(), null, null, this);
		}
		Object extraValue = meta.getValue().getValueData();
		Object outputRow[] = RowDataUtil.addValueData(r, data.outputRowMeta.size() - 1, extraValue);
		putRow(data.outputRowMeta, outputRow);
		if (checkFeedback(linesRead))
			logBasic((new StringBuilder("Linenr ")).append(linesRead).toString());
		return true;
	}

	public boolean init(StepMetaInterface smi, StepDataInterface sdi)
	{
		meta = (DealPluginMeta)smi;
		data = (DealPluginData)sdi;
		return super.init(smi, sdi);
	}

	public void dispose(StepMetaInterface smi, StepDataInterface sdi)
	{
		meta = (DealPluginMeta)smi;
		data = (DealPluginData)sdi;
		super.dispose(smi, sdi);
	}

	public void run()
	{
		logBasic("Starting to run...");
		try
		{
			while (processRow(meta, data) && !isStopped());
		}
		catch (Exception e)
		{
			logError((new StringBuilder("Unexpected error : ")).append(e.toString()).toString());
			logError(Const.getStackTracker(e));
			setErrors(1L);
			stopAll();
		}finally{
			dispose(meta, data);
			logBasic((new StringBuilder("Finished, processing ")).append(linesRead).append(" rows").toString());
			markStop();
		}
	}
}
