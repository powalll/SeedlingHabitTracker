package com.example.seedlinghabittracker;

public class StreakItem {
    private int ImageResource;
    private String habitTitle;
    private String habitLength;

    public StreakItem(int ImageResource, String habitTitle, String habitLength)
    {
        this.ImageResource = ImageResource;
        this.habitTitle = habitTitle;
        this.habitLength = habitLength;
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
}
