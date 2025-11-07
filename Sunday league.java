import org.w3c.dom.css.Rect;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.io.*;

class Team{
    private String id,name;
    private long matches,grade,diff;

    public Team(String id, String name, long matches, long grade, long diff) {
        this.id = id;
        this.name = name;
        this.matches = matches;
        this.grade = grade;
        this.diff = diff;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMatches() {
        return matches;
    }

    public void setMatches(long matches) {
        this.matches = matches;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }

    public long getDiff() {
        return diff;
    }

    public void setDiff(long diff) {
        this.diff = diff;
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(id).append(" ");
        builder.append(name).append(" ");
        builder.append(matches).append(" ");
        builder.append(diff).append(" ");
        builder.append(grade).append(" ");
        builder.append("\n------------------\n");
        return builder.toString();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Vector<Team> v = new Vector<>();
        for(int i = 1;i <= 20;i++){
            sc.nextLine();
            String id = sc.nextLine();
            String name = sc.nextLine();
            long matches = sc.nextLong();
            long diff = sc.nextLong();
            long grade = sc.nextLong();
            v.add(new Team(id,name,matches,grade,diff));
            sc.nextLine();
            sc.nextLine();
        }
        Map<String,Team> mp = new HashMap<>();
        for(var tmp : v){
            mp.put(tmp.getName(),tmp);
        }
        for(int i = 1; i <= 10;i++){
            String []parts = sc.nextLine().split(" - ");
            String []team1 = parts[0].split("\\s+");
            String []team2 = parts[1].split("\\s+");
            String team1Name = "";
            for(int j = 0;j < team1.length - 1;j++){
                team1Name += team1[j] + " ";
            }
            team1Name = team1Name.trim();
            int team1Sorce = Integer.parseInt(team1[team1.length - 1]);
            String team2Name ="";
            for(int j = 1; j < team2.length; j++){
                team2Name += team2[j] + " ";
            }
            team2Name = team2Name.trim();
            int team2Sorce = Integer.parseInt(team2[0]);

            Team teamOne = mp.get(team1Name.trim());
            Team teamSecond = mp.get(team2Name.trim());
            teamOne.setMatches(teamOne.getMatches() + 1);
            teamSecond.setMatches(teamSecond.getMatches() + 1);

            if(team1Sorce > team2Sorce){
                teamOne.setGrade(teamOne.getGrade() + 3);
            }
            else if(team2Sorce > team1Sorce){
                teamSecond.setGrade(teamSecond.getGrade() + 3);
            }
            else{
                teamOne.setGrade(teamOne.getGrade() + 1);
                teamSecond.setGrade(teamSecond.getGrade() + 1);
            }
            teamOne.setDiff(teamOne.getDiff() + (team1Sorce - team2Sorce));
            teamSecond.setDiff(teamSecond.getDiff() + (team2Sorce - team1Sorce));
        }
        Collections.sort(v, new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                if(o1.getGrade() != o2.getGrade()){
                    return (int)(o2.getGrade() - o1.getGrade());
                }
                else if(o1.getDiff() != o2.getDiff()){
                    return (int)(o2.getDiff() - o1.getDiff());
                }
                else return o1.getId().compareTo(o2.getId());
            }
        });
        for(int i = 0;i < v.size();i++){
            System.out.print("#" + (i + 1) + " ");
            System.out.print(v.get(i));
        }
    }
}
