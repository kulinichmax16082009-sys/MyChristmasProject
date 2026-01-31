package game.inventories;

public class Task {
    private String description;
    private String answer;
    private long duration;

    public Task() {
        description = "";
        duration = 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void addDuration(long amount) {
        duration += amount;
    }

    public void startTimer() {
        try {
            while (!isExpired()) {
                duration--;
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isExpired() {
        return duration <= 0;
    }

    @Override
    public String toString() {
        return "popis: " + description +
                ", trvání: " + duration + " s";
    }
}
