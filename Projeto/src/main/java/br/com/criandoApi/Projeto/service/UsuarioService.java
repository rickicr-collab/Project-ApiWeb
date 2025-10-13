package br.com.criandoApi.Projeto.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final IUsuario repository;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
   
    

    public UsuarioService(IUsuario repository){
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Usuario> listarUsuarios(){
        logger.info("Usuario: " + getLogado() + " - Acessando Lista de Usuario!");
        return repository.findAll();
    }

    public Usuario criarUsuario(Usuario usuario){
        logger.info("Usuario: " + getLogado() + " - Criando novo Usuário!");
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        return repository.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario){
        logger.info("Usuario: " + getLogado() + " - Atualizando Usuário!");
        String encoderUpdate = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoderUpdate);
        return repository.save(usuario);
    }

    public boolean excluirUsuario(Integer id){
        logger.info("Usuario: " + getLogado() + " - Excluindo Usuário!");
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

    private String getLogado(){
        Authentication userLogado = SecurityContextHolder.getContext().getAuthentication();
        if(userLogado instanceof AnonymousAuthenticationToken){
            return userLogado.getName();
        }
        return "RicDeveloper";
    }

}
