CREATE TABLE IF NOT EXISTS users
(
    user_id
    SERIAL
    PRIMARY
    KEY,
    email
    VARCHAR
(
    255
) UNIQUE NOT NULL,
    password VARCHAR
(
    255
) NOT NULL
    );

CREATE TABLE IF NOT EXISTS notes
(
    note_id
    SERIAL
    PRIMARY
    KEY,
    user_id
    INT,
    date
    DATE
    NOT
    NULL,
    header
    VARCHAR
(
    255
) NOT NULL,
    text TEXT NOT NULL,
    CONSTRAINT fk_user
    FOREIGN KEY
(
    user_id
)
    REFERENCES users
(
    user_id
)
    );


INSERT INTO users(email, password)
SELECT 'user1@gmail.com',
       'password1' WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'user1@gmail.com'
);


INSERT INTO users(email, password)
SELECT 'user2@hotmail.com',
       'password2' WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'user2@hotmail.com'
);

INSERT INTO notes(user_id, date, header, text)
SELECT 1,
       '2024-01-12',
       'Meeting',
       'Discuss project progress' WHERE NOT EXISTS (
    SELECT 1 FROM notes WHERE user_id = 1
);

INSERT INTO notes(user_id, date, header, text)
SELECT 1,
       '2024-01-13',
       'Task',
       'Complete coding assignment' WHERE NOT EXISTS (
    SELECT 1 FROM notes WHERE user_id = 1
);

INSERT INTO notes(user_id, date, header, text)
SELECT 2,
       '2024-01-12',
       'Reminder',
       'Buy groceries' WHERE NOT EXISTS (
    SELECT 1 FROM notes WHERE user_id = 2
);