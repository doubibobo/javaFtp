package com.java.ftp.panel.local;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

/**
 * ˢ�±����ļ���������ļ��б�
 * @author zhuch
 *
 */
public class RefreshAction extends AbstractAction{
	private LocalPanel localPanel;
	// ���ص���Դ�������
	public RefreshAction(LocalPanel localPanel, String name, Icon icon) {
		super(name, icon);
		this.localPanel = localPanel;
	}
	public void actionPerformed(ActionEvent e) {
		// ���ñ�����Դ�б��ˢ�·�������LocalPanel����
	}
}
