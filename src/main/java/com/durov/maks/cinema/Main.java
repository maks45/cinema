package com.durov.maks.cinema;

import com.durov.maks.cinema.exceptions.AuthenticationException;
import com.durov.maks.cinema.lib.Injector;
import com.durov.maks.cinema.model.CinemaHall;
import com.durov.maks.cinema.model.Movie;
import com.durov.maks.cinema.model.MovieSession;
import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.security.AuthenticationService;
import com.durov.maks.cinema.service.CinemaHallService;
import com.durov.maks.cinema.service.MovieService;
import com.durov.maks.cinema.service.MovieSessionService;
import com.durov.maks.cinema.service.OrderService;
import com.durov.maks.cinema.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.durov.maks.cinema");

    public static void main(String[] args) throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        movieService.add(movie);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("100 human cinema hall");
        CinemaHallService cinemaHallService =
                (CinemaHallService) INJECTOR.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(LocalDateTime.now());
        MovieSessionService movieSessionService =
                (MovieSessionService) INJECTOR.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        List<Movie> movies = movieService.getAll();
        List<MovieSession> availableSessions = movieSessionService
                .findAvailableSessions(movie.getId(), LocalDateTime.now().toLocalDate());
        List<CinemaHall> cinemaHalls = cinemaHallService.getAll();
        availableSessions.forEach(System.out::println);
        movies.forEach(System.out::println);
        cinemaHalls.forEach(System.out::println);
        AuthenticationService authService =
                (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);
        User user = authService.register("maksim.durov45@gmail.com","maks45", "1111");
        System.out.println(authService.login("maksim.durov45@gmail.com", "1111"));
        ShoppingCartService shoppingCartService =
                (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
        shoppingCartService.registerNewShoppingCart(user);
        System.out.println(shoppingCartService.getByUser(user));
        shoppingCartService.addSession(movieSession, user);
        System.out.println(shoppingCartService.getByUser(user));
        OrderService orderService = (OrderService) INJECTOR.getInstance(OrderService.class);
        orderService.completeOrder(shoppingCartService.getByUser(user).getTickets(),user);
        orderService.getOrderHistory(user).forEach(System.out::println);
    }
}
