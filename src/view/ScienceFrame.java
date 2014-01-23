/**
 * Frame for IO Project.
 */
package view;

import controller.ScienceController;

import javax.swing.JFrame;

/**
 * @author CJ Oman
 * @version 1.0
 * @date Dec 13, 2013 8:37:36 AM
 */
public class ScienceFrame extends JFrame
{
	/**
	 * IOpanel for the frame.
	 */
	private SciencePanel basePanel;
	
	/**
	 * Constructor for IOframe
	 * @param baseController IOcontroller passed in for MVC relationship.
	 */
	public ScienceFrame(ScienceController baseController)
	{
		basePanel = new SciencePanel(baseController);
		
		setupFrame();
	}
	
	/**
	 * Sets up the frame size and loads the contentPane.
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setSize(400, 400);
		this.setVisible(true);
	}

}
