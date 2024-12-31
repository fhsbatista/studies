package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.Show;
import br.com.alura.screenmatch.service.ConsumeApi;
import br.com.alura.screenmatch.service.ShowDataMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenMatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var json = ConsumeApi.get("https://www.omdbapi.com/?t=the+chosen&apikey=6585022c");
        System.out.println(json);

        var mapper = new ShowDataMapper();
        var show = mapper.map(json, Show.class);
        System.out.println(show);
    }


}
