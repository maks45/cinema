package com.durov.maks.cinema.controller;

import com.durov.maks.cinema.model.MovieSession;
import com.durov.maks.cinema.model.ShoppingCart;
import com.durov.maks.cinema.model.Ticket;
import com.durov.maks.cinema.model.dto.moviesession.MovieSessionResponseDto;
import com.durov.maks.cinema.model.dto.shoppingcart.ShoppingCartResponseDto;
import com.durov.maks.cinema.model.dto.ticket.TicketResponseDto;
import com.durov.maks.cinema.service.CinemaHallService;
import com.durov.maks.cinema.service.MovieService;
import com.durov.maks.cinema.service.ShoppingCartService;
import com.durov.maks.cinema.service.UserService;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    private final CinemaHallService cinemaHallService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieService movieService;

    public ShoppingCartController(CinemaHallService cinemaHallService,
                                  ShoppingCartService shoppingCartService,
                                  UserService userService, MovieService movieService) {
        this.cinemaHallService = cinemaHallService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieService = movieService;
    }

    @GetMapping("/byuser")
    public ShoppingCartResponseDto getByUser(@RequestParam Long userId) {
        return getShoppingCartResponseDto(shoppingCartService
                .getByUser(userService.getUserById(userId)));
    }

    @PostMapping("/addmoviesession")
    public void addMovieSession(@RequestBody MovieSessionResponseDto movieSessionResponseDto,
                                @RequestParam Long userId) {
        shoppingCartService.addSession(getMovieSession(movieSessionResponseDto),
                userService.getUserById(userId));
    }

    private ShoppingCartResponseDto getShoppingCartResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto.setTicketResponseDtos(
                shoppingCart.getTickets().stream().map(this::getTicketResponseDto)
                        .collect(Collectors.toList()));
        shoppingCartResponseDto.setUserLogin(shoppingCart.getUser().getLogin());
        shoppingCartResponseDto.setUserId(shoppingCart.getUser().getId());
        return shoppingCartResponseDto;
    }

    private TicketResponseDto getTicketResponseDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setId(ticket.getId());
        ticketResponseDto.setCinemaHallId(ticket.getMovieSession().getCinemaHall().getId());
        ticketResponseDto.setMovieId(ticket.getMovieSession().getMovie().getId());
        ticketResponseDto.setMovieTitle(ticket.getMovieSession().getMovie().getTitle());
        ticketResponseDto.setDate(ticket.getMovieSession().getShowTime());
        return ticketResponseDto;
    }

    private MovieSession getMovieSession(MovieSessionResponseDto movieSessionResponseDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setId(movieSessionResponseDto.getMovieSessionId());
        movieSession.setCinemaHall(cinemaHallService
                .getCinemaHallById(movieSessionResponseDto.getCinemaHallId()));
        movieSession.setShowTime(movieSessionResponseDto.getDate());
        movieSession.setMovie(movieService.getMovieById(movieSessionResponseDto.getMovieId()));
        return movieSession;
    }
}
