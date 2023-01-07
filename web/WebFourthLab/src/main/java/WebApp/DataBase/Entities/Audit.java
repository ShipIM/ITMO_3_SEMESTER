package WebApp.DataBase.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "audit_log")
@NoArgsConstructor
@Getter
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "method")
    private String name;

    @Column(name = "time")
    private ZonedDateTime time;

    public Audit(String name, ZonedDateTime time) {
        this.name = name;
        this.time = time;
    }
}
