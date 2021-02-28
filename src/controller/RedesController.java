package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class RedesController {
	public String ip(String nameOs) {
		String result = "";
		String command = "";
		if (nameOs.contains("Windows")) {
			command = "ipconfig";
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(Runtime.getRuntime().exec(command).getInputStream()));
				String x = "";
				while ((x = br.readLine()) != null) {
					if (x.contains("Ethernet")) {
						String name = x.split(" ")[2];
						x = br.readLine();
						while (x != null && !x.contains("encap")) {
							if (x.contains("inet")) {
								result += name + " Ipv4:" + x.split(" ")[1] + "\n";
							}
							x = br.readLine();
						}
					}
				}
			} catch (IOException e) {
			}
		} else {
			try {
				command = "ip -a";
				BufferedReader br = new BufferedReader(
						new InputStreamReader(Runtime.getRuntime().exec(command).getInputStream()));
				String x = "";
				while ((x = br.readLine()) != null) {
					if (x.contains("Ethernet")) {
						String name = x.split(" ")[2];
						x = br.readLine();
						while (x != null && !x.contains("Adaptador")) {
							if (x.contains("IPv4")) {
								result += name + " Ipv4:" + x.split(":")[1] + "\n";
							}
							x = br.readLine();
						}
					}
				}
			} catch (IOException e) {
			}
		}

		return result;
	}

	public String ping(String nameOs) {
		String result = "";
		String command = "";
		String address = "www.google.com.br";
		if (nameOs.contains("Windows")) {
			command = "ping -n 10 " + address;
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(Runtime.getRuntime().exec(command).getInputStream()));
				String x = "";
				while ((x = br.readLine()) != null) {
					if (x.contains("Resposta")) {
						String[] lista = x.split(" ");
						result += lista[4] + " " + lista[3] + "\n";
					} else if (x.contains("dia")) {
						String[] lista = x.split(" ");
						result += lista[12] + " foi a media de tempo em ms";
					}
				}
			} catch (IOException e) {
			}
		} else {
			command = "ping -c 10 " + address;
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(Runtime.getRuntime().exec(command).getInputStream()));
				String x = "";
				while ((x = br.readLine()) != null) {
					if (x.contains("ttl")) {
						String[] lista = x.split(" ");
						result += lista[4] + " " + lista[3] + "\n";
					} else if (x.contains("rtt")) {
						String[] lista = x.split(" ");
						result += lista[4].split("/")[1] + " foi a media de tempo em ms";
					}
				}
			} catch (IOException e) {
			}
		}
		return result;
	}
}
