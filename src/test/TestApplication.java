package test;

import java.awt.EventQueue;

import application.Application;

public class TestApplication {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Application();
			}
		});
	}
}
