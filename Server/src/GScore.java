import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GScore {

	private int user_high_score;
	private int user_avg_score_last_hour;
	private int num_last_hour;
	private int user_avg_score_last_week;
	private int num_last_week;
	private int user_avg_score_last_month;
	private int num_last_month;
	private String currentTime;
	private String lastTime;

	public GScore() {

		user_high_score = 0;
		user_avg_score_last_hour = 0;
		num_last_hour = 0;
		user_avg_score_last_week = 0;
		num_last_week = 0;
		user_avg_score_last_month = 0;
		num_last_month = 0;

	}

	public boolean updateScores(int newscore) {
		currentTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
				.getInstance().getTime());

		// update userhighscore
		if (newscore > user_high_score) {
			user_high_score = newscore;
		}

		// update last hour score
		if (currentTime.substring(9).equals(lastTime.substring(9))) {
			double tmp = (user_avg_score_last_hour * num_last_hour) + newscore;
			num_last_hour++;
			double avg_score = tmp / num_last_hour;
			user_avg_score_last_hour = (int) avg_score;
		} else {
			num_last_hour = 0;
			user_avg_score_last_hour = 0;
			return false;
		}

		// update last week score
		if (currentTime.substring(7).equals(lastTime.substring(7))) {
			double tmp = (user_avg_score_last_week * num_last_week) + newscore;
			num_last_week++;
			double avg_score = tmp / num_last_week;
			user_avg_score_last_week = (int) avg_score;
		} else {
			num_last_week = 0;
			user_avg_score_last_week = 0;
			return false;
		}

		// update last month score
		if (currentTime.substring(5).equals(lastTime.substring(5))) {
			double tmp = (user_avg_score_last_month * num_last_month)
					+ newscore;
			num_last_month++;
			double avg_score = tmp / num_last_month;
			user_avg_score_last_month = (int) avg_score;
		} else {
			num_last_month = 0;
			user_avg_score_last_month = 0;
			return false;
		}

		lastTime = currentTime;
		return true;
	}

	public int getUserHighScore() {
		return user_high_score;
	}

	public int getUserAvgScoreLastHour() {
		return user_avg_score_last_hour;
	}

	public int getUserAvgScoreLastWeek() {
		return user_avg_score_last_week;
	}

	public int getUserAvgScoreLastMonth() {
		return user_avg_score_last_month;
	}

}
