package ru.javaops.restaurant_voting.util;

import lombok.experimental.UtilityClass;
import ru.javaops.restaurant_voting.error.IllegalRequestDataException;
import ru.javaops.restaurant_voting.model.BaseEntity;
import ru.javaops.restaurant_voting.model.Vote;

import java.time.LocalTime;

@UtilityClass
public class ValidationUtil {

    static final LocalTime voteDeadline = LocalTime.of(11, 0);

    public static void checkNew(BaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalRequestDataException(entity.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new IllegalRequestDataException("Not found entity with " + msg);
        }
    }

    //  Conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
    public static void assureIdConsistent(BaseEntity entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.id() != id) {
            throw new IllegalRequestDataException(entity.getClass().getSimpleName() + " must has id=" + id);
        }
    }

    public static void checkVoteDeadline(Vote vote) {
        if (vote.getVoteTime().isAfter(voteDeadline)) {
            throw new IllegalRequestDataException("The time for voting has expired, you could vote until " + voteDeadline);
        }
    }
}