package ru.javaops.restaurant_voting;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.javaops.restaurant_voting.model.Role;
import ru.javaops.restaurant_voting.model.User;
import ru.javaops.restaurant_voting.util.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestUtil {
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    public static final String USER_MAIL = "user1@gmail.com";
    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final User user = new User(USER_ID, USER_MAIL, "User1_FirstName", "User1_LastName", "pass", List.of(Role.USER));
    public static final User admin = new User(ADMIN_ID, ADMIN_MAIL, "Admin_FirstName", "Admin_LastName", "admin", List.of(Role.ADMIN, Role.USER));

    public static User getNew() {
        return new User(null, "new_mail@gmail.com", "New_FirstName", "New_LastName", "newpass", List.of(Role.USER));
    }

    public static User getUpdated() {
        return new User(USER_ID, "user_update@gmail.com", "FirstName_Updated", "FirstName_Updated", "password_update", List.of(Role.USER));
    }

    public static void assertEquals(User actual, User expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("password").isEqualTo(expected);
    }

    // No id in HATEOAS answer
    public static void assertNoIdEquals(User actual, User expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("id", "password").isEqualTo(expected);
    }

    public static User asUser(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValue(jsonActual, User.class);
    }

    public static ResultMatcher jsonMatcher(User expected, BiConsumer<User, User> equalsAssertion) {
        return mvcResult -> equalsAssertion.accept(asUser(mvcResult), expected);
    }
}
