package org.arielcapas.clase_1.controllers;

import jakarta.validation.Valid;
import org.arielcapas.clase_1.domain.Dtos.SaveBookDTO;
import org.arielcapas.clase_1.domain_entities.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryRestController {
    private ResponseEntity<List<Book>> findAll(){
        return new ResponseEntity<>(
                LibraryController.books,
                HttpStatus.OK
        );
    }
}

@PostMapping("/save")
private ResponseEntity<String> save(@RequestBody @Valid SaveBookDTO info, BindingResult errors)
//Aqui debe realizar el proceso del save que esta en LibraryController junto con el status de la peticion