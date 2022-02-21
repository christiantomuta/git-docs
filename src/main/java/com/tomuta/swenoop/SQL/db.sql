CREATE TABLE users (
                       user_ID char(36) PRIMARY KEY,
                       username char(50) NOT NULL UNIQUE,
                       password char (64) NOT NULL,
                       coins integer NOT NULL,
                       user_description varchar(120),
                       ELO integer NOT NULL
);

CREATE TABLE cards (
                       card_ID char(36) PRIMARY KEY,
                       card_description varchar(120),
                       card_kind integer NOT NULL,
                       card_element integer NOT NULL,
                       damage integer NOT NULL,
                       user_ID char(36) NOT NULL,
                       inDeck boolean NOT NULL,
                       CONSTRAINT card_to_user FOREIGN KEY(user_ID) REFERENCES users(user_ID),
                       CHECK (damage >= 0),
                       CHECK (card_kind >= 0),
                       CHECK (card_element >= 0)
);