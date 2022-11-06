-- Post schema updates
ALTER TABLE auto_post ADD COLUMN car_id int REFERENCES car(id);

ALTER TABLE auto_user ADD CONSTRAINT login_unique UNIQUE (login);

ALTER TABLE auto_post ADD COLUMN sold BOOLEAN;

ALTER TABLE auto_post ADD COLUMN price INT;

--Car schema updates
ALTER TABLE car ADD COLUMN  body TEXT;

ALTER TABLE car ADD COLUMN color TEXT;

--Engine schema updates
ALTER TABLE engine ADD COLUMN type TEXT;

ALTER TABLE engine ADD COLUMN vol FLOAT;

--User schema updates

ALTER TABLE auto_user ADD COLUMN name TEXT;