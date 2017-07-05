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
	//��������һ��Ӧ�ó����Դ���Ӧ��Ӧ�ó�����е��ã����ùȸ��������һ����ҳ������
	private Desktop desktop = null;
	//�����ļ��б�Ļ������
	private JScrollPane scrollPane = null;
//	Separator jSeparator1;
	//���ù�����
	private JToolBar toolBar = null;
	//���ڻ�ȡ�б��������ģ��
	private JComboBox localDiskComboBox = null;
	//����һ�������ļ��Ĵ���
	private JTable localDiskTable = null;
	// ���ñ����ļ�·���ı�ǩ
	private JLabel localSelFilePathLabel = null;
	// �����ϴ��ļ���ť
	private JButton uploadButton;
	// ����ɾ���ļ���ť
	private JButton delButton = null;
	// ����ˢ���ļ���ť
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
	 * ���沼�����ʼ������
	 */
	private void initComponents() {
		ActionMap actionMap = getActionMap();
//		actionMap.put("delAction", new DelFileAction(this, "ɾ��", null));
//		actionMap.put("uploadAction", new UploadAction(this, "�ϴ�", null));
//		actionMap.put("refreshAction", new RefreshAction(this, "ˢ��", null));
		
		// ���ò��ַ�ʽ
		GridBagConstraints gridBagConstraints;

		toolBar = new JToolBar();
		delButton = new JButton();
		uploadButton = new JButton();
		refreshButton = new JButton();
		localDiskComboBox = new JComboBox();
		//��������Ĵ�С������Ӧ
		localDiskComboBox.setPreferredSize(new Dimension(100, 25));
		//���û������
		scrollPane = new JScrollPane();
		localDiskTable = new JTable();
		// ���������Զ��϶�����, �й�����ק��Ч��
		localDiskTable.setDragEnabled(true);
		localSelFilePathLabel = new JLabel();
		/**
		 *  �����б߿����һ�����⣬ʹ�����ָ����λ�ú�Ĭ��������ı���ɫ���ɵ�ǰ���ȷ������
		 *  TitledBorder.CENTER: �������ı����ڱ߿��ߵ����ġ�
		 *  TitledBorder.ABOVE_TOP: ���������ڱ߿򶥶��ߵ��ϲ���
		 */
		setBorder(javax.swing.BorderFactory.createTitledBorder(null, "����",
				javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.ABOVE_TOP));
		setLayout(new java.awt.GridBagLayout());

		toolBar.setRollover(true);
		toolBar.setFloatable(false);

		delButton.setText("ɾ��");
		delButton.setFocusable(false);
//		delButton.setAction(actionMap.get("delAction"));
		toolBar.add(delButton);

		uploadButton.setText("�ϴ�");
		uploadButton.setFocusable(false);
//		uploadButton.setAction(actionMap.get("uploadAction"));
		toolBar.add(uploadButton);

		refreshButton.setText("ˢ��");
		refreshButton.setFocusable(false);
//		refreshButton.setAction(actionMap.get("refreshAction"));
		toolBar.add(refreshButton);
//		toolBar.add(jSeparator1);
		
		//File.listRoots():�г����õ��ļ�ϵͳ����
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
		//������Ⱦ������Դ��FTP��Դ����������Ⱦ��
		localDiskTable.getColumnModel().getColumn(0).setCellRenderer(
				FTPTableCellRanderer.getCellRanderer());
		//RowSorter ��һ��ʵ�֣���ʹ�� TableModel �ṩ����͹��˲�����
		sorter = new TableRowSorter<TableModel>(localDiskTable.getModel());
		TableStringConverter converter = new TableConverter();
		//���ø���ֵ��ģ��ת��Ϊ�ַ����Ķ���
		sorter.setStringConverter(converter);
		//���� RowSorter��RowSorter �����ṩ�� JTable ������͹��ˡ� 
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
