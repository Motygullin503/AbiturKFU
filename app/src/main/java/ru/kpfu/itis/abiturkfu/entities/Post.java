package ru.kpfu.itis.abiturkfu.entities;

/**
 * Created by Bulat on 02.04.2018 at 00:31.
 */
public class Post {

    private String title;

    private String examPoints;
    private String image;

    public Post(String title, String examPoints, String image) {
        this.title = title;
        this.image = image;
        this.examPoints = examPoints;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getExamPoints() {
        return examPoints;
    }

    public void setExamPoints(String examPoints) {
        this.examPoints = examPoints;
    }
}
