public class Highscore {
    public String name;
    public int score;

    Highscore() {
        this.name = "";
        this.score = 0;
    }

    Highscore(String newName, int newScore) {
        this.name = newName.trim();
        this.score = newScore;
    }
}
