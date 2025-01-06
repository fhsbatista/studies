package br.com.alura.screenmatch.entities;

import br.com.alura.screenmatch.model.Season;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Episode {
    private Integer season;
    private Integer number;
    private String title;
    private Double rating;
    private LocalDate released;

    public Episode(Integer season,
                   Integer number,
                   String title,
                   Double rating,
                   LocalDate released) {
        this.season = season;
        this.number = number;
        this.title = title;
        this.rating = rating;
        this.released = released;
    }

    public static List<Episode> filterTopEpisodes(Integer number, List<Episode> episodes) {
        return episodes.stream()
                .filter(e -> !e.rating.equals(0.0))
                .sorted(Comparator.comparing(Episode::getRating).reversed())
                .limit(number)
                .toList();
    }

    public static List<Episode> filterWorstEpisodes(Integer number, List<Episode> episodes) {
        return episodes.stream()
                .filter(e -> !e.rating.equals(0.0))
                .sorted(Comparator.comparing(Episode::getRating))
                .limit(number)
                .toList();
    }

    public String getTitle() {
        return title;
    }

    public Double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "S" + season + "E" + number + " - " + title + " | Rating: " + rating + " | Release Date: " + released;
    }
}

