CREATE TABLE IF NOT EXISTS users
(
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_admin BOOLEAN DEFAULT false
    );

CREATE TABLE IF NOT EXISTS notes
(
    note_id SERIAL PRIMARY KEY,
    user_id INT,
    date DATE NOT NULL,
    header VARCHAR(255) NOT NULL,
    text TEXT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(user_id)
    );
