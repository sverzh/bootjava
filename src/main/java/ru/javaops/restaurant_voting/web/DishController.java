package ru.javaops.restaurant_voting.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.restaurant_voting.model.Dish;
import ru.javaops.restaurant_voting.repository.DishRepository;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;
import ru.javaops.restaurant_voting.util.ValidationUtil;

import javax.validation.Valid;

import java.net.URI;

import static ru.javaops.restaurant_voting.util.ValidationUtil.assureIdConsistent;
import static ru.javaops.restaurant_voting.util.ValidationUtil.checkNotFoundWithId;

@RestController
@Tag(name = "Dish Controller")
@RequestMapping(value = DishController.URL)
@Transactional
@AllArgsConstructor
@Slf4j

public class DishController {
    static final String URL = "/api/restaurant";

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    @PostMapping(value = "/{restaurantId}/dish", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish, @PathVariable int restaurantId) {
        log.info("create {} ", dish);
        ValidationUtil.checkNew(dish);
        dish.setRestaurant(checkNotFoundWithId(restaurantRepository.getOneById(restaurantId), restaurantId));
        Dish created = dishRepository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/dish")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping("/dish/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Dish update(@Valid @RequestBody Dish dish, @PathVariable int id) {
        log.info("update {} ", dish);
        Dish oldDish = checkNotFoundWithId(dishRepository.getOneById(id), id);
        assureIdConsistent(dish, oldDish.id());
        dish.setId(oldDish.getId());
        dish.setRestaurant(oldDish.getRestaurant());
        if (dish.getPrice() == 0) {
            dish.setPrice(oldDish.getPrice());
        }
        return dishRepository.save(dish);
    }

    @DeleteMapping("/dish/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        dishRepository.deleteById(id);
    }

    @GetMapping("/dish/{id}")
    public Dish get(@PathVariable int id) {
        return checkNotFoundWithId(dishRepository.getOneById(id), id);
    }

}