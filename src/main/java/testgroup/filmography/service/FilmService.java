package testgroup.filmography.service;

import testgroup.filmography.model.Film;

import java.util.List;

public interface FilmService {
    List<Film> allFilms(int page, int pageLength);

    void add(Film film);
    void delete(Film film);
    void edit(Film film);

    Film getById(int id);
    int filmsCount();
}
