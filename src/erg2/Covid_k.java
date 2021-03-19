package erg2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Covid_k {

	static City Cities[];
	static int lines=0;

	static void exch(int i, int j) {
		City t = Cities[i]; Cities[i] = Cities[j]; Cities[j] = t;
		}
	
	
	static void sink(int k, int N) {
		while (2*k <= N) {
		int j = 2*k;
		if (j < N && Cities[j].compareTo(Cities[j+1])<0) j++;
		if (Cities[k].compareTo(Cities[j])>=0) break;
		exch(k, j);
		k = j;
		}
	} 
	
	static void heapsort() {
		for (int k = lines/2; k >= 1;k--)sink(k, lines);
		while (lines > 1) {
		exch(1, lines);
		sink(1, --lines);
		}
	}
	
	public static void main (String [] args) {
		
		try {
		File file = new File("erg2\\cities.txt");
		Scanner scanNumOfLines = new Scanner(file);
		while (scanNumOfLines.hasNext()) {scanNumOfLines.nextLine();lines++;}
		scanNumOfLines.close();
		System.out.println("Insert k:");
		Scanner scan=new Scanner(System.in);
		int k=scan.nextInt();
		scan.close();
		if (k>lines) {System.out.print("k is bigger than cities. Exiting...");System.exit(0);};
		Cities=new City[lines+1];
		Scanner myReader=new Scanner(file);
		String temp[]=new String[4];
		int i=1;
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			temp=data.split(" ",4);
			if (Integer.parseInt(temp[0])<1 ||Integer.parseInt(temp[0])>999) {
				System.out.println("Invalid ID. Exiting...");
				System.exit(0);
			}
			if ((temp[1]).length()==0 ||(temp[1]).length()>50) {
				System.out.println("Invalid population. Exiting...");
				System.exit(0);
			}
			if (Integer.parseInt(temp[2])<=0 ||Integer.parseInt(temp[2])>10000000) {
				System.out.println("Invalid population. Exiting...");
				System.exit(0);
			}
			if (Integer.parseInt(temp[3])<=0 || Integer.parseInt(temp[3])>Integer.parseInt(temp[2])) {
				System.out.println("Invalid Covid Cases. Exiting...");
				System.exit(0);
			}
			Cities[i]=new City(Integer.parseInt(temp[0]),temp[1],Integer.parseInt(temp[2]),Integer.parseInt(temp[3]));
			i++;
			}
		myReader.close();
		heapsort();
		System.out.println("The top k cities are:");
		for (i=Cities.length-1;i>Cities.length-1-k;i--)System.out.println(Cities[i].getName());
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred trying reading the file.");
		      e.printStackTrace();
		    }
		
		
		
		
	}
}
