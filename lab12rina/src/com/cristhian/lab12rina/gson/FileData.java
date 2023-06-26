package com.cristhian.lab12rina.gson;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileData implements Serializable {
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 

	/**
	 * 
	 */
	private static final long serialVersionUID = 4840739895318854695L;
	
	public final String name;
	
	public final String lastModified;
	
	public final boolean isDir;
	
	public final long size;
	
	public final String path;
	
	public final boolean isBack;

	public FileData(File f) throws IOException
	{
		name = f.getName();
		lastModified =getLastModified(f);
		isDir = f.isDirectory();
		if(isDir)
			size = -1;
		else
			size =  Files.size(Paths.get(f.getAbsolutePath()));
		path = f.getAbsolutePath().replace("\\", "/");
		isBack = false;
	}
	
	public FileData(File f, String name) throws IOException
	{
		this.name = name;
		lastModified =getLastModified(f);
		isDir = f.isDirectory();
		if(isDir)
			size = -1;
		else
			size =  Files.size(Paths.get(f.getAbsolutePath()));
		path = f.getAbsolutePath().replace("\\", "/");
		isBack = true;

	}

	private static String getLastModified(File f) {
		return simpleDateFormat.format(new Date(f.lastModified()));
	}
	

}
