package model;

public class Lesson {
    private Long id;
    private String lessonName;
    private String description;
    public Lesson(){

    }

    public Lesson(String lessonName, String description) {
        this.lessonName = lessonName;
        this.description = description;
    }

    public Lesson(Long id, String lessonName, String description) {
        this.id = id;
        this.lessonName = lessonName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonName='" + lessonName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
