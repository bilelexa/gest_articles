package com.exa.gestarticles.web.rest;

import java.util.Optional;

import com.exa.gestarticles.domain.Article;
import com.exa.gestarticles.repository.ArticleRepository;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.ResponseContentTypeHandler;
import java.util.List;

public class GestArticlesRestAPI extends AbstractVerticle{
	
	private final ArticleRepository articleRepository;

    public GestArticlesRestAPI(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
	
	@Override
    public void start() throws Exception {
		
		String defaultHeaders = "Origin, X-Requested-With, Content-Type, Accept"; 
		String defaultMethods = "GET, POST, OPTIONS, PUT, HEAD, DELETE, CONNECT"; 
		String defaultIpAndPorts = "*"; 
		
		Router router = Router.router(vertx);
		
		router.route().handler(rc -> {         
		rc.response().putHeader("Access-Control-Allow-Headers", defaultHeaders); 
		rc.response().putHeader("Access-Control-Allow-Methods", defaultMethods); 
		rc.response().putHeader("Access-Control-Allow-Origin", defaultIpAndPorts); 
		rc.response().putHeader("Access-Control-Allow-Credentials", "true"); 
		rc.next(); 
	     }); 
		
		router.route("/articles/*").handler(ResponseContentTypeHandler.create());
		router.route(HttpMethod.POST, "/articles").handler(BodyHandler.create());
		router.route(HttpMethod.PUT, "/articles").handler(BodyHandler.create());
		router.route(HttpMethod.OPTIONS, "/articles/*").handler(rc -> { 
			rc.response().setStatusCode(200);
		    rc.response().end();
		});
		
		router.get("/articles/:id").produces("application/json").handler(rc -> { 
			Optional<Article> art = articleRepository.findById(Long.parseLong(rc.request().getParam("id")));
			rc.response().setStatusCode(200);
			rc.response().end(Json.encodePrettily(art.get()));
		});
		
		router.get("/articles").produces("application/json").handler(rc -> { 
			List<Article> arts = articleRepository.findAll();
		    rc.response().end(Json.encodePrettily(arts));
		});
		
		router.post("/articles").produces("application/json").handler(rc -> { 
			Article a = Json.decodeValue(rc.getBodyAsString(), Article.class);
			articleRepository.save(a);
			rc.response().setStatusCode(200);
		    rc.response().end();
		});
		
		router.put("/articles").produces("application/json").handler(rc -> { 
			Article a = Json.decodeValue(rc.getBodyAsString(), Article.class);
			articleRepository.save(a);
			rc.response().setStatusCode(200);
		    rc.response().end();
		});
		
		router.delete("/articles/:id").handler(rc -> {
			articleRepository.deleteById(Long.parseLong(rc.request().getParam("id")));
		    rc.response().setStatusCode(200);
		    rc.response().end();
		});
		
		vertx.createHttpServer().requestHandler(router::accept).listen(3100, "0.0.0.0");
		System.out.println("HTTP server started on port 3100");
	}

}
