package com.deonbabushka.dronbabushka.entities;

        import lombok.Data;

        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer userId;
    Integer day;
    Integer lesson;
    Integer number;
}
