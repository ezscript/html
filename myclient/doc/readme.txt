...

test/org/pentaho/test/ui/database/DatabaseDialogHarness.java




启动 ：

org.pentaho.di.ui.spoon.Spoon



日志：log4j

/Kettle 4.4.0/src/log4j.xml


转换：

sdffoowerw.dofweyrwef=\u8f6c\u6362\u5df2\u7ecf\u6253\u5f00


  打开文件： 
  org.pentaho.di.ui.spoon.TransFileListener.open();     46
  
  -- 转换已经打开
  
  TransLog.Log.TransformationOpened 
  
  
  org.pentaho.di.ui.spoon.trans.TransGraph
  3325
  
  转换开始： org.pentaho.di.ui.spoon.trans.TransGraph.start()   3272
 
  
转换实体存储：
   org.pentaho.di.repository
   
   
   
2017-11-30

1. 变量 
Variables v = new Variables();
v.setVariable("myTableName", "data_im_chat");


2. 线程数目控制  : 单线程方式
TransMeta innerMeta =  trans.getTransMeta();
//执行方案
innerMeta.setTransformationType(TransformationType.SerialSingleThreaded);


3.结束控制：
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
1.? 映射关系建立
2.? 复杂业务逻辑



