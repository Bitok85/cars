CREATE TABLE IF NOT EXISTS auto_user (
    id SERIAL PRIMARY KEY,
    login VARCHAR,
    password VARCHAR
);

CREATE TABLE  IF NOT EXISTS auto_post (
    id SERIAL PRIMARY KEY,
    text TEXT,
    created TIMESTAMP,
    photo bytea,
    auto_user_id INT REFERENCES auto_user(id)
);
