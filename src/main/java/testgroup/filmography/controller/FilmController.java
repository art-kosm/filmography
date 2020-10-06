package testgroup.filmography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import testgroup.filmography.model.Film;
import testgroup.filmography.service.FilmService;

import java.util.List;

@Controller
public class FilmController {

    // "Можно использовать встроенные методы для CRUD-операций; добавить
    // фильтрацию, поиск, сортировку; добавить другие связанные таблицы;
    // реализовать поддержку разных пользователей с авторизацией и
    // аутентификацией и т.д. и т.п."

    private FilmService filmService;
    private final int pageLength = 7;
    private int currentPage;

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
/*
        Film film = new Film();
        film.setTitle("Inception");
        film.setYear(2010);
        film.setGenre("sci-fi");
        film.setWatched(true);
        filmService.add(film);
        film = new Film();
        film.setTitle("The LOTR");
        film.setYear(2001);
        film.setGenre("fantasy");
        film.setWatched(false);
        filmService.add(film);
        film = new Film();
        film.setTitle("Tag");
        film.setYear(2018);
        film.setGenre("comedy");
        film.setWatched(false);
        filmService.add(film);
 */
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allFilms(@RequestParam(defaultValue = "1") int page) {
        List<Film> films = filmService.allFilms(page, pageLength);
        currentPage = page;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("filmsList", films);
        modelAndView.addObject("page", page);

        int filmsCount = filmService.filmsCount();
        modelAndView.addObject("filmsCount", filmsCount);

        int pagesCount = filmsCount > 0 ? (filmsCount + pageLength - 1) / pageLength : 1;
        modelAndView.addObject("pagesCount", pagesCount);

        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id)
    {
        Film film = filmService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("film", film);

        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + currentPage);
        filmService.edit(film);

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + currentPage);
        filmService.add(film);

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFilm(@PathVariable("id") int id) {
        Film film = filmService.getById(id);
        filmService.delete(film);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + currentPage);

        return modelAndView;
    }
}
