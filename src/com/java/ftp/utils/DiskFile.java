package com.java.ftp.utils;

import java.io.File;

public class DiskFile extends File implements FileInterface{
	public DiskFile() {
		super(".");
	}
	public DiskFile(File theFile) {
		// 构造一个表示此抽象路径名的 file: URI
		super(theFile.toURI());
	}
	
	public String toString() {
		return getName();
	}
}
