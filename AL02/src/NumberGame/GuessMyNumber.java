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
        boolean expectArrayIsSorted = true;

        for(int i = 1; i < Math.min(100, array.length); i++)
            if(array[i - 1] > array[i]){
                expectArrayIsSorted = false;
                break;
            }

        if(expectArrayIsSorted) {
            System.out.println("Array is sorted, use binary search.");

            int low = 0;
            int high = array.length - 1;

            while(high >= low) {
                int mid = (low + high) / 2;
                int guess = oracle(array[mid]);

                if(guess == -1) {
                   high = mid - 1;
                } else if(guess == 1) {
                    low = mid + 1;
                } else {
                    return array[mid];
                }
            }
        }

        System.out.println("Array is not sorted, use for-loop.");

        for (int i = 0; i < array.length; i++) {
            if (oracle(array[i]) == 0)
                return array[i];
        }

        return 1;
    }
}