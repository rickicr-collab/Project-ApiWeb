package br.com.criandoApi.Projeto.model;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome_completo", length = 200, nullable = false)
    private String nome;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "telefone", length = 15, nullable = false)
    private String telefone;

    @Column(name = "senha", length = 100, columnDefinition = "TEXT", nullable = false)
    private String senha;


}
