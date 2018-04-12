create table POSTS (
    ID serial,
    TITLE varchar NOT NULL,
    FIRST_NAME varchar(100),
    LAST_NAME varchar(100),
    POST_BODY varchar NOT NULL,
    DATE_CREATED timestamptz NOT NULL DEFAULT now(),
    APPROVED boolean NOT NULL DEFAULT false
);