package test.ma.org.step.deal;

import java.util.List;
import java.util.Map;
import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.core.CheckResult;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.exception.*;
import org.pentaho.di.core.row.*;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.*;
import org.w3c.dom.Node;

public class DealPluginMeta extends BaseStepMeta implements StepMetaInterface {

	private ValueMetaAndData value;
	
	
	public DealPluginMeta() {
	}

	public ValueMetaAndData getValue() {
		return value;
	}

	public void setValue(ValueMetaAndData value) {
		this.value = value;
	}

	public String getXML() throws KettleException {
		
		String retval = "";
		retval = (new StringBuilder(String.valueOf(retval)))
				.append("    <values>").append(Const.CR).toString();
		if (value != null)
			retval = (new StringBuilder(String.valueOf(retval))).append(
					value.getXML()).toString();
		retval = (new StringBuilder(String.valueOf(retval)))
				.append("      </values>").append(Const.CR).toString();
		return retval;
	}

	public void getFields(RowMetaInterface r, String origin,
			RowMetaInterface info[], StepMeta nextStep, VariableSpace space) {
		if (value != null) {
			ValueMetaInterface v = value.getValueMeta();
			v.setOrigin(origin);
			r.addValueMeta(v);
		}
	}

	public Object clone() {
		Object retval = super.clone();
		return retval;
	}

	public void loadXML(Node stepnode, List databases, Map counters)
			throws KettleXMLException {
		try {
			value = new ValueMetaAndData();
			Node valnode = XMLHandler.getSubNode(stepnode, "values", "value");
			if (valnode != null) {
				System.out.println((new StringBuilder("reading value in "))
						.append(valnode).toString());
				value.loadXML(valnode);
			}
		} catch (Exception e) {
			throw new KettleXMLException(
					"Unable to read step info from XML node", e);
		}
	}

	public void setDefault() {
		value = new ValueMetaAndData(new ValueMeta("Êý¾Ý", 1), new Double(
				110.011D));
		value.getValueMeta().setLength(12);
		value.getValueMeta().setPrecision(4);
	}

	public void readRep(Repository rep, ObjectId id_step, List databases,
			Map counters) throws KettleException {
		try {
			String name = rep.getStepAttributeString(id_step, 0, "value_name");
			String typedesc = rep.getStepAttributeString(id_step, 0,
					"value_type");
			String text = rep.getStepAttributeString(id_step, 0, "value_text");
			boolean isnull = rep.getStepAttributeBoolean(id_step, 0,
					"value_null");
			int length = (int) rep.getStepAttributeInteger(id_step, 0,
					"value_length");
			int precision = (int) rep.getStepAttributeInteger(id_step, 0,
					"value_precision");
			int type = ValueMeta.getType(typedesc);
			value = new ValueMetaAndData(new ValueMeta(name, type), null);
			value.getValueMeta().setLength(length);
			value.getValueMeta().setPrecision(precision);
			if (isnull) {
				value.setValueData(null);
			} else {
				ValueMetaInterface stringMeta = new ValueMeta(name, 2);
				if (type != 2)
					text = ValueDataUtil.trim(text);
				value.setValueData(value.getValueMeta().convertData(stringMeta,
						text));
			}
		} catch (KettleDatabaseException dbe) {
			throw new KettleException((new StringBuilder(
					"error reading step with id_step=")).append(id_step)
					.append(" from the repository").toString(), dbe);
		} catch (Exception e) {
			throw new KettleException((new StringBuilder(
					"Unexpected error reading step with id_step="))
					.append(id_step).append(" from the repository").toString(),
					e);
		}
	}

	public void saveRep(Repository rep, ObjectId id_transformation,
			ObjectId id_step) throws KettleException {
		try {
			rep.saveStepAttribute(id_transformation, id_step, "value_name",
					value.getValueMeta().getName());
			rep.saveStepAttribute(id_transformation, id_step, 0, "value_type",
					value.getValueMeta().getTypeDesc());
			rep.saveStepAttribute(id_transformation, id_step, 0, "value_text",
					value.getValueMeta().getString(value.getValueData()));
			rep.saveStepAttribute(id_transformation, id_step, 0, "value_null",
					value.getValueMeta().isNull(value.getValueData()));
			rep.saveStepAttribute(id_transformation, id_step, 0,
					"value_length", value.getValueMeta().getLength());
			rep.saveStepAttribute(id_transformation, id_step, 0,
					"value_precision", value.getValueMeta().getPrecision());
		} catch (KettleDatabaseException dbe) {
			throw new KettleException(
					(new StringBuilder(
							"Unable to save step information to the repository, id_step="))
							.append(id_step).toString(), dbe);
		}
	}

	public void check(List remarks, TransMeta transmeta, StepMeta stepMeta,
			RowMetaInterface prev, String input[], String output[],
			RowMetaInterface info) {
		if (prev == null || prev.size() == 0) {
			CheckResult cr = new CheckResult(3,
					"Not receiving any fields from previous steps!", stepMeta);
			remarks.add(cr);
		} else {
			CheckResult cr = new CheckResult(1, (new StringBuilder(
					"Step is connected to previous one, receiving "))
					.append(prev.size()).append(" fields").toString(), stepMeta);
			remarks.add(cr);
		}
		if (input.length > 0) {
			CheckResult cr = new CheckResult(1,
					"Step is receiving info from other steps.", stepMeta);
			remarks.add(cr);
		} else {
			CheckResult cr = new CheckResult(4,
					"No input received from other steps!", stepMeta);
			remarks.add(cr);
		}
	}

	public StepDialogInterface getDialog(Shell shell, StepMetaInterface meta,
			TransMeta transMeta, String name) {
		return new DealPluginDialog(shell, meta, transMeta, name);
	}

	public StepInterface getStep(StepMeta stepMeta,
			StepDataInterface stepDataInterface, int cnr, TransMeta transMeta,
			Trans disp) {
		return new DealPlugin(stepMeta, stepDataInterface, cnr, transMeta, disp);
	}

	public StepDataInterface getStepData() {
		return new DealPluginData();
	}
}
