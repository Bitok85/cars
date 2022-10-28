CREATE TABLE participates (
      id serial PRIMARY KEY,
      item_id int not null REFERENCES auto_post(id),
      user_id int not null REFERENCES auto_user(id)
);