package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.entities.Episode;
import br.com.alura.screenmatch.model.Season;
import br.com.alura.screenmatch.model.Show;
import br.com.alura.screenmatch.service.ConsumeApi;
import br.com.alura.screenmatch.service.DataMapper;
import br.com.alura.screenmatch.usecases.GetEpisodes;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void showMenu() {
        var scanner = new Scanner(System.in);

        System.out.println("Type the TV Show name");
        var name = scanner.nextLine();

        List<br.com.alura.screenmatch.entities.Episode> episodes = new ArrayList<>();
        episodes = GetEpisodes.call(name);

        episodes.forEach(e -> {
            System.out.println(e.getTitle());
        });

        System.out.println("3 best episodes");
        Episode.filterTopEpisodes(3, episodes).forEach(System.out::println);

        System.out.println("3 worst episodes");
        Episode.filterWorstEpisodes(3, episodes).forEach(System.out::println);
    }
}
