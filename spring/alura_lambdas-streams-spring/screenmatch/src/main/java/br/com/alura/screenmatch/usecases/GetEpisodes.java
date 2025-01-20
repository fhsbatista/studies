package br.com.alura.screenmatch.usecases;

import br.com.alura.screenmatch.entities.Episode;
import br.com.alura.screenmatch.model.Season;
import br.com.alura.screenmatch.model.Show;
import br.com.alura.screenmatch.service.ConsumeApi;
import br.com.alura.screenmatch.service.DataMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GetEpisodes {
    private static final String BASEURL = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "apikey=6585022c";

    public static List<Episode> call(String showName) {
        List<Season> seasons = getSeasons(showName);

        List<Episode> episodes = new ArrayList<>();

        seasons.forEach(s -> {
            s.episodes().forEach(e -> {
                try {
                    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    var releaseDate = LocalDate.parse(e.releaseDate(), formatter);
                    var rating = e.rating().equals("N/A") ? 0.0 : Double.parseDouble(e.rating());
                    var episode = new Episode(
                            s.season(),
                            e.number(),
                            e.title(),
                            rating,
                            releaseDate);

                    episodes.add(episode);
                } catch (Exception exception) {
                    System.out.println("Could not parse episode: " + e);
                }

            });
        });

        return episodes;
    }

    private static List<Season> getSeasons(String showName) {
        var showJson = ConsumeApi.get(BASEURL + showName.replace(" ", "+") + "&" + API_KEY);
        var show = DataMapper.map(showJson, Show.class);


        var seasons = new ArrayList<Season>();

        for (int i = 1; i <= show.seasonsNumber(); i++) {
            var seasonJson = ConsumeApi.get(BASEURL + showName.replace(" ", "+") + "&season=" + i + "&" + API_KEY);
            var season = DataMapper.map(seasonJson, Season.class);
            seasons.add(season);
        }

        return seasons;
    }
}
