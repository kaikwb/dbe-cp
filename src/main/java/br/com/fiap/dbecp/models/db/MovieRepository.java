/*
 * Nomes: Kaik Wulck Bassanelli   RM: 96731
 *        Lucas Satoru Shiaku     RM: 97019
 *        Rafael Vieira Pinto     RM: 97117
 * */

package br.com.fiap.dbecp.models.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Page<Movie> findAllByActiveTrue(Pageable page);

}
