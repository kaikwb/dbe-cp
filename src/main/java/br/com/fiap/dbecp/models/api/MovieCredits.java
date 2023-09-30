/*
 * Nomes: Kaik Wulck Bassanelli   RM: 96731
 *        Lucas Satoru Shiaku     RM: 97019
 *        Rafael Vieira Pinto     RM: 97117
 * */

package br.com.fiap.dbecp.models.api;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record MovieCredits(
    String rating,
    @NotBlank
    List<String> directors,
    @NotBlank
    List<String> producers,
    @NotBlank
    List<String> writers,
    @NotBlank
    List<String> cast
) {
}
