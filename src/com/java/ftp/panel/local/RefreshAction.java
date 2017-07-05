package com.java.ftp.panel.local;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

/**
 * 刷新本地文件夹下面的文件列表
 * @author zhuch
 *
 */
public class RefreshAction extends AbstractAction{
	private LocalPanel localPanel;
	// 本地的资源管理面板
	public RefreshAction(LocalPanel localPanel, String name, Icon icon) {
		super(name, icon);
		this.localPanel = localPanel;
	}
	public void actionPerformed(ActionEvent e) {
		// 调用本地资源列表的刷新方法，在LocalPanel类中
	}
}
