package com.example.springboot;

import com.example.springboot.controllers.LivroController;
import com.example.springboot.models.LivroModel;
import com.example.springboot.dtos.LivroRecordDto;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        
        
        System.out.println("Digite a opção deseja:");
        System.out.println("1- Cadastrar livro");
        System.out.println("2- Listar livros");
        System.out.println("3- emprestar Livro");
        System.out.println("4- Devolver livro");
        Scanner ler = new Scanner(System.in);
        int opcao = ler.nextInt();
        switch (opcao) {
            case 1:
                new CadastrarLivro();
                break;
        
            default:
                break;
        }

        ler.close();

        
    }
    
    public static class CadastrarLivro {
        String titulo;
        String autor;
        int anoPublicacao;
        int quantidade;

        public CadastrarLivro(){
            LivroController bibliotecaController  = new LivroController();

            LivroModel bibliotecaModel  = new LivroModel();

            Scanner ler = new Scanner(System.in);
            System.out.println("Digite o título do livro:");
            this.titulo = ler.nextLine();
            System.out.println("Digite o autor do livro:");
            this.autor = ler.nextLine();
            System.out.println("Digite o ano de publicação do livro:");
            this.anoPublicacao = ler.nextInt();
            System.out.println("Digite a quantidade de exemplares do livro:");
            this.quantidade = ler.nextInt();
            ler.close();

            
            
            bibliotecaModel.setTitulo(this.titulo);
            bibliotecaModel.setAutor(this.autor);
            bibliotecaModel.setAnoPublicacao(this.anoPublicacao);
            bibliotecaModel.setQuantidade(this.quantidade);
            
            LivroRecordDto bibliotecaRecordDto  = new LivroRecordDto(
                bibliotecaModel.getTitulo(),
                bibliotecaModel.getAutor(),
                bibliotecaModel.getAnoPublicacao(),
                bibliotecaModel.getQuantidade() 
            );

            System.out.println(bibliotecaModel.getTitulo());
            bibliotecaController.cadastrarLivro(bibliotecaRecordDto);
        }
        
    }

}
