package com.java.ftp.utils;

import java.io.File;

public class DiskFile extends File implements FileInterface{
	public DiskFile() {
		super(".");
	}
	public DiskFile(File theFile) {
		// ����һ����ʾ�˳���·������ file: URI
		super(theFile.toURI());
	}
	
	public String toString() {
		return getName();
	}
}
