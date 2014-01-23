/**
 * Panel for IO Project.
 */
package view;

import controller.ScienceController;
import model.Person;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author CJ Oman
 * @version 1.2 
 * added two listeners and load button. added gameCountLabel.
 * @date Dec 18, 2013 9:07:47 AM
 */
public class SciencePanel extends JPanel
{
	private ScienceController baseController;
	private JButton saveButton;
	private JButton loadButton;
	private JTextField gradeField;
	private JTextField heightField;
	private JTextField weightField;
	private JLabel weightLabel;
	private JLabel heightLabel;
	private JLabel gradeLabel;
	private SpringLayout baseLayout;
	private JLabel BMILabel;
	
	/**
	 * defines each object.
	 * @param baseController
	 */
	public SciencePanel(ScienceController baseController)
	{
		this.baseController = baseController;
		
		saveButton = new JButton("Save collected data");
		loadButton = new JButton("Load collected data");
		gradeField = new JTextField(10);
		gradeLabel = new JLabel("Grade: ");
		heightField = new JTextField(5);
		heightLabel = new JLabel("Height: ");
		weightField = new JTextField(5);
		weightLabel = new JLabel("Weight: ");
		BMILabel = new JLabel("Body Mass Index: ");
		baseLayout = new SpringLayout();
		
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	/**
	 * adds things to the panel.
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(heightField);
		this.add(heightLabel);
		this.add(weightField);
		this.add(weightLabel);
		this.add(saveButton);
		this.add(loadButton);
		this.add(gradeField);
		this.add(gradeLabel);
		this.add(BMILabel);
	}
	
	/**
	 * sets up the Layout for panel.
	 */
	private void setupLayout()
	{
		setBackground(Color.CYAN);
		baseLayout.putConstraint(SpringLayout.NORTH, heightLabel, 14, SpringLayout.SOUTH, gradeLabel);
		baseLayout.putConstraint(SpringLayout.WEST, weightField, 0, SpringLayout.WEST, heightField);
		baseLayout.putConstraint(SpringLayout.WEST, gradeField, 0, SpringLayout.WEST, heightField);
		baseLayout.putConstraint(SpringLayout.NORTH, weightField, 17, SpringLayout.SOUTH, heightField);
		baseLayout.putConstraint(SpringLayout.NORTH, heightField, -3, SpringLayout.NORTH, heightLabel);
		baseLayout.putConstraint(SpringLayout.WEST, heightField, 6, SpringLayout.EAST, heightLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, gradeField, 30, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, weightLabel, 0, SpringLayout.WEST, gradeLabel);
		baseLayout.putConstraint(SpringLayout.WEST, heightLabel, 0, SpringLayout.WEST, gradeLabel);
		baseLayout.putConstraint(SpringLayout.NORTH,weightLabel, 20, SpringLayout.SOUTH, heightLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, gradeLabel, 33, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, gradeLabel, 25, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, BMILabel, 16, SpringLayout.SOUTH, weightField);
		baseLayout.putConstraint(SpringLayout.WEST, BMILabel, 0, SpringLayout.WEST, weightLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, saveButton);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 6, SpringLayout.EAST, saveButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, saveButton, -37, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, saveButton, 0, SpringLayout.EAST, heightField);
	}
	
	/**
	 * decides what clicking buttons do.
	 */
	private void setupListeners()
	{
		saveButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.makeGameFromInput(gradeField.getText(), heightField.getText(), weightField.getText());
				if (tempGame != null)
				{
					baseController.saveGameInfo(tempGame);
					BMILabel.setText("Current game count:" + baseController.getProjectGames().size());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Try again with a valid number");
				}
			}
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.readGameInfo();
				if (tempGame != null)
				{
					gradeField.setText(tempGame.getGameTitle());
					heightField.setText(Integer.toString(tempGame.getFunRanking()));
					String tempRules = "";
					for (String currentRules : tempGame.getGameRules())
					{
						tempRules += currentRules + "\n";
					}
					weightField.setText(tempRules);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Check the save file make sure it is in order.");
				}
			}
		});
	}
}
