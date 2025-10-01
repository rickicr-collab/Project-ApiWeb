package br.com.criandoApi.Projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.criandoApi.Projeto.model.Usuario;

public interface IUsuario extends JpaRepository<Usuario, Integer> {
    
}
