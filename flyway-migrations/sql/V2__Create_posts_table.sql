create table POSTS (
    ID serial,
    TITLE varchar NOT NULL,
    FIRST_NAME varchar(100),
    LAST_NAME varchar(100),
    CONTACT_DETAILS varchar,
    POST_BODY varchar NOT NULL,
    DATE_CREATED timestamp NOT NULL DEFAULT NOW(),
    APPROVED boolean NOT NULL DEFAULT false,
    VERIFIED boolean NOT NULL DEFAULT false
);
