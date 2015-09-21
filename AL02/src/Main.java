import NumberGame.GuessMyNumber;

/**
 * Created by kaarel on 21/09/15.
 */
public class Main {
    public static void main(String[] args) {
        int[] array = {321, 432, 902, 327, 840, 1000, 20390};

        GuessMyNumber guessMyNumber = new GuessMyNumber(327);

        System.out.println("Guessed number " + guessMyNumber.playGame(array));
    }
}
