DROP TABLE IF EXISTS section_test;
DROP TABLE IF EXISTS result;
DROP TABLE IF EXISTS answer;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS section;
DROP TABLE IF EXISTS test;
DROP TABLE IF EXISTS test_type;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id        UUID PRIMARY KEY,
    login     VARCHAR(20) NOT NULL UNIQUE,
    password  VARCHAR(20) NOT NULL,
    firstName VARCHAR(30) NOT NULL,
    lastName  VARCHAR(30) NOT NULL,
    avatar    BYTEA       NULL,
    CONSTRAINT users_login_key UNIQUE (login),
    CONSTRAINT users_login_min_length_check CHECK (LENGTH(login) >= 5)
);

CREATE TABLE section
(
    id   UUID PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT section_name_key UNIQUE (name)
);

CREATE TABLE test_type
(
    id                   UUID PRIMARY KEY,
    name                 VARCHAR(40)  NOT NULL,
    description          VARCHAR(130) NOT NULL,
    correct_answer_count INT          NOT NULL,
    CONSTRAINT test_type_name_key UNIQUE (name)
);

CREATE TABLE test
(
    id             UUID PRIMARY KEY,
    type_id        UUID        NOT NULL,
    section_id     UUID        NOT NULL,
    title          VARCHAR(40) NOT NULL,
    question_count INT         NULL,
    CONSTRAINT test_title_key UNIQUE (title),
    CONSTRAINT test_type_id_fk FOREIGN KEY (type_id) REFERENCES test_type (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT test_section_id_fk FOREIGN KEY (section_id) REFERENCES section (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE question
(
    id            UUID PRIMARY KEY,
    test_id       UUID NOT NULL,
    question_text VARCHAR(100) NOT NULL,
    CONSTRAINT question_test_id_fk FOREIGN KEY (test_id) REFERENCES test (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE answer
(
    id          UUID PRIMARY KEY,
    question_id UUID        NOT NULL,
    answer_text VARCHAR(50) NOT NULL,
    correctness BOOLEAN,
    CONSTRAINT answer_question_id_fk FOREIGN KEY (question_id) REFERENCES question (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE result
(
    id           UUID PRIMARY KEY,
    user_id      UUID NOT NULL,
    test_id      UUID NOT NULL,
    grade        INT  NOT NULL,
    date_of_test TEXT NOT NULL,
    CONSTRAINT result_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT result_test_id_fk FOREIGN KEY (test_id) REFERENCES test (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);