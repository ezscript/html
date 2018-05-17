package test.ma.org.job;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.pentaho.di.core.KettleVariablesList;
import org.pentaho.di.core.UserVariables;
import org.pentaho.di.core.annotations.JobEntry;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.gui.OverwritePrompter;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.job.entries.special.JobEntrySpecial;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.repository.filerep.KettleFileRepository;
import org.pentaho.di.ui.job.dialog.JobExecutionConfigurationDialog;
import org.pentaho.di.ui.job.entries.special.JobEntrySpecialDialog;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import test.ma.org.init.TestInit;

public class TestJob {

	public static void main(String[] args) {

		testClass();
		TestInit.initEnv();

		runJob();
	}

	static String jobPath = "F:/code/kettle/jobs/b.xml";

	private static void runJob() {
		JobMeta meta = null;
		try {
			Document document = XMLHandler.loadXMLFile(jobPath);
			Element root = document.getDocumentElement();
			// meta = new JobMeta(root, null, null);
			meta = new JobMeta(new FileInputStream(jobPath),null,null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Map<String, String> defaultValMap =	KettleVariablesList.getInstance().getDefaultValueMap();
		
		
		Job job = new Job(null, meta);
		job.initializeVariablesFrom(UserVariables.getInstance().getVariable());
		job.setVariable("a", "12314");
		job.setVariable("b", "content");
		job.setVariable("c", "00821");
		
	//	job.setParentVariableSpace("");

		String val = job.getVariable("hohoho");
		System.out.println(val);
		job.run();
		
		job.waitUntilFinished();
	}

	private static void testClass() {
		Job j;
		org.pentaho.di.ui.spoon.job.JobGraph f;
		JobExecutionConfigurationDialog d; // 529

		JobEntrySpecialDialog b;// strat job

		// class JobEntrySpecial extends JobEntryBase implements Cloneable,
		// JobEntryInterface
		JobEntry ji;
		// org.pentaho.di.job.Job.execute();

		JobEntrySpecial entry;
		// ui/job-graph.xul

		// ת��job
		// JobTrans.Specification.Group.Label=Transformation specification
		// JobTrans.TransformationFile.Label=Transformation filename\:
		org.pentaho.di.ui.job.entries.trans.JobEntryTransDialog dialog;

	}
}
