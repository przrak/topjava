package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

<<<<<<< HEAD
public class Meal extends AbstractBaseEntity {
=======
public class Meal {
    private Integer id;

>>>>>>> db3a0e1282e7f7efd08db6b1d428db72b257aebd
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
<<<<<<< HEAD
        super(id);
=======
        this.id = id;
>>>>>>> db3a0e1282e7f7efd08db6b1d428db72b257aebd
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

<<<<<<< HEAD
=======
    public boolean isNew() {
        return id == null;
    }

>>>>>>> db3a0e1282e7f7efd08db6b1d428db72b257aebd
    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
