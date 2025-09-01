package com.fatec.loja;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository 
extends JpaRepository<Produto, Integer> {

    //Lista produtos em destaque ordenado crescente
    @Query("SELECT p FROM Produto p WHERE p.destaque > 0 ORDER BY p.destaque ASC")
    public List<Produto> listarVitrine();

    //Busca produtos pelo termo 
    @Query("SELECT p FROM Produto p WHERE p.nome LIKE %:termo% OR p.descritivo LIKE %:termo%")
    public List<Produto> fazerBusca(@Param("termo") String termo);

}
