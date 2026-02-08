package game.inventories;

public class Task {
    private String question;
    private String answer;
    private long duration;

    public Task(String question, String answer, long duration) {
        this.question = question;
        this.answer = answer;
        this.duration = duration;
    }

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

    public void addDuration(long amount) {
        duration += amount;
    }

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

    public boolean isExpired() {
        return duration == 0;
    }

    @Override
    public String toString() {
        if (duration < 0) return "Otázka: " + question + "\n Trvání: není časově omezené";
        return "Otázka: " + question + "\n Trvání: " + duration + " s";
    }
}
