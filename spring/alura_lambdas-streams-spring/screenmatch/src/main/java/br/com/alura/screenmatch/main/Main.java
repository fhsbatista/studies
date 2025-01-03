package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.Season;
import br.com.alura.screenmatch.model.Show;
import br.com.alura.screenmatch.service.ConsumeApi;
import br.com.alura.screenmatch.service.DataMapper;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String BASEURL = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "apikey=6585022c";

    public static void showMenu() {
        var scanner = new Scanner(System.in);

        System.out.println("Type the TV Show name");
        var name = scanner.nextLine();

        var showJson = ConsumeApi.get(BASEURL + name.replace(" ", "+") + "&" + API_KEY);
        var show = DataMapper.map(showJson, Show.class);

        System.out.println(show);

        var seasons = new ArrayList<Season>();

        for (int i = 1; i <= show.seasonsNumber(); i++) {
            var seasonJson = ConsumeApi.get(BASEURL + name.replace(" ", "+") + "&season=" + i + "&" + API_KEY);
            var season = DataMapper.map(seasonJson, Season.class);
            seasons.add(season);
        }

        seasons.forEach(System.out::println);

        seasons.forEach(season -> {
            season.episodes().forEach(episode -> {
                System.out.println(episode.title());
            });
        });

        System.out.println("Melhores 3 episódios de todos");
        getTopEpisodes(3, seasons).forEach(System.out::println);

        System.out.println("Piores 3 episódios de todos");
        getWorstEpisodes(3, seasons).forEach(System.out::println);

    }

    private static List<Episode> getTopEpisodes(Integer topNumber, List<Season> seasons) {
        return seasons.stream()
                .flatMap(s -> s.episodes().stream())
                .filter(e -> !e.rating().equals("N/A"))
                .sorted(Comparator.comparing(Episode::rating).reversed())
                .limit(3)
                .toList();
    }

    private static List<Episode> getWorstEpisodes(Integer number, List<Season> seasons) {
        return seasons.stream()
                .flatMap(s -> s.episodes().stream())
                .filter(e -> !e.rating().equals("N/A"))
                .sorted(Comparator.comparing(Episode::rating))
                .limit(3)
                .toList();
    }
}
