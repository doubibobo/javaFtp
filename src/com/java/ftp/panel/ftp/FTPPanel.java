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
	//ɾ���ļ��а�ť
	private JButton delButton = null;
	//�����ļ���ť
	private JButton  downButton = null;
	//ˢ�°�ť
	private JButton refreshButton = null;
	//���ñ����ͼ
	private JTable ftpDiskTable = null;
	//���ù�����
	private JScrollPane scrollPane = null;
	//���ù�����
	private JToolBar toolBar = null;
	//�����ļ���·����ǩ
	private JLabel ftpFilePathLabel = null;
	//�����ļ�����
	Queue<Object[]> queue = new LinkedList<Object[]>();
	
	/**
	 * ���캯�����������в���
	 */
	public FTPPanel() {
		initComponents();
	}
	
	/**
	 * �������ܣ���ʼ�����
	 */
	private void initComponents() {
		// ���ò˵���
		toolBar = new JToolBar();
		// ����ɾ����ť
		delButton = new JButton();
		// �������ذ�ť
		downButton = new JButton();
		// ����ˢ�°�ť
		refreshButton = new JButton();
		// ���ñ��
		ftpDiskTable = new JTable();
		// ���������Զ��϶�����, �й�����ק��Ч��
		ftpDiskTable.setDragEnabled(true);
		// �����ļ���·��
		ftpFilePathLabel = new JLabel();
		
		//�����ļ��Ĳ��ַ�ʽ
		GridBagConstraints gridBagConstraints = null;
		
		
		//����Զ���ļ��������ɾ����ť
		delButton.setText("ɾ��");
		//������ʧȥ����
		delButton.setFocusable(false);
		//�����ı��Ķ��뷽ʽ
		delButton.setHorizontalAlignment(SwingConstants.CENTER);
		//�����ļ��ı�ǩλ��
		delButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		toolBar.add(delButton);
		
		//����Զ���ļ�����������ذ�ť
		downButton.setText("����");
		downButton.setFocusable(false);
		toolBar.add(downButton);
		
		//����Զ���ļ��������ˢ�°�ť
		refreshButton.setText("ˢ��");
		refreshButton.setFocusable(false);
		toolBar.add(refreshButton);
		
		//�����ļ��Ĳ��ַ�ʽ
		gridBagConstraints = new GridBagConstraints();
		//�����ң����ϵ���0����Ԫ��ʼ
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		//�п���Ŀռ�ʱ���Զ����
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		//��Ԫ��Ŀ��
		gridBagConstraints.weightx = 1.0;
		add(toolBar, gridBagConstraints);
		
		
	}
	public static void main(String args[]) {
		FTPPanel my = new FTPPanel();
	}
}
