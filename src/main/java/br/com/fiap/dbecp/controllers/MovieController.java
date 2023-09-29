package br.com.fiap.dbecp.controllers;

import br.com.fiap.dbecp.models.db.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieRepository repository;

    @PostMapping
    public void create(@RequestBody ) {

    }
}
