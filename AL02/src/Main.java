import NumberGame.GuessMyNumber;

/**
 * Created by kaarel on 21/09/15.
 */
public class Main {
    public static void main(String[] args) {
        int[] array = {321, 432, 902, 327, 840, 1000, 20390};
        int[] array2 = {1, 10, 20, 21, 34, 100, 101, 102, 333, 400, 500, 10000, 10200};

        GuessMyNumber guessMyNumber = new GuessMyNumber(327);
        GuessMyNumber guessMyNumber2 = new GuessMyNumber(500);

        System.out.println("Guessed number (1) " + guessMyNumber.playGame(array));
        System.out.println("Guessed number (2) " + guessMyNumber2.playGame(array2));
    }
}
