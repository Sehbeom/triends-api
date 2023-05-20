use triends;

INSERT INTO plan(title, start_date, end_date, center_lat, center_lng, thumbnail)
VALUES
('서울 여행', '2023-06-01', '2023-06-05', 37.5665, 126.9780, (SELECT CONCAT('https://source.unsplash.com/random/800x600?landscape,', REPLACE(title, ' ', '+')))),
('부산 여행', '2023-07-01', '2023-07-05', 35.1796, 129.0756, (SELECT CONCAT('https://source.unsplash.com/random/800x600?landscape,', REPLACE(title, ' ', '+')))),
('제주도 여행', '2023-08-01', '2023-08-05', 33.4890, 126.4983, (SELECT CONCAT('https://source.unsplash.com/random/800x600?landscape,', REPLACE(title, ' ', '+')))),
('강원도 여행', '2023-09-01', '2023-09-05', 37.8228, 128.1555, (SELECT CONCAT('https://source.unsplash.com/random/800x600?landscape,', REPLACE(title, ' ', '+')))),
('경주 여행', '2023-10-01', '2023-10-05', 35.8532, 129.2249, (SELECT CONCAT('https://source.unsplash.com/random/800x600?landscape,', REPLACE(title, ' ', '+'))));

select * from plan;

INSERT INTO users (id, password, name, tel, profileimg, email, admin) VALUES
('john_doe', 'password1', 'John Doe', '555-1234', 'profile1.jpg', 'john_doe@example.com', 0),
('jane_doe', 'password2', 'Jane Doe', '555-5678', 'profile2.jpg', 'jane_doe@example.com', 0),
('admin', 'password3', 'Admin', '555-4321', 'profile3.jpg', 'admin@example.com', 1),
('johndoe123', 'password4', 'John Doe', '555-2468', 'profile4.jpg', 'johndoe123@example.com', 0),
('janedoe456', 'password5', 'Jane Doe', '555-1357', 'profile5.jpg', 'janedoe456@example.com', 0),
('bob', 'password6', 'Bob', '555-9876', 'profile6.jpg', 'bob@example.com', 0),
('alice', 'password7', 'Alice', '555-8765', 'profile7.jpg', 'alice@example.com', 0),
('charlie', 'password8', 'Charlie', '555-5432', 'profile8.jpg', 'charlie@example.com', 0),
('dave', 'password9', 'Dave', '555-3690', 'profile9.jpg', 'dave@example.com', 0),
('emily', 'password10', 'Emily', '555-0123', 'profile10.jpg', 'emily@example.com', 0);

select * from users;

INSERT INTO members(plan_id, user_id) VALUES
(1, 2),
(1, 4),
(1, 5),
(2, 3),
(2, 5),
(2, 7),
(3, 1),
(3, 4),
(4, 2),
(4, 5),
(4, 6),
(5, 1),
(5, 3),
(5, 8),
(6, 2),
(6, 4),
(6, 9),
(7, 3),
(7, 5),
(7, 10);

select * from members;

select * from attraction_info;

-- 1번 plan_id에 대한 day_info 더미 데이터 생성
INSERT INTO days (plan_id, day_info)
SELECT 1, 1 UNION ALL
SELECT 1, 2 UNION ALL
SELECT 1, 3 UNION ALL
SELECT 1, 4 UNION ALL
SELECT 1, 5;

-- 2번 plan_id에 대한 day_info 더미 데이터 생성
INSERT INTO days (plan_id, day_info)
SELECT 2, 1 UNION ALL
SELECT 2, 2 UNION ALL
SELECT 2, 3 UNION ALL
SELECT 2, 4 UNION ALL
SELECT 2, 5;

-- 3번 plan_id에 대한 day_info 더미 데이터 생성
INSERT INTO days (plan_id, day_info)
SELECT 3, 1 UNION ALL
SELECT 3, 2 UNION ALL
SELECT 3, 3 UNION ALL
SELECT 3, 4 UNION ALL
SELECT 3, 5;

-- 4번 plan_id에 대한 day_info 더미 데이터 생성
INSERT INTO days (plan_id, day_info)
SELECT 4, 1 UNION ALL
SELECT 4, 2 UNION ALL
SELECT 4, 3 UNION ALL
SELECT 4, 4 UNION ALL
SELECT 4, 5;

-- 5번 plan_id에 대한 day_info 더미 데이터 생성
INSERT INTO days (plan_id, day_info)
SELECT 5, 1 UNION ALL
SELECT 5, 2 UNION ALL
SELECT 5, 3 UNION ALL
SELECT 5, 4 UNION ALL
SELECT 5, 5;

-- 6번 plan_id에 대한 day_info 더미 데이터 생성
INSERT INTO days (plan_id, day_info)
SELECT 6, 1 UNION ALL
SELECT 6, 2 UNION ALL
SELECT 6, 3 UNION ALL
SELECT 6, 4 UNION ALL
SELECT 6, 5;

-- 7번 plan_id에 대한 day_info 더미 데이터 생성
INSERT INTO days (plan_id, day_info)
SELECT 7, 1 UNION ALL
SELECT 7, 2 UNION ALL
SELECT 7, 3 UNION ALL
SELECT 7, 4 UNION ALL
SELECT 7, 5;

INSERT INTO course (days_id, content_id, start_time, end_time)
VALUES
    (8, (select content_id from attraction_info order by rand() limit 1), 9, 13),
    (8, (select content_id from attraction_info order by rand() limit 1), 14, 17),
    (8, (select content_id from attraction_info order by rand() limit 1), 18, 21),
    (9, (select content_id from attraction_info order by rand() limit 1), 9, 12),
    (9, (select content_id from attraction_info order by rand() limit 1), 12, 15),
    (10, (select content_id from attraction_info order by rand() limit 1), 10, 12),
    (10, (select content_id from attraction_info order by rand() limit 1), 13, 16),
    (10, (select content_id from attraction_info order by rand() limit 1), 16, 19),
    (11, (select content_id from attraction_info order by rand() limit 1), 9, 12),
    (12, (select content_id from attraction_info order by rand() limit 1), 10, 14);
    
INSERT INTO course (days_id, content_id, start_time, end_time)
VALUES
    (15, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 14, 17),
    (15, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 17, 20),
    (15, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 21, 24),
    (16, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 12, 16),
    (16, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 16, 19),
    (17, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 9, 12),
    (17, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 12, 15),
    (18, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 12, 15),
    (18, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 15, 18),
    (18, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 18, 21),
    (19, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 9, 13),
    (19, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 13, 17);

-- days_id 22 이상 26 이하에 대한 더미 데이터 생성
INSERT INTO course (days_id, content_id, start_time, end_time)
VALUES
    (22, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 9, 12),
    (22, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 12, 15),
    (23, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 10, 13),
    (23, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 13, 16),
    (24, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 11, 14),
    (24, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 14, 17),
    (25, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 12, 15),
    (25, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 15, 18),
    (26, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 13, 16);

-- days_id 29 이상 33 이하에 대한 더미 데이터 생성
INSERT INTO course (days_id, content_id, start_time, end_time)
VALUES
    (29, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 9, 12),
    (29, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 12, 15),
    (30, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 10, 13),
    (30, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 13, 16),
    (31, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 11, 14),
    (31, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 14, 17),
    (32, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 12, 15),
    (32, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 15, 18),
    (33, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 13, 16);

-- days_id 36 이상 40 이하에 대한 더미 데이터 생성
INSERT INTO course (days_id, content_id, start_time, end_time)
VALUES
    (36, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 9, 12),
    (36, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 12, 15),
    (37, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 10, 13),
    (37, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 13, 16),
    (38, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 11, 14),
    (38, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 14, 17),
    (39, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 12, 15),
    (39, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 15, 18),
    (40, (SELECT content_id FROM attraction_info ORDER BY RAND() LIMIT 1), 13, 16);

INSERT INTO notice (user_id, subject, content) VALUES
(3, '공지사항 제목1', '공지사항 내용1'),
(3, '공지사항 제목2', '공지사항 내용2'),
(3, '공지사항 제목3', '공지사항 내용3'),
(3, '공지사항 제목4', '공지사항 내용4'),
(3, '공지사항 제목5', '공지사항 내용5'),
(3, '공지사항 제목6', '공지사항 내용6'),
(3, '공지사항 제목7', '공지사항 내용7'),
(3, '공지사항 제목8', '공지사항 내용8'),
(3, '공지사항 제목9', '공지사항 내용9'),
(3, '공지사항 제목10', '공지사항 내용10');

select * from notice;

INSERT INTO userpreference (user_id, content_type_id) VALUES
(1, 12),
(1, 28),
(1, 38),
(2, 14),
(2, 32),
(3, 12),
(3, 15),
(3, 28),
(4, 39),
(5, 12),
(5, 15),
(5, 38),
(5, 39),
(6, 14),
(6, 25),
(7, 14),
(7, 32),
(8, 25),
(8, 32),
(8, 38),
(9, 14),
(9, 25),
(9, 28),
(9, 38),
(10, 12),
(10, 15),
(10, 25),
(10, 28);

INSERT INTO rates (user_id, content_id, score) VALUES
(4, 134095, 3),
(9, 522298, 4),
(2, 2734236, 2),
(10, 2732402, 5),
(6, 135083, 4),
(3, 2734236, 3),
(7, 2678726, 2),
(1, 2732402, 5),
(8, 2710808, 4),
(5, 2855508, 1),
(1, 643038, 3),
(2, 1748100, 4),
(6, 1757894, 5),
(4, 2791977, 2),
(10, 1021171, 3),
(3, 2844102, 4),
(9, 131824, 5),
(5, 130003, 2),
(8, 2734236, 4),
(7, 1748100, 1);

INSERT INTO rates (user_id, content_id, score) VALUES
(6, 125405, 3),
(9, 125609, 4),
(2, 125930, 2),
(4, 126015, 5),
(8, 125711, 4),
(1, 126207, 3),
(5, 125561, 2),
(3, 125706, 5),
(10, 126200, 4),
(7, 126515, 1),
(3, 135083, 3),
(6, 1748100, 4),
(9, 2734236, 2),
(2, 2710808, 5),
(4, 2855508, 1),
(8, 2732402, 4),
(1, 136358, 2),
(5, 643038, 4),
(3, 2791977, 5),
(10, 1757894, 2),
(7, 522298, 3),
(1, 2732402, 4),
(6, 1021171, 3),
(9, 131824, 4),
(2, 2678726, 1),
(4, 130003, 5),
(8, 1932641, 2),
(5, 2749871, 4),
(3, 134095, 3),
(10, 2832553, 5);

INSERT INTO friends (user1, user2)
VALUES (1, 3),
       (1, 4),
       (1, 5),
       (2, 4),
       (2, 5),
       (2, 6),
       (3, 5),
       (3, 6),
       (3, 7),
       (4, 6),
       (4, 7),
       (4, 8),
       (5, 7),
       (5, 8),
       (5, 9),
       (6, 8),
       (6, 9),
       (6, 10),
       (7, 9),
       (7, 10);