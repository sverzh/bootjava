package ru.javaops.restaurant_voting.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.restaurant_voting.model.Dish;
import ru.javaops.restaurant_voting.repository.DishRepository;
import ru.javaops.restaurant_voting.to.RestaurantTo;

import java.util.List;

import static ru.javaops.restaurant_voting.util.RestaurantsUtil.createMenuToday;

@RestController
@RequestMapping(value = MenuController.URL)
@AllArgsConstructor
@Slf4j
public class MenuController {
    static final String URL = "/api/account/menu";

    private final DishRepository dishRepository;

    @GetMapping
    public List<RestaurantTo> findAllRestaurantWithDishCreatedToday() {
        log.info("findAllRestaurantWithDishCreatedToday");
        List<Dish> dishList = dishRepository.findAllDishCreatedToday();
        return createMenuToday(dishList);
    }
}
