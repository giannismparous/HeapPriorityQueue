package erg2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dynamic_Median {

public static void main (String [] args) {
		
		try {
		File file = new File("erg2\\cities.txt");
		Scanner scanNumOfLines = new Scanner(file);
		int lines=0;
		int maxID = -1;
		while (scanNumOfLines.hasNextLine()) {
			int id = scanNumOfLines.nextInt();
			if (id>maxID) maxID = id;
			scanNumOfLines.nextLine();
			lines++;
		}
		scanNumOfLines.close();
		PQ PriorityQueue= new PQ(2*lines,maxID);
		PQ PriorityQueueMedian=new PQ(lines,maxID);
		City median;
		Scanner myReader=new Scanner(file);
		String temp[]=new String[4];
		int c = 0;
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
			PriorityQueue.insert(new City(Integer.parseInt(temp[0]),temp[1],Integer.parseInt(temp[2]),Integer.parseInt(temp[3])));
			int m = PriorityQueue.size();
			if (m%2!=0) m=m/2; else m=(m/2)-1;
			for (int i=0;i<m;i++)PriorityQueueMedian.insert(PriorityQueue.getmax());
			median = PriorityQueue.max();
			for (int i=0;i<m;i++)PriorityQueue.insert(PriorityQueueMedian.getmax());
			c++;
			if (c%5==0) System.out.println(median.getName());
		}
		myReader.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred trying reading the file.");
		      e.printStackTrace();
		    }

		
	}
	
}
