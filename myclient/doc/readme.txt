...

test/org/pentaho/test/ui/database/DatabaseDialogHarness.java




���� ��

org.pentaho.di.ui.spoon.Spoon



��־��log4j

/Kettle 4.4.0/src/log4j.xml


ת����

sdffoowerw.dofweyrwef=\u8f6c\u6362\u5df2\u7ecf\u6253\u5f00


  ���ļ��� 
  org.pentaho.di.ui.spoon.TransFileListener.open();     46
  
  -- ת���Ѿ���
  
  TransLog.Log.TransformationOpened 
  
  
  org.pentaho.di.ui.spoon.trans.TransGraph
  3325
  
  ת����ʼ�� org.pentaho.di.ui.spoon.trans.TransGraph.start()   3272
 
  
ת��ʵ��洢��
   org.pentaho.di.repository
   
   
   
2017-11-30

1. ���� 
Variables v = new Variables();
v.setVariable("myTableName", "data_im_chat");


2. �߳���Ŀ����  : ���̷߳�ʽ
TransMeta innerMeta =  trans.getTransMeta();
//ִ�з���
innerMeta.setTransformationType(TransformationType.SerialSingleThreaded);


3.�������ƣ�
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

?????
1.? ӳ���ϵ����
2.? ����ҵ���߼�



