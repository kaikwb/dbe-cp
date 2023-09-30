/*
 * Nomes: Kaik Wulck Bassanelli   RM: 96731
 *        Lucas Satoru Shiaku     RM: 97019
 *        Rafael Vieira Pinto     RM: 97117
 * */

package br.com.fiap.dbecp.models.db;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByName(String name);

    @Transactional
    default Person findOrCreateByName(String name) {
        Person person = findByName(name);

        if (person == null) {
            person = new Person(name);
            save(person);
        }

        return person;
    }
}
