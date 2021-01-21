package com.LockedMe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class ApplicationDetails {
static Scanner scanner = new Scanner(System.in);
	
	public static boolean Welcome()
	{
		System.out.println("************************************************");
		System.out.println("Welcome to LockedMe.com");
		System.out.println("Developers details");
		System.out.println("Name :  Md Sanaullah Baig ");
		System.out.println("Orgranization:  wt pvt.limited");
		System.out.println("contact number :  +91 7416516474");
		System.out.println("email :  sanubaig@gmail.com");
		System.out.println("*************************************************");
		System.out.println("THe Application is used for the following ");
		System.out.println("# Sorting files inside directory and its subdirectory");
		System.out.println("# Adding file to a directory");
		System.out.println("# Deleting a file from the directory");
		System.out.println("# Searching a file in a directory");
		System.out.println("\n\nPress Yes to Proceed");
		String str = scanner.next();
		while(true)
		{
			if(str.equalsIgnoreCase("yes"))
			{
				return true;
			}else
			{
				System.out.println("/n Invalid entry. Please try again ..");
				str = scanner.next();
			}
		}
		
	}
	
  public static void choices() {
	 while(true) {
		System.out.println("\n\nPlease enter your choice");
		System.out.println("Press 1 List current file names in ascending order");
		System.out.println("Press 2 List user interfaces");
		System.out.println("Press 3 Close the Application");
		int n = scanner.nextInt();
		switch(n)
		{
		case 1: 
		  	    List<String> list = new ArrayList<>();
			    list = listFiles();
			    System.out.println("List of files in Root folder and sub folders");
			    for(int i=0; i< list.size(); i++)
			     {
				   System.out.println(list.get(i));
			     }
			    break;
			
		case 2: 
 			  userInterfaces();
			  break;
		case 3: 
			  System.out.println("Application closed");
			  System.exit(0);
			  break;
		
		default: 
			  System.out.println("Wrong Choice!!  Please select 1 or 2 or 3");
		}
	}
  }
  

  public static ArrayList<String> listFiles() {
	  System.out.println("Enter Root Directory Path");
	  String location = scanner.next();
	  File file = null;
	  try {
		  file = new File(location);
	  }catch(NullPointerException e) {
		  System.out.println("Please enter correct Root Directory");
		  e.printStackTrace();
	  }
	  
	  File[] fs = file.listFiles();
	  ArrayList<String> list = new ArrayList<>();
	  list = read(fs,list);
	  Collections.sort(list);
	  return list;
  }
  
  
  public static ArrayList<String>read(File file[],ArrayList<String> list){
	  for (File eachfile : file) {
		  list.add(eachfile.getName());
		  if(eachfile.isDirectory()) {
			  File fs[] = eachfile.listFiles();
			  read(fs,list);
		  }
	  }
	  return list;
  }
  
  
  public static void userInterfaces() {
	  System.out.println("\nPlease Enter your Choice **\n");
	  System.out.println("Press 1: Add a file to the existing directory list");
	  System.out.println("Press 2: Delete a user specified file from the existing Directory list");
	  System.out.println("Press 3: Search a user specified file from the main directory");
	  System.out.println("Press 4: Navigate back to the main context\n");
	  
	  int n = scanner.nextInt();
	  switch(n) {
	  case 1:
		        addFile();
		        break;
	  case 2: 
		        deleteFile();
		        break;
	  case 3:
		        searchFile();
		        break;
	  case 4: 
		        choices();
		        break;
		        
	  default:   
		  System.out.println("Wrong choice entered!");
	  }
  }
  
		        	
	public static void addFile() {
		System.out.println("Enter Existing Directory Path with new file name \n Example:E:\\temp\\p.txt\n");
		Path path = Paths.get(scanner.next());
		List<String> list = new ArrayList<>();
		try {
			Files.write(path,list,StandardOpenOption.CREATE_NEW);
		}catch(IOException e) {
			System.out.println("\nFile Exists!");
			e.printStackTrace();
		}
		System.out.println("\nFile Created!");
	}
	
	
	
	public static void deleteFile() {
		System.out.println("Enter the file to be deleted with absolute path\n");
		Path path = Paths.get(scanner.next());
		try
		{
			Files.deleteIfExists(path);
			System.out.println("File Deleted!");
		}catch(DirectoryNotEmptyException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void searchFile() {
		System.out.println("\n Enter the file to be searched with extension. Example : text.txt ");
		{
			String string = scanner.next();
			ArrayList<String> list = new ArrayList<>();
			list = listFiles();
			if(list.contains(string)) {
				System.out.println("\nFile Exist!");
			}else
				System.out.println("\nFile does not Exist!");
		}
	}
	
	
	public static void main(String[] args) {
		boolean check = Welcome();
		if(check) {
		    choices();
		}
	}
}

		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
