package br.com.criandoApi.Projeto.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
    private String nome;
    private String email;
    private String senha;


    public UsuarioDto(String nome, String email, String senha){
        super();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    

}
