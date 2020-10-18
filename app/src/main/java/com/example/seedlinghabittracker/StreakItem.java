package com.example.seedlinghabittracker;

public class StreakItem {
    private int ImageResource;
    private String habitTitle;
    private String habitLength;
    private int streakCount;
    public StreakItem(int ImageResource, String habitTitle, String habitLength, int streakCount)
    {
        this.ImageResource = ImageResource;
        this.habitTitle = habitTitle;
        this.habitLength = habitLength;
        this.streakCount = streakCount;
    }
    public int getImageResource()
    {
        return ImageResource;
    }
    public String getHabitTitle()
    {
        return habitTitle;
    }
    public String getHabitLength()
    {
        return habitLength;
    }
    public int getStreakCount(){return streakCount;}
    public void setStreakCount(int streakCount) { this.streakCount = streakCount; }
}
