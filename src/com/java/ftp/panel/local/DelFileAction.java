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
 * ɾ�������ļ��Ķ���������
 * @author zhuch
 *
 */
class DelFileAction extends AbstractAction{
	// ������Դ��������Ӧ�ö���
	private LocalPanel localPanel;
	/**
	 * ���ɾ����ť���������¼�
	 * @param localPanel
	 * @param name
	 * @param icon
	 */
	public DelFileAction(LocalPanel localPanel, String name, Icon icon) {
		// ���ø���Ĺ��췽��
		super(name, icon);
		// ��ֵ������Դ������������
		this.localPanel = localPanel;
	}
	/**
	 * ɾ�������ļ��Ķ���������
	 */
	public void actionPerformed(ActionEvent e) {
		// ���Ȳ鿴�����ѡ������
		int confirmDialog = JOptionPane.showConfirmDialog(localPanel, "ȷ��Ҫ����ɾ����");
		if (confirmDialog == JOptionPane.YES_OPTION) {
			Runnable runnable = new Runnable() {
				/**
				 * ɾ���ļ��ĵݹ����
				 * @param file Ҫɾ�����ļ�����
				 */
				private void delFile (File file) {
					try {
						// �жϵ��õ��ļ��ǲ����ļ�
						if (file.isFile()) {
							// ���file��һ���ļ�������ɾ���ļ����ļ�����
							boolean delete = file.delete();
							if (!delete) {
								JOptionPane.showMessageDialog(localPanel, file.getAbsoluteFile() + "�ļ��޷�ɾ��" , "ɾ���ļ�", JOptionPane.ERROR_MESSAGE);
								return;
							} 
						} else if (file.isDirectory()){
							// ���file��һ���ļ��У����еݹ�ɾ��
							File[] theSonOftheFile = file.listFiles();//��ȡ���ļ��е��ļ��б�
							if (theSonOftheFile.length > 0) {
								for (File subFile:theSonOftheFile) {
									// �ݹ���ñ�����ɾ�����ļ�����������ļ���
									delFile(subFile);
								}
							}
							// ��ɾ���Ľ�ʣһ���ļ���ʱ��
							boolean delete = file.delete();
							JOptionPane.showMessageDialog(localPanel, file.getAbsoluteFile() + "�ļ��޷�ɾ��" , "ɾ���ļ�", JOptionPane.ERROR_MESSAGE);
							return;//�ݹ��˳�
						}
					} catch(Exception ex) {
						Logger.getLogger(LocalPanel.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
				/**
				 * �̵߳����巽��
				 */
				public void run() {
					File parent = null;
					// ��������ѡ������
					/**
					 * ������Ҫ���ӱ�����������
					 */
					DiskFile file = new DiskFile();
					if (parent == null) {
						// ��ȡ�ļ�����һ���ļ���
						parent = file.getParentFile();
					}
					if (file != null) {
						// ���õݹ鷽��ɾ����ѡ�������
						delFile(file);
					}
					// ɾ������б����ļ��е�ˢ��
					JOptionPane.showMessageDialog(localPanel, "ɾ���ɹ�");
				}
			};
			new Thread(runnable).start();
		}
	}
}
