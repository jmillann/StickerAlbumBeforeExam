INSERT INTO Collector (name, surname, email, balance) VALUES ('Josep', 'Roure', 'josep.roure@tecnocampus.cat', 1000);
INSERT INTO Collector (name, surname, email, balance) VALUES ('Antonio', 'Garcia', 'antonio.garcia@tecnocampus.cat',66);
INSERT INTO Collector (name, surname, email, balance) VALUES ('Maria', 'Sabadell', 'maria.sabadell@tecnocampus.cat',2000);
INSERT INTO Collector (name, surname, email, balance) VALUES ('Tina', 'Terassa', 'tina.terrassa@tecnocampus.cat',1000);
INSERT INTO Collector (name, surname, email, balance) VALUES ('Míriam', 'Solsona', 'miriam.solsona@tecnocampus.cat',0);
INSERT INTO Collector (name, surname, email, balance) VALUES ('Paula', 'Mataro', 'paula.mataro@tecnocampus.cat',1000);
INSERT INTO Collector (name, surname, email, balance) VALUES ('Tina', 'Tona', 'tina.tona@tecnocampus.cat',10000);


/*
 Adding three albums with three sections each one and two stickers per section. Stickers also have a description.
 */
INSERT INTO Album (name, editor, begins, ends, owner_id) VALUES ('Album1', 'Editor1', CURRENT_DATE, CURRENT_DATE + INTERVAL '6' MONTH,1);
INSERT INTO Album (name, editor, begins, ends, owner_id) VALUES ('Album2', 'Editor2', CURRENT_DATE, CURRENT_DATE + INTERVAL '6' MONTH,2);
INSERT INTO Album (name, editor, begins, ends, owner_id) VALUES ('Album3', 'Editor1', CURRENT_DATE - INTERVAL '6' MONTH, CURRENT_DATE - INTERVAL '1' DAY,1);

INSERT INTO Section (name, album_id) VALUES ('Section1', 1);
INSERT INTO Section (name, album_id) VALUES ('Section2', 1);
INSERT INTO Section (name, album_id) VALUES ('Section3', 1);

INSERT INTO Section (name, album_id) VALUES ('Section1', 2);
INSERT INTO Section (name, album_id) VALUES ('Section2', 2);
INSERT INTO Section (name, album_id) VALUES ('Section3', 2);

INSERT INTO Section (name, album_id) VALUES ('Section1', 3);
INSERT INTO Section (name, album_id) VALUES ('Section2', 3);
INSERT INTO Section (name, album_id) VALUES ('Section3', 3);

INSERT INTO Sticker (number, name, section_id) VALUES (1, 'Sticker1', 1);
INSERT INTO Sticker (number, name, section_id) VALUES (2, 'Sticker2', 1);

INSERT INTO Sticker (number, name, section_id) VALUES (3, 'Sticker3', 2);
INSERT INTO Sticker (number, name, section_id) VALUES (4, 'Sticker4', 2);

INSERT INTO Sticker (number, name, section_id) VALUES (5, 'Sticker5', 3);
INSERT INTO Sticker (number, name, section_id) VALUES (6, 'Sticker6', 3);

INSERT INTO Sticker (number, name, section_id) VALUES (1, 'Sticker7', 4);
INSERT INTO Sticker (number, name, section_id) VALUES (2, 'Sticker8', 4);

INSERT INTO Sticker (number, name, section_id) VALUES (3, 'Sticker9', 5);
INSERT INTO Sticker (number, name, section_id) VALUES (4, 'Sticker10', 5);

INSERT INTO Sticker (number, name, section_id) VALUES (5, 'Sticker11', 6);
INSERT INTO Sticker (number, name, section_id) VALUES (6, 'Sticker12', 6);


INSERT INTO Sticker (number, name, section_id) VALUES (1, 'Sticker13', 7);
INSERT INTO Sticker (number, name, section_id) VALUES (2, 'Sticker14', 7);

INSERT INTO Sticker (number, name, section_id) VALUES (3, 'Sticker15', 8);
INSERT INTO Sticker (number, name, section_id) VALUES (4, 'Sticker16', 8);

INSERT INTO Sticker (number, name, section_id) VALUES (5, 'Sticker17', 9);
INSERT INTO Sticker (number, name, section_id) VALUES (6, 'Sticker18', 9);

INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (1, 'title', 'Heidi');
INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (1, 'place', 'At home');

INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (2, 'title', 'Peter');
INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (2, 'place', 'At home');

INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (3, 'title', 'Grandfather');
INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (3, 'place', 'At home');

INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (4, 'title', 'Heidi');
INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (4, 'place', 'At mountains');

INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (5, 'title', 'Peter');
INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (5, 'place', 'At mountains');

INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (6, 'title', 'Grandfather');
INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (6, 'place', 'At mountains');

INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (7, 'title', 'Heidi');
INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (7, 'place', 'At home');

INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (8, 'title', 'Peter');
INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (8, 'place', 'At home');

INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (9, 'title', 'Grandfather');
INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (9, 'place', 'At home');

INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (10, 'title', 'Heidi');
INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (10, 'place', 'At mountains');

INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (11, 'title', 'Peter');
INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (11, 'place', 'At mountains');

INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (12, 'title', 'Grandfather');
INSERT INTO sticker_description (sticker_id, description_key, description) VALUES (12, 'place', 'At mountains');

/*
 Collection: collector 1 - album 1 - stickers 1, 2, 4, 5
 */
INSERT INTO collection (begin_date, end_date, album_id, collector_id)
VALUES (CURRENT_DATE - INTERVAL '1' DAY, CURRENT_DATE + INTERVAL '6' MONTH , 1, 1);
INSERT INTO has_sticker (number_of_copies, blocked_copies, album_id, collector_id, sticker_id) VALUES
(1, 0, 1, 1, 1),
(3, 0, 1, 1, 2),
(3, 0, 1, 1, 4),
(3, 0, 1, 1, 5);

/*
 Collection: collector 1 - album 2 - stickers 7, 8, 9
 */
INSERT INTO collection (begin_date, end_date, album_id, collector_id)
VALUES (CURRENT_DATE - INTERVAL '1' DAY, CURRENT_DATE + INTERVAL '6' MONTH , 2, 1);
INSERT INTO has_sticker (number_of_copies, blocked_copies, album_id, collector_id, sticker_id) VALUES
(2, 0, 2, 1, 7),
(3, 0, 2, 1, 8),
(3, 0, 2, 1, 9);

/*
 Collection: collector 2 - album 1 - stickers 2, 6
 */
INSERT INTO collection (begin_date, end_date, album_id, collector_id)
VALUES (CURRENT_DATE - INTERVAL '1' DAY, CURRENT_DATE + INTERVAL '6' MONTH , 1, 2);
INSERT INTO has_sticker (number_of_copies, blocked_copies, album_id, collector_id, sticker_id) VALUES
(2, 0, 1, 2, 2),
(2, 0, 1, 2, 6);

/*
 Collection: collector 3 - album 1 - stickers 3,4,5,6
 */
INSERT INTO collection (begin_date, end_date, album_id, collector_id)
VALUES (CURRENT_DATE - INTERVAL '1' DAY, CURRENT_DATE + INTERVAL '6' MONTH , 1, 3);
INSERT INTO has_sticker (number_of_copies, blocked_copies, album_id, collector_id, sticker_id) VALUES
(2, 0, 1, 3, 3),
(2, 0, 1, 3, 4),
(2, 0, 1, 3, 5),
(2, 0, 1, 3, 6);

/*
 Collection: collector 4 - album 1 - stickers 1, 2, 5
 */
INSERT INTO collection (begin_date, end_date, album_id, collector_id) VALUES
(CURRENT_DATE - INTERVAL '1' DAY, CURRENT_DATE + INTERVAL '6' MONTH , 1, 4);
INSERT INTO has_sticker (number_of_copies, blocked_copies, album_id, collector_id, sticker_id) VALUES
(2, 0, 1, 4, 1),
(2, 0, 1, 4, 2),
(2, 0, 1, 4, 5);

/*
 Collection: collector 5 - album 1 - stickers none
 */
INSERT INTO collection (begin_date, end_date, album_id, collector_id) VALUES
(CURRENT_DATE - INTERVAL '1' DAY, CURRENT_DATE + INTERVAL '6' MONTH , 1, 6);

/*
    Collection: collector 7 - album 1 - stickers 1,
 */
INSERT INTO collection (begin_date, end_date, album_id, collector_id) VALUES
(CURRENT_DATE - INTERVAL '1' DAY, CURRENT_DATE + INTERVAL '6' MONTH , 1, 7);
INSERT INTO has_sticker (number_of_copies, blocked_copies, album_id, collector_id, sticker_id) VALUES
(15, 0, 1, 7, 1);

/*
    Collection: collector 4 - album 3 - stickers 13
 */
INSERT INTO collection (begin_date, end_date, album_id, collector_id) VALUES
(CURRENT_DATE - INTERVAL '1' DAY, CURRENT_DATE - INTERVAL '1' DAY + INTERVAL '6' MONTH , 3, 4);
INSERT INTO has_sticker (number_of_copies, blocked_copies, album_id, collector_id, sticker_id) VALUES
(15, 0, 3, 4, 13);

INSERT INTO blind_auction (begin_date, end_date, owner_id, sticker_id, initial_price)
VALUES (CURRENT_DATE - INTERVAL '7' DAY, CURRENT_DATE - INTERVAL '1' DAY, 1, 1, 10),
       (CURRENT_DATE - INTERVAL '7' DAY, CURRENT_DATE - INTERVAL '1' DAY, 2, 2, 8),
       (CURRENT_DATE - INTERVAL '7' DAY, CURRENT_DATE - INTERVAL '1' DAY, 3, 5, 12),
       (CURRENT_DATE - INTERVAL '7' DAY, CURRENT_DATE + INTERVAL '1' DAY, 2, 2, 9);
UPDATE has_sticker SET blocked_copies = 1 WHERE sticker_id = 1 AND collector_id = 1 AND album_id = 1;
UPDATE has_sticker SET blocked_copies = 2 WHERE sticker_id = 2 AND collector_id = 2 AND album_id = 1;
UPDATE has_sticker SET blocked_copies = 1 WHERE sticker_id = 5 AND collector_id = 3 AND album_id = 1;


INSERT INTO Bid (auction_id, bidder_id, offer, date)
       VALUES (1, 4, 12, CURRENT_DATE - INTERVAL '2' DAY),
              (1, 5, 11, CURRENT_DATE - INTERVAL '2' DAY),
              (1, 6, 12, CURRENT_DATE - INTERVAL '2' DAY),
              (2, 4, 10, CURRENT_DATE - INTERVAL '2' DAY),
              (2, 5, 9, CURRENT_DATE - INTERVAL '2' DAY),
              (2, 6, 8,  CURRENT_DATE - INTERVAL '2' DAY),
              (3, 4, 15, CURRENT_DATE - INTERVAL '2' DAY),
              (3, 5, 14, CURRENT_DATE - INTERVAL '2' DAY),
              (3, 6, 13, CURRENT_DATE - INTERVAL '2' DAY),
              (4, 1, 10, CURRENT_DATE - INTERVAL '4' DAY),
              (4, 2, 12, CURRENT_DATE - INTERVAL '2' DAY),
              (4, 3, 12, CURRENT_DATE - INTERVAL '2' DAY),
              (4, 4, 12, CURRENT_DATE - INTERVAL '1' DAY),
              (4, 5, 11, CURRENT_DATE - INTERVAL '2' DAY);