import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class GamesScore {
	private	HashMap<String, Game> scores;
	private	HashMap<String, HashMap<String, GScore>> people;
	
	public GamesScore() {
		 scores = new HashMap<String, Game>();
		 people = new HashMap<String, HashMap<String, GScore>>();

	}
	
	public boolean addUser(String name) {
		
		if(people.get(name) != null)
			return false;
		
		people.put(name, new HashMap<String, GScore>());
		return true;
	}
	
	public boolean addUserGame(String name, String gname) {
		
		if(people.get(name) == null)
			return false;
			
		people.get(name).put(gname, new GScore());
		return true;
	}
	
	public boolean updateUserScores(String name, String gname, int newscore) {
	
		if(people.get(name) == null)
			return false;
		
		if(people.get(name).get(gname) == null)
			return false;
		
		people.get(name).get(gname).updateScores(newscore);
		return true;
	}
	
	public boolean updateGameScores(String gname, int newscore){
		
		if(scores.get(gname) == null)
			return false;
		
		scores.get(gname).updateScores(newscore);
		return true;
	}
	
	public boolean update(String name, String gname, int newscore) {
		
		return updateUserScores(name, gname, newscore) && updateGameScores(gname, newscore);
		
	}

	public String getStats(String name) {
		
		if(people.get(name) == null)
			return "User not Found\n";
		
		HashMap<String, GScore> userstats = people.get(name);
		
		String stats = "";
		
		Set<String> keys = userstats.keySet();
		Iterator<String> iter = keys.iterator();
		
		while(iter.hasNext()) {
			String gn = iter.next();
			GScore g = userstats.get(gn);
			
			stats = stats + gn + "\t" + g.getUserHighScore() 
							   + "\t" + scores.get(gn).getOverallHighScore() 
							   + "\t" + scores.get(gn).getOverallAvgScore()
							   + "\t" + g.getUserAvgScoreLastHour()
							   + "\t" + g.getUserAvgScoreLastWeek()
							   + "\t" + g.getUserAvgScoreLastMonth()
							   + "\n";
		}
		
		return stats;
	}
	
}