package test.ma.org.step.deal;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.pentaho.di.core.row.ValueMetaAndData;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.core.PropsUI;
import org.pentaho.di.ui.core.dialog.EnterValueDialog;
import org.pentaho.di.ui.trans.step.BaseStepDialog;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulException;
import org.pentaho.ui.xul.containers.XulDialog;
import org.pentaho.ui.xul.containers.XulRoot;
import org.pentaho.ui.xul.containers.XulWindow;

import test.ma.org.step.txul.TxulDialog;


public class DealPluginDialog extends BaseStepDialog 	implements StepDialogInterface{
	
	private static Class<?> PKG = DealPluginDialog.class; 
//	StepMetaInterface
	private DealPluginMeta input;
	private ValueMetaAndData value;
	private Label wlValName;
	private Text wValName;
	private FormData fdlValName;
	private FormData fdValName;
	private Label wlValue;
//	private Button wbValue;
	private Text wValue;
	private FormData fdlValue;
	private FormData fdbValue;
	private FormData fdValue;
	
	StepDialogInterface innerDialog =null;
	


	public DealPluginDialog(Shell parent, Object in, TransMeta transMeta, String sname)
	{
		super(parent, (BaseStepMeta)in, transMeta, sname);
		input = (DealPluginMeta)in;
		value = input.getValue();
	}

	public String open()
	{
		int a = 200;
		if(200 == a){
			TxulDialog dcDialog = new TxulDialog();
			XulDomContainer container = null;
			try {
				container = dcDialog.getSwtInstance(shell);
			} catch (XulException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			XulRoot root = (XulRoot) container.getDocumentRoot().getRootElement();
			if (root instanceof XulDialog) {
				((XulDialog) root).show();
			}
			if (root instanceof XulWindow) {
				((XulWindow) root).open();
			}
			return stepname;
		}
		
		Shell parent = getParent();
		Display display = parent.getDisplay();
		shell = new Shell(parent, 3312);
		props.setLook(shell);
		setShellImage(shell, input);
		ModifyListener lsMod = new ModifyListener() {

			public void modifyText(ModifyEvent e)
			{
				input.setChanged();
			}

		};
		changed = input.hasChanged();
		FormLayout formLayout = new FormLayout();
		formLayout.marginWidth = 5;
		formLayout.marginHeight = 5;
		shell.setLayout(formLayout);
		shell.setText(BaseMessages.getString(PKG, "maStepDeal.Title"));
		int middle = props.getMiddlePct();
		int margin = 4;
		wlStepname = new Label(shell, 0x20000);
		wlStepname.setText(BaseMessages.getString(PKG, "maStepDeal.StepName.Label"));
		props.setLook(wlStepname);
		fdlStepname = new FormData();
		fdlStepname.left = new FormAttachment(0, 0);
		fdlStepname.right = new FormAttachment(middle, -margin);
		fdlStepname.top = new FormAttachment(0, margin);
		wlStepname.setLayoutData(fdlStepname);
		wStepname = new Text(shell, 18436);
		wStepname.setText(stepname);
		props.setLook(wStepname);
		wStepname.addModifyListener(lsMod);
		fdStepname = new FormData();
		fdStepname.left = new FormAttachment(middle, 0);
		fdStepname.top = new FormAttachment(0, margin);
		fdStepname.right = new FormAttachment(100, 0);
		wStepname.setLayoutData(fdStepname);
		wlValName = new Label(shell, 0x20000);
		wlValName.setText(NormalMessages.getString("maStepDeal.ValueName.Label"));
		props.setLook(wlValName);
		fdlValName = new FormData();
		fdlValName.left = new FormAttachment(0, 0);
		fdlValName.right = new FormAttachment(middle, -margin);
		fdlValName.top = new FormAttachment(wStepname, margin);
		wlValName.setLayoutData(fdlValName);
		wValName = new Text(shell, 18436);
		props.setLook(wValName);
		wValName.addModifyListener(lsMod);
		fdValName = new FormData();
		fdValName.left = new FormAttachment(middle, 0);
		fdValName.right = new FormAttachment(100, 0);
		fdValName.top = new FormAttachment(wStepname, margin);
		wValName.setLayoutData(fdValName);
		wlValue = new Label(shell, 0x20000);
		wlValue.setText(NormalMessages.getString("DmaStepDeal.ValueToAdd.Label"));
		props.setLook(wlValue);
		fdlValue = new FormData();
		fdlValue.left = new FormAttachment(0, 0);
		fdlValue.right = new FormAttachment(middle, -margin);
		fdlValue.top = new FormAttachment(wValName, margin);
		wlValue.setLayoutData(fdlValue);
	//	wbValue = new Button(shell, 0x1000008);
//		props.setLook(wbValue);
//		wbValue.setText(Messages.getString("System.Button.Edit"));
		fdbValue = new FormData();
		fdbValue.right = new FormAttachment(100, 0);
		fdbValue.top = new FormAttachment(wValName, margin);
//		wbValue.setLayoutData(fdbValue);
		wValue = new Text(shell, 18436);
		props.setLook(wValue);
		wValue.addModifyListener(lsMod);
		fdValue = new FormData();
		fdValue.left = new FormAttachment(middle, 0);
//		fdValue.right = new FormAttachment(wbValue, -margin);
		fdValue.right = new FormAttachment(100, 0);
		fdValue.top = new FormAttachment(wValName, margin);
		wValue.setLayoutData(fdValue);
/*//		wbValue.addSelectionListener(new SelectionAdapter(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				super.widgetDefaultSelected(arg0);
				System.out.println("widgetDefaultSelected");
			}

			public void widgetSelected(SelectionEvent arg0) {
				super.widgetSelected(arg0);
				innerDialog =  input.getDialog(shell, input, transMeta, "number");
				innerDialog.open();
			}	
			
		});*/
		
		wOK = new Button(shell, 8);
		wOK.setText(NormalMessages.getString("System.Button.OK"));
		wCancel = new Button(shell, 8);
		wCancel.setText(NormalMessages.getString("System.Button.Cancel"));
		BaseStepDialog.positionBottomButtons(shell, new Button[] {
			wOK, wCancel
		}, margin, wValue);
		lsCancel = new Listener() {

			public void handleEvent(Event e)
			{
				cancel();
			}
		
		};
		lsOK = new Listener() {

			public void handleEvent(Event e)
			{
				ok();
			}
			
		};
		wCancel.addListener(13, lsCancel);
		wOK.addListener(13, lsOK);
		lsDef = new SelectionAdapter() {

			public void widgetDefaultSelected(SelectionEvent e)
			{
				ok();
			}

		};
		wStepname.addSelectionListener(lsDef);
		wValName.addSelectionListener(lsDef);
		shell.addShellListener(new ShellAdapter() {

			public void shellClosed(ShellEvent e)
			{
				cancel();
			}
			
		});
		setSize();
		getData();
		input.setChanged(changed);
		shell.open();
		while (!shell.isDisposed()) 
			if (!display.readAndDispatch())
				display.sleep();
		return stepname;
	}

	public void getData()
	{
		wStepname.selectAll();
		if (value != null)
		{
			wValName.setText(value.getValueMeta().getName());
			wValue.setText(String.valueOf(value.toString()));
		//	wValue.setText((new StringBuilder(String.valueOf(value.toString()))).append(" (").append(value.toStringMeta()).append(")").toString());
		}
	}

	private void cancel()
	{
		stepname = null;
		input.setChanged(changed);
		dispose();
	}

	private void ok()
	{
		stepname = wStepname.getText();
		value.getValueMeta().setName(wValName.getText());
		input.setValue(value);
		dispose();
	}






}
