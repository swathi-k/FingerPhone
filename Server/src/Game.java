
public class Game {
	private int overall_high_score;
	private int overall_avg_score;
	private int num_of_games_played;
	
	public Game() {
		overall_high_score = 0;
		overall_avg_score = 0;
		num_of_games_played = 0;
	}
	
	public boolean updateScores(int newscore) {
		
		//update high score
		if(newscore > overall_high_score)
			overall_high_score = newscore;
		
		//update avg score
		double tmp = (overall_avg_score * num_of_games_played) + newscore; 
		num_of_games_played++;
		double avg_score = tmp / num_of_games_played;
		overall_avg_score = (int) avg_score;
		
		return true;
	}
	
	public int getOverallHighScore() {
		return overall_high_score;
	}
	
	public int getOverallAvgScore() {
		return overall_avg_score;
	}
	
}
