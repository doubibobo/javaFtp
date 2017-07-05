package com.java.ftp.panel.local;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import com.java.ftp.utils.DiskFile;

/**
 * 删除本地文件的动作处理器
 * @author zhuch
 *
 */
class DelFileAction extends AbstractAction{
	// 本地资源管理面板的应用对象
	private LocalPanel localPanel;
	/**
	 * 点击删除按钮所触发的事件
	 * @param localPanel
	 * @param name
	 * @param icon
	 */
	public DelFileAction(LocalPanel localPanel, String name, Icon icon) {
		// 调用父类的构造方法
		super(name, icon);
		// 赋值本地资源管理面板的引用
		this.localPanel = localPanel;
	}
	/**
	 * 删除本地文件的动作处理方法
	 */
	public void actionPerformed(ActionEvent e) {
		// 首先查看表格中选定的行
		int confirmDialog = JOptionPane.showConfirmDialog(localPanel, "确定要进行删除吗？");
		if (confirmDialog == JOptionPane.YES_OPTION) {
			Runnable runnable = new Runnable() {
				/**
				 * 删除文件的递归调用
				 * @param file 要删除的文件对象
				 */
				private void delFile (File file) {
					try {
						// 判断调用的文件是不是文件
						if (file.isFile()) {
							// 如果file是一个文件，调用删除文件的文件方法
							boolean delete = file.delete();
							if (!delete) {
								JOptionPane.showMessageDialog(localPanel, file.getAbsoluteFile() + "文件无法删除" , "删除文件", JOptionPane.ERROR_MESSAGE);
								return;
							} 
						} else if (file.isDirectory()){
							// 如果file是一个文件夹，进行递归删除
							File[] theSonOftheFile = file.listFiles();//获取该文件夹的文件列表
							if (theSonOftheFile.length > 0) {
								for (File subFile:theSonOftheFile) {
									// 递归调用本方法删除本文件夹下面的子文件夹
									delFile(subFile);
								}
							}
							// 当删除的仅剩一个文件的时候
							boolean delete = file.delete();
							JOptionPane.showMessageDialog(localPanel, file.getAbsoluteFile() + "文件无法删除" , "删除文件", JOptionPane.ERROR_MESSAGE);
							return;//递归退出
						}
					} catch(Exception ex) {
						Logger.getLogger(LocalPanel.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
				/**
				 * 线程的主体方法
				 */
				public void run() {
					File parent = null;
					// 遍历表格的选择内容
					/**
					 * 这里需要增加遍历表格的内容
					 */
					DiskFile file = new DiskFile();
					if (parent == null) {
						// 获取文件的上一级文件夹
						parent = file.getParentFile();
					}
					if (file != null) {
						// 调用递归方法删除所选择的内容
						delFile(file);
					}
					// 删除后进行本地文件夹的刷新
					JOptionPane.showMessageDialog(localPanel, "删除成功");
				}
			};
			new Thread(runnable).start();
		}
	}
}
