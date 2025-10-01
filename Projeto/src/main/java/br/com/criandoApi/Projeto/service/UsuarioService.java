package br.com.criandoApi.Projeto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.criandoApi.Projeto.model.Usuario;
import br.com.criandoApi.Projeto.repository.IUsuario;

@Service
public class UsuarioService {

    private IUsuario repository;

    public UsuarioService(IUsuario repository){
        this.repository = repository;
    }

    public List<Usuario> listarUsuarios(){
        List<Usuario> usuarios = repository.findAll();
        return usuarios;
    }

    public Usuario criarUsuario(Usuario usuario){
        Usuario novoUsuario = repository.save(usuario);
        return novoUsuario;
    }

    public Usuario atualizarUsuario(Usuario usuario){
        Usuario atualizarUsuario = repository.save(usuario);
        return atualizarUsuario;
    }

    public Boolean excluirUsuario(Integer id){
        repository.deleteById(id);
        return true;
    }

}
