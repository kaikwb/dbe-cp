/*
 * Nomes: Kaik Wulck Bassanelli   RM: 96731
 *        Lucas Satoru Shiaku     RM: 97019
 *        Rafael Vieira Pinto     RM: 97117
 * */

package br.com.fiap.dbecp.models.db;

import jakarta.persistence.*;

@Table(name = "persons", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name")
})
@Entity(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_id_seq")
    @SequenceGenerator(name = "persons_id_seq", sequenceName = "persons_id_seq")
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
