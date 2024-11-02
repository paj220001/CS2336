public class Game implements Comparable<Game>
{
    private String name;
    private int highScore;
    private String initials;
    private int plays;
    private double revenue;


    public Game()
    {
        this.name = "";
        this.highScore = -1;
        this.plays = -1;
        this.initials = "";
    }

    public Game(String name, int highScore, String initials, int plays, double revenue)
    {
        this.name = name;
        this.highScore = highScore;
        this.plays = plays;
        this.initials = initials;
        this.revenue = revenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getInitals() {
        return initials;
    }

    public void setInitals(String initals) {
        this.initials = initals;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
        this.revenue = this.plays * 0.25;
    }

    @Override
    public String toString()
    {
        String rev = String.format("%.2f", revenue);
        String line = name + ", " + highScore + ", " + initials + ", " + plays + ", " + "$" + rev + "\n";
        return line;
    }

    @Override
    public int compareTo(Game game)
    {
        return this.name.compareTo(game.getName());
    }

    public String getRevenue() {
        String rev = String.format("%.2f", revenue);
        return rev;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    
}
