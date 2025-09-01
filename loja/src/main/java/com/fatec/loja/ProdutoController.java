package com.fatec.loja;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//* permite que qualquer dominio acesse os recursos da api, ou seja, aceita requisições de qualquer origem
@CrossOrigin(origins = "*")
public class ProdutoController {
    @Autowired
    ProdutoRepository bd;

    //Define que o método abaixo deve ser chamado sempre que o sistema receber uma requisição HTTP POST no endpoint /api/produto.
    @PostMapping("/api/produto")
    //Um metodo void que recebe como atributo um obj Produto
    //O conteudo do corpo da requisição é convertido em um objeto produto
    public void gravar(@RequestBody Produto obj){
        //Salva o objeto
        bd.save(obj);
        System.out.println("Produto gravado com sucesso!");
    }

    @GetMapping("/api/produto/{codigo}")
    //Diz que o valor de {codigo} da URL deve ser passado para a variável c
    //É verificado a existencia de um porduto com o codigo c
    //Retorna o produto encontrado ou um produto vazio
    public Produto carregar(@PathVariable("codigo") int c){
        if(bd.existsById(c)){
            return bd.findById(c).get();
        } else {
            return new Produto();
        }
    }

    @PutMapping("/api/produto")
    public void alterar(@RequestBody Produto obj){
        if(bd.existsById(obj.getCodigo())){
            bd.save(obj);
            System.out.println("Produto alterado com sucesso!");
        }
    }

    @DeleteMapping("/api/codigo/{codigo}")
    public void remover(@PathVariable("codigo") int codigo){
        if(bd.existsById(codigo)){
            //Deleta o produto com o respectivo codigo do banco de dados
            bd.deleteById(codigo);
            System.out.println("Produto removido com sucesso!");
        }
    }

    @GetMapping("/api/produtos")
    //Retorna uma lista de produto 
    public List<Produto> listar(){
        return bd.findAll();
    }

    @GetMapping("/api/produtos/vitrine")
    public List<Produto> mostrarVitrine() {
        return bd.listarVitrine();
    }

    @GetMapping("/api/produtos/busca")
    public List<Produto> buscarProdutos(@RequestParam String termo) {
        return bd.fazerBusca(termo);
    }
 }
