/**
 * The game superclass for IO Project.
 */
package model;

import java.util.ArrayList;

/**
 * @author CJ Oman
 * @version 1.0
 * @date Dec 13, 2013 8:57:30 AM
 * Added data members, constructors and default play method.
 */
public class Person
{
	private ArrayList<String> height;
	private int weight;
	private String gradeLevel;
	
	public Person()
	{
		height = new ArrayList<String>();
		weight = 0;
		gradeLevel = "";
	}
	
	/**
	 * Defines the ArrayList.
	 * @param gameRules
	 * @param funRanking
	 * @param gameTitle
	 */
	public Person(ArrayList<String> height, int weight, String gradeLevel)
	{
		this.height = height;
		this.weight = weight;
		this.gradeLevel = gradeLevel;
	}
	
	/**
	 * @return the gameRules
	 */
	public ArrayList<String> getHeight()
	{
		return height;
	}

	/**
	 * @return the funRanking
	 */
	public int getWeight()
	{
		return weight;
	}

	/**
	 * @return the gameTitle
	 */
	public String getGradeLevel()
	{
		return gradeLevel;
	}

	/**
	 * @param gameRules the gameRules to set
	 */
	public void setHeight(ArrayList<String> height)
	{
		this.height = height;
	}

	/**
	 * @param funRanking the funRanking to set
	 */
	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	/**
	 * @param gameTitle the gameTitle to set
	 */
	public void setGradeLevel(String gradeLevel)
	{
		this.gradeLevel = gradeLevel;
	}

	public void play()
	{
		
	}

}
