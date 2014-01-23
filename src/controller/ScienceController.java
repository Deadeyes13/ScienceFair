/**
 * Controller for IO Project
 */
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.Person;
import view.ScienceFrame;

/**
 * @author CJ Oman
 * @version 1.3 
 * added scanner. added makeGame method, save game method, and checkNumber method added pickRandom, readAll, convertToText..
 * @date Dec 18, 2013 9:07:10 AM
 */
public class ScienceController
{
	/**
	 *  Reference GUI
	 */
	private ScienceFrame appFrame;

	/**
	 *  Reference to ProjectGames.
	 */
	private ArrayList<Person> projectPerson;
	
	/**
	 * 
	 */
	public ScienceController()
	{
		projectPerson = new ArrayList<Person>();
	}
	
	/**
	 * @return the projectGames
	 */
	public ArrayList<Person> getProjectGames()
	{
		return projectPerson;
	}

	
	/**
	 * Starts the Program.
	 */
	public void start()
	{
		appFrame = new ScienceFrame(this);
	}
	
	/**
	 * shows how to read game info.
	 * @return Game Info
	 */
	public Person readPersonInfo()
	{
		String fileName = "save file.txt"; // Without a path it will look to the directory of the project!
		File currentSaveFile = new File(fileName);
		
		Scanner fileReader;
		Person currentPerson = null;
		int weight = 0;
		String gradeLevel = "";
		ArrayList<String> height = new ArrayList<String>();
			
		/**
		 * Major Scanner Methods!!!
		 * .next() - the next string seperated by whitespacce so if the file had "twas brillig and the slithey.." .next() would return twas
		 * .nextLine() - returns an entire line
		 * .nextInt() - returns the next integer value
		 * .nextBoolean() - returns the next boolean value 
		 * .nextDouble() - the next double value
		 */
		
		try
		{
			fileReader = new Scanner(currentSaveFile);
			gradeLevel = fileReader.nextLine();
			weight = fileReader.nextInt();
			while(fileReader.hasNext())
			{
				height.add(fileReader.nextLine());
			}
			
			currentPerson = new Person(height, weight, gradeLevel);
			fileReader.close();
		}
		catch(FileNotFoundException currentFileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, currentFileDoesNotExist.getMessage());
		}
		
		return currentPerson;
		
	}
	
	/**
	 * Reads Game Info.
	 * @return gameInfo
	 */
	private String readAllPersonInfo()
	{
		String fileContents = "";
		String fileName = "saveFile.txt";
		File currentSaveFile = new File(fileName);
		Scanner fileReader;
		
		try
		{
			fileReader = new Scanner(currentSaveFile);
			while(fileReader.hasNext())
			{
				fileContents += fileReader.nextLine();
			}
			fileReader.close();
		}
		catch(FileNotFoundException currentFileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, currentFileDoesNotExist.getMessage());
		}
		
		return fileContents;
	}
	
	/**
	 * Takes text and splits it up to make game object.
	 * @param currentInfo
	 */
	private void convertTextToPerson(String currentInfo)
	{
		String [] personChunks = currentInfo.split(";");
		
		for(String currentBlock : personChunks)
		{
			int currentIndex = currentBlock.indexOf("\n");
			String grade = currentBlock.substring(0, currentIndex);
			int nextIndex = currentBlock.indexOf("\n", currentIndex);
			String weight = currentBlock.substring(currentIndex + 1, nextIndex);
			String height = currentBlock.substring(nextIndex + 1);
			Person currentPerson = makePersonFromInput(grade, weight, height);
			projectPerson.add(currentPerson);
		}
	}
	
	/**
	 * picks a random games save file.
	 * @return save game file
	 */
	public Person pickRandomPersonFromSaveFile()
	{
		Person currentPerson = null;
		
		String allInfo = readAllPersonInfo();
		convertTextToPerson(allInfo);
		int randomIndex = (int) (Math.random() * (double) projectPerson.size());
		currentPerson = projectPerson.get(randomIndex);
		
		return currentPerson;
	}
	
	/*
	 * creates the game.
	 * @param gameTitle
	 * @param gameRanking
	 * @param gameRules
	 * @return
	 */
	public Person makePersonFromInput(String gradeLevel, String weight, String height)
	{
		Person currentPerson = new Person();
		currentPerson.setGradeLevel(gradeLevel);
		
		if(checkNumberFormat(weight))
		{
			currentPerson.setWeight(Integer.parseInt(weight));
		}
		else
		{
			return null;
		}
		
		String[] temp = height.split("\n");
		ArrayList<String> tempRules = new ArrayList<String>();
		
		for (String tempWord : temp)
		{
			tempRules.add(tempWord);
		}
		currentPerson.setHeight(tempRules);
		
		return currentPerson;
	}
	
	/**
	 * checks if the number really is a number.
	 * @param toBeParsed
	 * @return the number.
	 */
	private boolean checkNumberFormat(String toBeParsed)
	{
		boolean isNumber = false;
		
		try
		{
			int valid = Integer.parseInt(toBeParsed);
			isNumber = true;
		}
		catch (NumberFormatException error)
		{
			JOptionPane.showMessageDialog(appFrame, "Please try again with an actual number for the ranking.");
		}
		
		return isNumber;
	}
	
	/**
	 * shows how to save the game.
	 * @param currentGame
	 */
	public void saveGameInfo(Person currentPerson)
	{
		PrintWriter gameWriter;
		String saveFile = "save file.txt";
		
		try
		{
			gameWriter = new PrintWriter(saveFile); //Creates the save file.
			
			gameWriter.append(currentPerson.getGradeLevel()+"\n");
			gameWriter.append(currentPerson.getWeight()+"\n");
			for(int count = 0; count < currentPerson.getHeight().size(); count++)
			{
				gameWriter.append(currentPerson.getHeight().get(count)+"\n");
			}
				
			gameWriter.append(";"+"\n");
			
			gameWriter.close(); //Required to prevent corruption of date and maintain security of the file.
		}
		catch(FileNotFoundException noFileExists)
		{
			JOptionPane.showMessageDialog(appFrame, "Could not create the save file. :(");
			JOptionPane.showMessageDialog(appFrame, noFileExists.getMessage());
		}
	}
}
