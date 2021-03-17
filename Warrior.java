package zadachi;

import java.util.ArrayList;
import java.util.List;

public class Warrior {
    int level = 1;
    int experience1 = 100;
    int experience = 100;
    int ostatok = 100;
    List<String> achieve = new ArrayList<>();
    String[] rang = {"Pushover", "Novice", "Fighter",
            "Warrior", "Veteran", "Sage", "Elite",
            "Conqueror", "Champion", "Master", "Greatest"};
    String rank = rang[0];

    public void checkLevel(){
        this.experience += experience1-ostatok;
        do {
            if (this.experience >= 10000){
                this.experience = 10000;
                this.level = 100;
                break;
            }
            else if (this.experience1 >= 200 && this.level != 100){
                this.level++;
                this.experience1 -= 100;
            }
        }
        while (this.experience1 >= 200);
        ostatok = this.experience1;
    }

    public void checkRank(){
        if (level == 100){
            rank = rang[10];
        }
        else {
            rank = rang[level/10];
        }
    }

    public String training(String s, int experience, int level){
        if (this.level >= level){
            achieve.add(s);
            this.experience1 += experience;
            checkLevel();
            checkRank();
            return s;
        }
        else {
            return "Not strong enough";
        }
    }

    public String battle(int level){
        if (level < 1 || level > 100) return "Invalid level";
        if (level == this.level){
            this.experience1 += 10;
        }
        else if ((this.level-level) == 1){
            this.experience1 += 5;
        }
        else if ((level/10) > (this.level/10) && (level - this.level) >= 5) return "You've been defeated";
        else if (level > this.level){
            this.experience1 += 20 * (level - this.level) * (level - this.level);
        }
        if ((this.level - level) >= 2){
            checkLevel();
            checkRank();
            return "Easy fight";
        }
        else if ((this.level - level) == 1 || (this.level - level) == 0) {
            checkLevel();
            checkRank();
            return "A good fight";
        }
        else if (level > this.level){
            checkLevel();
            checkRank();
            return "An intense fight";
        }
        return "";
    }
    public List<String> achievements() {
        return achieve;
    }

    public int level() {
        return level;
    }

    public int experience() {
        return experience;
    }

    public String rank() {
        return rank;
    }
}
