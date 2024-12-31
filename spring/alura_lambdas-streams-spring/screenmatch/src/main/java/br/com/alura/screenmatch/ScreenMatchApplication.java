package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.Season;
import br.com.alura.screenmatch.model.Show;
import br.com.alura.screenmatch.service.ConsumeApi;
import br.com.alura.screenmatch.service.DataMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenMatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var showJson = ConsumeApi.get("https://www.omdbapi.com/?t=the+chosen&apikey=6585022c");
        var show = DataMapper.map(showJson, Show.class);

        System.out.println(show);

        var episodeJson = ConsumeApi.get("https://www.omdbapi.com/?t=the+chosen&season=1&episode=4&apikey=6585022c");
        var episode = DataMapper.map(episodeJson, Episode.class);
        System.out.println(episode);

        var seasons = new ArrayList<Season>();

        for (int i = 1; i <= show.seasonsNumber(); i++) {
            var seasonJson = ConsumeApi.get("https://www.omdbapi.com/?t=the+chosen&season=" + i + "&apikey=6585022c");
            var season = DataMapper.map(seasonJson, Season.class);
            seasons.add(season);
        }

        seasons.forEach(System.out::println);
    }
}
