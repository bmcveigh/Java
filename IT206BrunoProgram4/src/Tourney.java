/*
	Brian McVeigh
	Program 4
	03.11.11
*/


import javax.swing.JOptionPane;
public class Tourney {
	
	public static final int MAXIMUMTEAMS = 20;
	
	
	public static void main (String [] args)
	{
		RugbyTeam[] teams = new RugbyTeam[MAXIMUMTEAMS];
		int numberTeams = 0;
		double teamHighestGPA = 0.0;
		String teamHighestGPAName = null;
		double playerHighestGPA = 0.0;
		String playerHighestGPAName = null;
		
		//Get all the rugby teams
		do {
			
			if (numberTeams >= 20)
			{
				JOptionPane.showMessageDialog(null, "Error.");
				break;
			}
			teams[numberTeams] = enter();
			numberTeams++;

		} while (JOptionPane.showConfirmDialog(null, "Another team?")==JOptionPane.YES_OPTION);
		
		String output = "Rosters:\n";

		//Builds the output for the rugby teams
		for (int i = 0; i < numberTeams; i++)
		{
			output += teams[i].toString() + "\n";
			
			//Finds the team average GPA
			if (teamHighestGPA < teams[i].getTeamAverageGPA())
			{
				teamHighestGPA = teams[i].getTeamAverageGPA();
				teamHighestGPAName = teams[i].getTeamName();
			}
			
			//Finds the player's highest GPA
			if (playerHighestGPA < teams[i].getPlayerHighestGPA())
			{
				playerHighestGPA = teams[i].getPlayerHighestGPA();
				playerHighestGPAName = teams[i].getPlayerHighestGPAName();
			}
			
		}
	
		//Outputs the highest GPA and GPA name and the 
		output += "The team highest average GPA("+teamHighestGPA+") is: " + teamHighestGPAName+ "\n";
		output += "The player with the highest GPA("+playerHighestGPA+") is: " + playerHighestGPAName + "\n";
		 
		
		JOptionPane.showMessageDialog(null, output);
	}

	private static RugbyTeam enter()
	{
		RugbyTeam oneTeam = new RugbyTeam();
		while (!oneTeam.setTeamName(JOptionPane.showInputDialog("Enter team name")))
			JOptionPane.showMessageDialog(null, "Error!  Team name must contain 'rugby' ");
		while (!oneTeam.setContact(JOptionPane.showInputDialog("Enter email of contact person")))
			JOptionPane.showMessageDialog(null, "Error!  Contact person must have an EDU account ");
		do {
			while (!oneTeam.setAPlayer(JOptionPane.showInputDialog("Player Name: "),
						Double.parseDouble(JOptionPane.showInputDialog("GPA:  "))))
					JOptionPane.showMessageDialog(null, "Ineligible Player!");

		}while (JOptionPane.showConfirmDialog(null, "Another player?")==JOptionPane.YES_OPTION);

		return oneTeam;
	}
	}