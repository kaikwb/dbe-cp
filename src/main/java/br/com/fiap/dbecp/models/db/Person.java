package br.com.fiap.dbecp.models.db;

import jakarta.persistence.*;

@Table(name = "persons", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name")
})
@Entity(name = "Person")
public class Person {
    @Id
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
}
