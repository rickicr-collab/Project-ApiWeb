package br.com.criandoApi.Projeto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @GetMapping("/usuarios")
    public String testo(){
        return "Olá, World!";
    }

}
