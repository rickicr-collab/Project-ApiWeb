package br.com.criandoApi.Projeto.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.criandoApi.Projeto.dto.UsuarioDto;
import br.com.criandoApi.Projeto.model.Usuario;
import br.com.criandoApi.Projeto.repository.IUsuario;
import br.com.criandoApi.Projeto.security.Token;
import br.com.criandoApi.Projeto.security.TokenUtil;
import jakarta.validation.Valid;

@Service
public class UsuarioService {

    private IUsuario repository;
    private PasswordEncoder passwordEncoder;
   
    

    public UsuarioService(IUsuario repository){
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Usuario> listarUsuarios(){
        List<Usuario> usuarios = repository.findAll();
        return usuarios;
    }

    public Usuario criarUsuario(Usuario usuario){
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        Usuario novoUsuario = repository.save(usuario);
        return novoUsuario;
    }

    public Usuario atualizarUsuario(Usuario usuario){
        String encoderUpdate = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoderUpdate);
        Usuario atualizarUsuario = repository.save(usuario);
        return atualizarUsuario;
    }

    public Boolean excluirUsuario(Integer id){
        repository.deleteById(id);
        return true;
    }

    public Boolean validarSenha(Usuario usuario) {
    String senha = repository.findById(usuario.getId()).get().getSenha();
     boolean validar = passwordEncoder.matches(usuario.getSenha(), senha);
     return validar;
    }

    public Token gerarToken(@Valid UsuarioDto usuario) {
        Usuario user = repository.findByNomeOrEmail(usuario.getNome(), usuario.getEmail());
        if(user != null){
            boolean validar = passwordEncoder.matches(usuario.getSenha(), user.getSenha());
            if(validar){
                return new Token(TokenUtil.criarToken(user));
            }
        }
        return null;
    }

    

}
