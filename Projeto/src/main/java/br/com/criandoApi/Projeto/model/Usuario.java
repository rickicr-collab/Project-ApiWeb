package br.com.criandoApi.Projeto.model;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @NotBlank(message = "O nome é obrigatório!")
    @Size(min = 3, message = "O noome deve tr no minimo 3 caracteres!")
    @Column(name = "nome_completo", length = 200, nullable = false)
    private String nome;

    @Email(message = "O email deve ser válido!")
    @NotBlank(message = "O email é obrigatório!")
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @NotBlank(message = "O telefone é obrigatório!")
    @Column(name = "telefone", length = 15, nullable = false)
    private String telefone;

    @NotBlank(message = "A senha é obrigatória!")
    @Column(name = "senha", length = 100, columnDefinition = "TEXT", nullable = false)
    private String senha;


}
