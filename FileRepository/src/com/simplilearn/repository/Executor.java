package com.simplilearn.repository;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Executor
{
	static final String folderPath = "C:\\SIMPLILEARN\\FILEREPO\\DOCUMENTS";
	
	public static void main(String[] args)
	{
		System.out.println("***** Welcome to LOCKEDME.COM *****");
		System.out.println("***** Designed & Developed By - Tushar Londhe *****");
		File folder = new File(folderPath);
		
		if(!folder.exists())
		{
			folder.mkdirs();		
		}
		
		welcomeJob();
	}

	private static void welcomeJob()
	{
		Scanner scan = null;
		int userOption = 0;
		
		try
		{
			scan = new Scanner(System.in);

			System.out.println("                                                    ");

			System.out.println("Please select specific options to proceed");

			System.out.println("                                                    ");

			System.out.println("          1.) Please press 1 to Display list of files");
			System.out.println("          2.) Please press 2 to start file operations");
			System.out.println("          3.) Please press 3 to exit");
			
			try
			{
				userOption = scan.nextInt();
				
				switch(userOption)
				{
					case 1:
					{
						System.out.println("Listing all files from path: ");
						System.out.println("                                                    ");
						File folder = new File(folderPath);
						listFiles(folder);
						welcomeJob();
						break;
					}
					case 2:
					{							
						startFileOperations();
						break;
					}
					case 3:
					{
						exitProgram(scan);
						break;
					}
					default:
					{
						System.out.println("                                                    ");
						System.err.println("Invalid option selected");
						System.out.println("                                                    ");
						break;
					}
				}
			}
			catch (InputMismatchException e)
			{
				System.out.println("                                                    ");
				System.err.println("Invalid option selected");
				System.out.println("                                                    ");
				welcomeJob();
			}
		}		
		catch (Exception e)
		{
			System.out.println("                                                    ");
			System.err.println("Please input correct option: ");
			System.out.println("                                                    ");
			welcomeJob();
		}
		finally
		{
			scan.close();
		}
	}

	private static void exitProgram(Scanner scan)
	{
		System.out.println("Are you sure, you want to exit, press Y or N");
		String option = scan.next().toUpperCase();
		if("Y".equals(option)) 
		{
			System.out.println("                                                    ");
			System.out.println("Thank you ... visit again");
			System.exit(0);
		}
		else
		{
			welcomeJob();
		}
	}

	private static void listFiles(File folder)
	{		
		File[] files = folder.listFiles();
		for(File f : files)
		{
			System.out.println(f.getAbsolutePath());
			System.out.println("                                                    ");
		}
		
		if(files.length == 0)
		{
			System.out.println("No files are available at this location");
		}
	}

	private static void startFileOperations()
	{
		System.out.println("                                                    ");
		System.out.println("          1.) Please press A to add a specific file");
		System.out.println("          2.) Please press D to delete a specific file");
		System.out.println("          3.) Please press S to search a specific file");
		System.out.println("          4.) Please press P to go back to previous menu");
		System.out.println("          5.) Please press E to exit");
		System.out.println("                                                    ");
		
		Scanner scan = null;
		String userOption = null;
		String fileName = null;
		
		try
		{
			scan = new Scanner(System.in);
			userOption = scan.next().toUpperCase();
			
			switch(userOption)
			{
				case "A":
				{
					System.out.println("                                                    ");
					System.out.println("Please provide name of the file to be added");
					fileName = scan.next();
					File f = new File(folderPath + "\\" + fileName + ".txt");
					f.createNewFile();
					System.out.println("                                                    ");
					System.out.println("File created successfully");
					break;
				}
				case "D":
				{
					System.out.println("                                                    ");
					System.out.println("Please provide name of the file to be deleted");
					fileName = scan.next();
					File f = new File(folderPath + "\\" + fileName + ".txt");
					if(f.isFile())
					{
						f.delete();
						System.out.println("                                                    ");
						System.out.println("File " + fileName + " has been deleted successfully.");						
						break;
					}
					else
					{
						System.out.println("                                                    ");
						System.out.println("File " + fileName + " is not available in the repository.");
						break;
					}					
				}
				case "S":
				{
					System.out.println("                                                    ");
					System.out.println("Please provide name of the file to find");
					fileName = scan.next();
					File folder = new File(folderPath);
					
					File[] files = folder.listFiles();
					for(File f : files)
					{
						if(fileName.equals(f.getName().split("\\.")[0]))
						{
							System.out.println(f.getAbsolutePath());
						}
					}										
					
					break;
				}
				case "P":
				{
					welcomeJob();
				}
				case "E":
				{
					exitProgram(scan);
				}
				default:
				{
					System.out.println("                                                    ");
					System.err.println("Invalid option selected");
					System.out.println("                                                    ");
					break;
				}
			}
			
			startFileOperations();
		}
		catch (InputMismatchException e)
		{
			System.out.println("                                                    ");
			System.err.println("Invalid option selected");
			System.out.println("                                                    ");
			startFileOperations();
		}
		catch (IOException e)
		{
			System.err.println("Something has gone wrong while creating the specified file ... Please try again");
			e.printStackTrace();
			startFileOperations();
		}
	}
}