package hotels;

public class StayDuration {

    private int startDate;

    private int endDate;

    public StayDuration(int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "StayDuration{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
