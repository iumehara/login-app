INSERT INTO users (name, password, role_id)
VALUES ("adam", "$2a$10$ulVshdckaFlW7/.ICzleOevtm/06CuPbvwxLtfNr0S7KIclCNt6oO"/* secreta */, 1),
("bob", "$2a$10$F87rzu.DDwDkucVqkN572.Dyk/10NEzNTFIWTU.3/k3.5Z9Uae8Um"/* secretb */, 1),
("cam", "$2a$10$5cmCVU990LJxUn5XiJSmHeXY8J726MGkxSbDuBgyMi1aAbhvNZ/i2"/* secretc */, 2),
("dan", "$2a$10$aGpTrby1FFie.yHVsvPcTe/ipHSI2RKLdRHrohFWmRJs2Tnz5whvW"/* secretd */, 2);

INSERT INTO roles (name)
VALUES ("staff"),
("admin");

INSERT INTO sessions (user_id, token)
VALUES (1, "token");
