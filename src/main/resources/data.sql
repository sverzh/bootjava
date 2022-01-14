INSERT INTO USERS (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('user1@gmail.com', 'User1_FirstName', 'User1_LastName', '{noop}pass'),
       ('admin@gmail.com', 'Admin_FirstName', 'Admin_LastName', '{noop}admin'),
       ('user2@gmail.com', 'User2_FirstName', 'User2_LastName', '{noop}pass'),
       ('user3@gmail.com', 'User3_FirstName', 'User3_LastName', '{noop}pass');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4);

INSERT INTO RESTAURANT (NAME, RATING)
VALUES ('McDonald’s', 0),
       ('KFS', 0),
       ('Burger King', 0);

INSERT INTO DISH (NAME, PRICE, CREATED, RESTAURANT_ID)
VALUES ('Dish1', 150, '2021-08-09', 1),
       ('Dish2', 210, '2021-08-09', 1),
       ('Dish3', 300, '2021-08-09', 1),
       ('Dish1', 250, '2021-08-09', 2),
       ('Dish2', 195, '2021-08-09', 2),
       ('Dish3', 350, '2021-08-09', 2),
       ('Dish1', 190, '2021-08-09', 3),
       ('Dish2', 290, '2021-08-09', 3),
       ('Dish3', 390, '2021-08-09', 3),
       ('Today Dish1', 100, now(), 1),
       ('Today Dish2', 200, now(), 1),
       ('Today Dish3', 300, now(), 1),
       ('Today Dish1', 150, now(), 2),
       ('Today Dish2', 250, now(), 2),
       ('Today Dish3', 350, now(), 2),
       ('Today Dish1', 190, now(), 3),
       ('Today Dish2', 290, now(), 3),
       ('Today Dish3', 390, now(), 3);

INSERT INTO VOTE (VOTE_DATE, VOTE_TIME, USER_ID, RESTAURANT_ID)
VALUES ('2022-01-09', '9:40', 1, 1),
       ('2022-01-09', '10:10', 3, 3),
       ('2022-01-09', '10:25', 4, 1),
       ('2022-01-10', '9:10', 1, 2),
       ('2022-01-10', '10:10', 3, 1),
       ('2022-01-10', '10:40', 4, 2);