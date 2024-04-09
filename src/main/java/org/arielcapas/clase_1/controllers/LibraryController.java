package org.arielcapas.clase_1.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.arielcapas.clase_1.domain.Dtos.SaveBookDTO;
import org.arielcapas.clase_1.domain_entities.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/library")
@Slf4j
//investigar que hace esa etiqueta de lombok
public class LibraryController {
    public static List<Book> books = new ArrayList<>();
    static {
        books.add(new Book("0316438960","Dune"));
        books.add(new Book("0062871317","Dune: Mesiah"));
        books.add(new Book("1984819882","Dune: Sons of dune"));
        books.add(new Book("0345803485","Stormlight Archive: The way of kings"));
        books.add(new Book("0765326388","Mistborn: The final empire"));
    }
    @GetMapping("/all")
    private String getBooks(Model model) {
        model.addAttribute("books", books);
        return "bookList";
    }

    @GetMapping("/")
    private String mainPage(){
        return "index";
    }

    @GetMapping("/rest")
    private String  mainPageRest(Model model){
        model.addAttribute("books", books);
                return("index-rest-h");
    }

//    el rest al final debe devolver el nuevo intdex rest esto solo para ver la diferencia entre client side rendering y server side rendering
// Usando el render books es un client side puro, cuando lo comento fue serverside rendering,  
    @PostMapping("/save")
    private String saveBook(@ModelAttribute @Valid SaveBookDTO info, BindingResult errors) {
//        log.info(info.toString());
        if(errors.hasErrors()){
            log.error("Hay errores");
            return("errorcito");
        }
//Aqui van los servicios, que ira en una capa distinta que es especificamente para los servicios
        Book book = books.stream()
                .filter(b->b.getISBN().equals(info.getISBN()))
                .findAny()
                .orElse(null);
        if(book == null){
            Book newBook = new Book(info.getISBN(), info.getTittle());
            books.add(newBook);
        }else{
            book.setTitle(info.getTittle());
        }
        return "redirect:/library/all";
    }

}
