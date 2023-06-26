package com.cristhian.lab12rina;

import java.io.File;

public class Apps {
	
	
	public static File getProjectDir() {
		
		//return new File("C:\\Users\\Yader Ulloa\\eclipse-workspace\\lab12rina");
		return new File("C:/usr/src/lab12rina");

	}

	public static File getExplorerIndex() {
		return new File(getProjectDir(), "editoresWeb/Explorador-de-archivos/index.html");
	}

	public static File getTextEditorIndex() {
		return new File(getProjectDir(),"editoresWeb/iOS-HTMLTextEditor-master/iOS-HTMLTextEditor/CKEditor/demo.html");
	}

	public static File getHtmlEditorIndex() {
		return new File(getProjectDir(),"editoresWeb/CodeUi-master/index.html");
	}


}
