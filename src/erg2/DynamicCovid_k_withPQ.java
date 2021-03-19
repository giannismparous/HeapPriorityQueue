package erg2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DynamicCovid_k_withPQ {
	
public static void main (String [] args) {
		
		try {
		File file = new File("erg2\\cities.txt");
		Scanner scanNumOfLines = new Scanner(file);
		int lines=0;
		int maxID =0;
		while (scanNumOfLines.hasNextLine()) {
			int id = scanNumOfLines.nextInt();
			if (id>maxID) maxID = id;
			scanNumOfLines.nextLine();
			lines++;
		}
		scanNumOfLines.close();
		System.out.println("Insert k:");
		Scanner scan=new Scanner(System.in);
		int k=scan.nextInt();
		scan.close();
		if (k>lines) {
			System.out.print("k is bigger than cities. Exiting...");
			System.exit(0);
		}
		Scanner myReader=new Scanner(file);
		PQ PriorityQueue= new PQ(2*k,maxID);
		String temp[]=new String[4];
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
			PriorityQueue.DynamicInsert(new City(Integer.parseInt(temp[0]),temp[1],Integer.parseInt(temp[2]),Integer.parseInt(temp[3])), k);
		}
		myReader.close();
		System.out.println("The top k cities are:");
		while (!PriorityQueue.isEmpty()) 
			{
			System.out.println(PriorityQueue.getmax().getName());
			} 
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred trying reading the file.");
		      e.printStackTrace();
		    }
		
		
		
		
	}
	
}
