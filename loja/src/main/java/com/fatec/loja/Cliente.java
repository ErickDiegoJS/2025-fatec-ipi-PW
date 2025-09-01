package com.fatec.loja;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Cliente {
    @Id
    private int codigo;
    private String nome;
    private String email;
    private String documento;
    private String telefone;
    private String senha;
    private int ativo;
    private String cidade;
    private String cep;
    private String logradouro;
    private String complemento;
}
