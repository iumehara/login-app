INSERT INTO users (name, password, role_id)
VALUES ("adam", "secreta", 1),
("bob", "secretb", 1),
("cam", "secretc", 2),
("dan", "secretd", 2);

INSERT INTO roles (name)
VALUES ("staff"),
("admin");

INSERT INTO sessions (user_id, token)
VALUES (1, "token");
