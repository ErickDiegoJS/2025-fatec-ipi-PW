package com.fatec.loja;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//indica ao Spring que essa interface faz parte da camada de repositório (acesso a dados)
@Repository
//cria um repositório JPA para a entidade Cliente
//ao estender JpaRepository, já ganha métodos prontos
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{
    //Define uma query SQL nativa para busca no banco de dados
    @Query(value = "select * from cliente where email=?1 and senha=?2", nativeQuery = true)
    //O retorno é um Optional<Cliente>, ou seja:
    //Optional.empty() se não encontrar.
    //Optional.of(cliente) se encontrar.
    public Optional<Cliente> fazerLogin(String email, String senha);

    //Busca todos os clientes onde o campo ativo = 0
    @Query(value="select * from cliente where ativo=0", nativeQuery = true)
    //Esse método retorna uma lista de clientes inativos
    public List<Cliente> listarInativos();
}