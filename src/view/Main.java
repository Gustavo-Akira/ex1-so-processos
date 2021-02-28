package view;

import java.util.Scanner;

import controller.RedesController;

public class Main {
	private static final RedesController controller = new RedesController();

	private static final String OS = System.getProperty("os.name");

	public static void main(String[] args) {
		boolean x = true;
		while (x) {
			System.out.println(" 1- ip \n 2-ping \n 3-sair");
			Scanner sc = new Scanner(System.in);
			String op = sc.nextLine();
			switch (op) {
			case "1":
				System.out.println(controller.ip(OS));
				break;
			case "2":
				System.out.println(controller.ping(OS));
				break;
			case "3":
				x=false;
				break;
			}
			
		}
	}

}
