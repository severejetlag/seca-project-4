create table USERS (
    ID serial,
    USER_NAME varchar(100) NOT NULL,
    FIRST_NAME varchar(100) NOT NULL,
    LAST_NAME varchar(100) NOT NULL,
    PASSWORD varchar(100) NOT NULL,
    NEIGHBORHOOD varchar(100) NOT NULL,
    BIO varchar NOT NULL
);