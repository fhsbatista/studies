ALTER TABLE doctors ADD active tinyint;
UPDATE doctors SET active = 1;
ALTER TABLE doctors MODIFY active tinyint NOT NULL;