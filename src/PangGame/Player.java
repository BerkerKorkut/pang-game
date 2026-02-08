package PangGame;

import java.util.ArrayList;

public class Player {

	private String name;
	private String password;
	private ArrayList<Integer> scores = new ArrayList();
	private ArrayList<Integer> times = new ArrayList();
	private ArrayList<String> dates = new ArrayList();
	
	Player(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	public void addGame(Integer score, Integer time, String date) {
		scores.add(score);
		times.add(time);
		dates.add(date);
	}
	
	public ArrayList<String> history(){
		ArrayList<String> stringList = new ArrayList();
		stringList.add("Game History of User " + name);
		int i = 0;
		while(i < scores.size()) {
			String s = "Score(points): " + scores.get(i) + " Time(seconds): " + times.get(i) + " Date(DD/MM/YYY): " + dates.get(i);
			stringList.add(s);
			i++;
		}
		return stringList;
	}
}
