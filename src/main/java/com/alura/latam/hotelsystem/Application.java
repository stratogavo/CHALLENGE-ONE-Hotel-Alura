package com.alura.latam.hotelsystem;

import java.awt.EventQueue;

import com.alura.latam.hotelsystem.ui.views.MenuPrincipal;

public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
