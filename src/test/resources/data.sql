
CREATE TABLE user_entity(
    id  INT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(255),
    email   VARCHAR(255)
);

INSERT INTO user_entity(name, email)
VALUES('user1', 'email1@mail.com'),
('user2', 'email2@mail.com');