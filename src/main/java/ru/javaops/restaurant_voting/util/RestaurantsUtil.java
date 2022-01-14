package ru.javaops.restaurant_voting.util;

import ru.javaops.restaurant_voting.model.Dish;
import ru.javaops.restaurant_voting.model.Restaurant;
import ru.javaops.restaurant_voting.to.DishTo;
import ru.javaops.restaurant_voting.to.RestaurantTo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantsUtil {

    public static List<RestaurantTo> createMenuToday(List<Dish> dishList) {
        Set<RestaurantTo> list = new HashSet<>();
        for (Dish dish : dishList) {
            list.add(createRestaurantTo(dish.getRestaurant(), dishList));
        }
        return list.stream().sorted((o1, o2) -> Integer.compare(o2.getRating(), o1.getRating()))
                .collect(Collectors.toList());
    }

    public static RestaurantTo createRestaurantTo(Restaurant restaurant, List<Dish> dishList) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getRating(), createDishListTodayTo(dishList, restaurant));
    }

    public static List<DishTo> createDishListTodayTo(List<Dish> dishList, Restaurant restaurant) {
        return dishList.stream()
                .filter(dish -> dish.getCreatedDate().isEqual(LocalDate.now()) && dish.getRestaurant().getId().equals(restaurant.getId()))
                .map(RestaurantsUtil::createDishTo)
                .collect(Collectors.toList());
    }

    public static DishTo createDishTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }
}
