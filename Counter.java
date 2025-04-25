/**
 * The type Counter.
 *
 * @author Nitay Damti 209458413
 * @version 19.0.2
 * @since 2023-14-06
 */
public class Counter {
    private int count;

    /**
     * Instantiates a new Counter.
     *
     * @param count the count
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
    void increase(int number) {
        this.count += number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
// subtract number from current count.
    void decrease(int number) {
        this.count -= number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
// get current count.
    int getValue() {
        return this.count;
    }
}