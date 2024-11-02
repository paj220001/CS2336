public class Game implements Comparable<Game> {
    // Instance variables to hold the game's name, high score, player's initials, number of plays, and total revenue
    private String name;
    private int highScore;
    private String initials;
    private int plays;
    private double revenue;

    // Default constructor that initializes the game with default values
    public Game() {
        this.name = "";
        this.highScore = -1;
        this.plays = -1;
        this.initials = "";
    }

    // Parameterized constructor to initialize the game with specific values
    public Game(String name, int highScore, String initials, int plays, double revenue) {
        this.name = name;
        this.highScore = highScore;
        this.plays = plays;
        this.initials = initials;
        this.revenue = revenue;
    }

    // Getter method for the name of the game
    public String getName() {
        return name;
    }

    // Setter method for the name of the game
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for the high score
    public int getHighScore() {
        return highScore;
    }

    // Setter method for the high score
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    // Getter method for the player's initials
    public String getInitals() {
        return initials;
    }

    // Setter method for the player's initials
    public void setInitals(String initials) {
        this.initials = initials;
    }

    // Getter method for the number of plays
    public int getPlays() {
        return plays;
    }

    // Setter method for the number of plays and updates the revenue based on the plays
    public void setPlays(int plays) {
        this.plays = plays;
        this.revenue = this.plays * 0.25; // Each play generates $0.25 in revenue
    }

    // Override the toString method to return the game information as a formatted string
    @Override
    public String toString() {
        String rev = String.format("%.2f", revenue); // Format the revenue to two decimal places
        String line = name + ", " + highScore + ", " + initials + ", " + plays + ", " + "$" + rev + "\n";
        return line;
    }

    // Implement the compareTo method to compare games based on their names (alphabetically)
    @Override
    public int compareTo(Game game) {
        return this.name.compareTo(game.getName());
    }

    // Getter method for the revenue, formatted as a string
    public String getRevenue() {
        String rev = String.format("%.2f", revenue); // Format the revenue to two decimal places
        return rev;
    }

    // Setter method for the revenue
    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
