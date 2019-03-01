DROP INDEX IF EXISTS meals_idx;

EXPLAIN ANALYZE SELECT * FROM meals WHERE user_id = 100000 AND date_time BETWEEN '2015-02-10' AND '2015-05-20' ORDER BY date_time DESC;

CREATE INDEX meals_idx ON meals (user_id);

EXPLAIN ANALYZE SELECT * FROM meals WHERE user_id = 100000 AND date_time BETWEEN '2015-02-10' AND '2015-05-20' ORDER BY date_time DESC;

DROP INDEX meals_idx;

CREATE INDEX meals_idx ON meals (user_id, date_time);

EXPLAIN ANALYZE SELECT * FROM meals WHERE user_id = 100000 AND date_time BETWEEN '2015-02-10' AND '2015-05-20' ORDER BY date_time DESC;