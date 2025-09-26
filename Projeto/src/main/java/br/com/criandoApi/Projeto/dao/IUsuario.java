package br.com.criandoApi.Projeto.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.criandoApi.Projeto.model.Usuario;

public interface IUsuario extends CrudRepository<Usuario, Integer> {
    
}
