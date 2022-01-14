package ru.javaops.restaurant_voting.to;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class RestaurantTo extends BaseTo implements Serializable {

    public RestaurantTo(Integer id, String name, int rating, List<DishTo> dishList) {
        super(id);
        this.name = name;
        this.rating = rating;
        this.dishList = dishList;
    }

    protected String name;
    private int rating;
    private List<DishTo> dishList;

}
