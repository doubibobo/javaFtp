package com.java.ftp.panel.local;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

import com.java.ftp.FTPClientFrame;

public class LocalPanel extends JPanel{	
	Queue<Object[]> queue = new LinkedList<Object[]>();
//	private UploadThread uploadThread = null;
	//用来关联一组应用程序以打开相应的应用程序进行调用，如用谷歌浏览器打开一个网页等内容
	private Desktop desktop = null;
	//本地文件列表的滑动面板
	private JScrollPane scrollPane = null;
//	Separator jSeparator1;
	//设置工具栏
	private JToolBar toolBar = null;
	//用于获取列表项的数据模型
	private JComboBox localDiskComboBox = null;
	//设置一个本地文件的窗口
	private JTable localDiskTable = null;
	// 设置本地文件路径的标签
	private JLabel localSelFilePathLabel = null;
	// 设置上传文件按钮
	private JButton uploadButton;
	// 设置删除文件按钮
	private JButton delButton = null;
	// 设置刷新文件按钮
	private JButton refreshButton = null;
	private TableRowSorter<TableModel> sorter;
//	FTPClientFrame frame = null;

	public LocalPanel() {
		initComponents();
	}

	public LocalPanel(FTPClientFrame client_Frame) {
//		frame = client_Frame;
		if (Desktop.isDesktopSupported()) {
			desktop = Desktop.getDesktop();
		}
		initComponents();
	}

	/**
	 * 界面布局与初始化方法
	 */
	private void initComponents() {
		ActionMap actionMap = getActionMap();
//		actionMap.put("delAction", new DelFileAction(this, "删除", null));
//		actionMap.put("uploadAction", new UploadAction(this, "上传", null));
//		actionMap.put("refreshAction", new RefreshAction(this, "刷新", null));
		
		// 设置布局方式
		GridBagConstraints gridBagConstraints;

		toolBar = new JToolBar();
		delButton = new JButton();
		uploadButton = new JButton();
		refreshButton = new JButton();
		localDiskComboBox = new JComboBox();
		//调整组件的大小，自适应
		localDiskComboBox.setPreferredSize(new Dimension(100, 25));
		//设置滑动面板
		scrollPane = new JScrollPane();
		localDiskTable = new JTable();
		// 启动表格的自动拖动处理, 有关于拖拽的效果
		localDiskTable.setDragEnabled(true);
		localSelFilePathLabel = new JLabel();
		/**
		 *  向现有边框添加一个标题，使其具有指定的位置和默认字体和文本颜色（由当前外观确定）。
		 *  TitledBorder.CENTER: 将标题文本置于边框线的中心。
		 *  TitledBorder.ABOVE_TOP: 将标题置于边框顶端线的上部。
		 */
		setBorder(javax.swing.BorderFactory.createTitledBorder(null, "本地",
				javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.ABOVE_TOP));
		setLayout(new java.awt.GridBagLayout());

		toolBar.setRollover(true);
		toolBar.setFloatable(false);

		delButton.setText("删除");
		delButton.setFocusable(false);
//		delButton.setAction(actionMap.get("delAction"));
		toolBar.add(delButton);

		uploadButton.setText("上传");
		uploadButton.setFocusable(false);
//		uploadButton.setAction(actionMap.get("uploadAction"));
		toolBar.add(uploadButton);

		refreshButton.setText("刷新");
		refreshButton.setFocusable(false);
//		refreshButton.setAction(actionMap.get("refreshAction"));
		toolBar.add(refreshButton);
//		toolBar.add(jSeparator1);
		
		//File.listRoots():列出可用的文件系统根。
//		localDiskComboBox.setModel(new DefaultComboBoxModel(File.listRoots())); 
//		localDiskComboBox.addItemListener(new java.awt.event.ItemListener() {
//			public void itemStateChanged(java.awt.event.ItemEvent evt) {
//				localDiskComboBoxItemStateChanged(evt);
//			}
//		});
		toolBar.add(localDiskComboBox);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(toolBar, gridBagConstraints);
//		localDiskTable.setModel(new LocalTableModel());
//		localDiskTable.setShowHorizontalLines(false);
//		localDiskTable.setShowVerticalLines(false);
//		localDiskTable.getTableHeader().setReorderingAllowed(false);
//		localDiskTable.addMouseListener(new java.awt.event.MouseAdapter() {
//			public void mouseClicked(java.awt.event.MouseEvent evt) {
//				localDiskTableMouseClicked(evt);
//			}
//		});
		scrollPane.setViewportView(localDiskTable);
		scrollPane.getViewport().setBackground(Color.WHITE);
		//设置渲染本地资源和FTP资源表格组件的渲染器
		localDiskTable.getColumnModel().getColumn(0).setCellRenderer(
				FTPTableCellRanderer.getCellRanderer());
		//RowSorter 的一个实现，它使用 TableModel 提供排序和过滤操作。
		sorter = new TableRowSorter<TableModel>(localDiskTable.getModel());
		TableStringConverter converter = new TableConverter();
		//设置负责将值从模型转换为字符串的对象。
		sorter.setStringConverter(converter);
		//设置 RowSorter。RowSorter 用于提供对 JTable 的排序和过滤。 
		localDiskTable.setRowSorter(sorter);
		sorter.toggleSortOrder(0);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(scrollPane, gridBagConstraints);

		localSelFilePathLabel.setBorder(javax.swing.BorderFactory
				.createEtchedBorder());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		add(localSelFilePathLabel, gridBagConstraints);
	}	
}
