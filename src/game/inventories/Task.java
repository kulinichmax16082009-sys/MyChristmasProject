package game.inventories;

/**
 * This class represents player's task from teacher that has question, answer and duration
 *
 * @author Maksym Kulynych
 */
public class Task {
    private String question;
    private String answer;
    private long duration;

    public Task() {
        this.question = "";
        this.answer = "";
        this.duration = -1;
    }

    public long getDuration() {
        return duration;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    /**
     * This method simply adds some amount to current task duration
     * @param amount amount of time that must be added
     */
    public void addDuration(long amount) {
        duration += amount;
    }

    /**
     * This method represents a timer of a task, which will decrease it's duration every 1 s
     */
    public void startTimer() {
        if (duration < 0) return;
        new Thread(() -> {
            try {
                while (!isExpired()) {
                    duration--;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * This method checks if task is expired by checking duration
     * @return true - is expired, false - isn't expired
     */
    public boolean isExpired() {
        return duration == 0;
    }

    /**
     * This method is player's task
     * @return player's task
     */
    @Override
    public String toString() {
        if (duration < 0) return "Otázka: " + question + "\n Trvání: není časově omezené";
        return "Otázka: " + question + "\n Trvání: " + duration + " s";
    }
}
