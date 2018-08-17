package com.exa.gestarticles;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exa.gestarticles.repository.ArticleRepository;
import com.exa.gestarticles.web.rest.GestArticlesRestAPI;

import io.vertx.core.Vertx;

@SpringBootApplication
public class MainApp {
	
	private final ArticleRepository articleRepository;

    public MainApp(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class);
	}
	
	@PostConstruct
	  public void deployServerVerticle() {
		Vertx.vertx().deployVerticle(new GestArticlesRestAPI(articleRepository));
	  }
	
}
