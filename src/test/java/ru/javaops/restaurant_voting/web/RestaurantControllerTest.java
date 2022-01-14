package ru.javaops.restaurant_voting.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.restaurant_voting.repository.RestaurantRepository;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.restaurant_voting.UserTestUtil.ADMIN_MAIL;
import static ru.javaops.restaurant_voting.web.RestaurantController.URL;

public class RestaurantControllerTest extends AbstractControllerTest{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
        Assertions.assertTrue(restaurantRepository.findById(1).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
        Assertions.assertTrue(restaurantRepository.findById(1).isPresent());
        Assertions.assertTrue(restaurantRepository.findById(2).isPresent());
        Assertions.assertTrue(restaurantRepository.findById(3).isPresent());
        Assertions.assertFalse(restaurantRepository.findById(4).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL + "/2"))
                .andExpect(status().isNoContent());
        Assertions.assertTrue(restaurantRepository.findById(1).isPresent());
        Assertions.assertFalse(restaurantRepository.findById(2).isPresent());
        Assertions.assertTrue(restaurantRepository.findById(3).isPresent());
    }
}