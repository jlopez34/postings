insert into TBL_POST (title, comments, likes, unlikes) values
  ('qouteoftheday', 'Follow your passion, stay true to yourself, never follow someone else is path unless...', 0, 0),
  ('Omg', 'Stay away from people who make you feel like you are hard to love', 0, 0),
  ('PopSugar', 'I think I can live without you, but it wont be any kind of life', 0, 0);

-- SELECT BY ID
-- SELECT id, title, comments, likes, unlikes FROM TBL_POST WHERE ID = 2;; Select by ID

-- INSERT NEW POST
-- INSERT INTO TBL_POST (title, comments, likes, unlikes) VALUES ('Atlanta', 'I really thinking dont, not real homeless', 0, 0); INSERT

-- SELECT ALL POSTS
-- SELECT id, title, comments, likes, unlikes FROM TBL_POST;

-- UPDATE POST
-- UPDATE TBL_POST
--   SET title = 'Atlanta',
--         comments = 'Look, I got a question, your mind racing',
--WHERE id=4;

-- UPDATE POST LIKE
-- UPDATE TBL_POST
--   SET likes = 3
--WHERE id=3;

-- UPDATE POST UNLIKE
-- UPDATE TBL_POST
--   SET unlikes = 2
--WHERE id=3;

-- DELETE POST
-- DELETE FROM TBL_POST WHERE ID = 4;