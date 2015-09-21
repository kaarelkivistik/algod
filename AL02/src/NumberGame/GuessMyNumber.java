package NumberGame;

public class GuessMyNumber {
    
    private int numberToGuess;
    
    public GuessMyNumber(int value) {
        this.numberToGuess = value;
    }
    
    /*
     * Oracle 
     * @param your guess
     * @return -1 if the number to guess is smaller than your guess
     *             1 if the number to guess is bigger than your guess
     *             0 if your guess is correct
     */
    public int oracle(int guess) {
        if (this.numberToGuess < guess) {
            return -1;
        } else if (numberToGuess > guess) {
            return 1;
        } else {
            return 0;
        }
    }
    
    /* 
     * Game - Guess a number
     * You have to guess which number the opponent choose from the given array.
     * To ask whether your guess is correct, run function oracle.
     * @param array Unique elements in random order (can be big)
     * @return guessed number
     */
    public int playGame(int[] array) {
        // TODO: write your code here

        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " " + oracle(array[i]));

            if(oracle(array[i]) == 0)
                return array[i];
        }
        
        return 1;
    }
}