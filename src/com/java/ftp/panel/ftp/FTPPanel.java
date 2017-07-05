package com.java.ftp.panel.ftp;

import java.awt.GridBagConstraints;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class FTPPanel extends JPanel{
	//删除文件夹按钮
	private JButton delButton = null;
	//下载文件按钮
	private JButton  downButton = null;
	//刷新按钮
	private JButton refreshButton = null;
	//设置表格视图
	private JTable ftpDiskTable = null;
	//设置滚动条
	private JScrollPane scrollPane = null;
	//设置工具栏
	private JToolBar toolBar = null;
	//设置文件的路径标签
	private JLabel ftpFilePathLabel = null;
	//设置文件队列
	Queue<Object[]> queue = new LinkedList<Object[]>();
	
	/**
	 * 构造函数，用来进行测试
	 */
	public FTPPanel() {
		initComponents();
	}
	
	/**
	 * 方法功能：初始化组件
	 */
	private void initComponents() {
		// 设置菜单栏
		toolBar = new JToolBar();
		// 设置删除按钮
		delButton = new JButton();
		// 设置下载按钮
		downButton = new JButton();
		// 设置刷新按钮
		refreshButton = new JButton();
		// 设置表格
		ftpDiskTable = new JTable();
		// 启动表格的自动拖动处理, 有关于拖拽的效果
		ftpDiskTable.setDragEnabled(true);
		// 设置文件的路径
		ftpFilePathLabel = new JLabel();
		
		//设置文件的布局方式
		GridBagConstraints gridBagConstraints = null;
		
		
		//设置远程文件夹下面的删除按钮
		delButton.setText("删除");
		//设置其失去焦点
		delButton.setFocusable(false);
		//设置文本的对齐方式
		delButton.setHorizontalAlignment(SwingConstants.CENTER);
		//设置文件的标签位置
		delButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		toolBar.add(delButton);
		
		//设置远程文件夹下面的下载按钮
		downButton.setText("下载");
		downButton.setFocusable(false);
		toolBar.add(downButton);
		
		//设置远程文件夹下面的刷新按钮
		refreshButton.setText("刷新");
		refreshButton.setFocusable(false);
		toolBar.add(refreshButton);
		
		//设置文件的布局方式
		gridBagConstraints = new GridBagConstraints();
		//从左到右，从上到下0个单元格开始
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		//有空余的空间时，自动填充
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		//单元格的宽度
		gridBagConstraints.weightx = 1.0;
		add(toolBar, gridBagConstraints);
		
		
	}
	public static void main(String args[]) {
		FTPPanel my = new FTPPanel();
	}
}
