use triends;

INSERT INTO plan(title, start_date, end_date, center_lat, center_lng, thumbnail)
VALUES
('서울 여행', '2023-06-01', '2023-06-05', 37.5665, 126.9780, (SELECT CONCAT('https://source.unsplash.com/random/800x600?landscape,', REPLACE(title, ' ', '+')))),
('부산 여행', '2023-07-01', '2023-07-05', 35.1796, 129.0756, (SELECT CONCAT('https://source.unsplash.com/random/800x600?landscape,', REPLACE(title, ' ', '+')))),
('제주도 여행', '2023-08-01', '2023-08-05', 33.4890, 126.4983, (SELECT CONCAT('https://source.unsplash.com/random/800x600?landscape,', REPLACE(title, ' ', '+')))),
('강원도 여행', '2023-09-01', '2023-09-05', 37.8228, 128.1555, (SELECT CONCAT('https://source.unsplash.com/random/800x600?landscape,', REPLACE(title, ' ', '+')))),
('경주 여행', '2023-10-01', '2023-10-05', 35.8532, 129.2249, (SELECT CONCAT('https://source.unsplash.com/random/800x600?landscape,', REPLACE(title, ' ', '+')))),
('전주 여행', '2023-11-01', '2023-11-05', 35.8242, 127.1480, (SELECT CONCAT('https://source.unsplash.com/random/800x600?landscape,', REPLACE(title, ' ', '+')))),
('부여 여행', '2023-12-01', '2023-12-05', 36.2768, 126.9126, (SELECT CONCAT('https://source.unsplash.com/random/800x600?landscape,', REPLACE(title, ' ', '+'))));

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

INSERT INTO course(plan_id, days, start_time, end_time, content_id)
VALUES
(1, 1, 900, 1100, 125405),
(1, 1, 1200, 1400, 125609),
(2, 2, 1000, 1200, 125930),
(2, 2, 1400, 1600, 126015),
(3, 3, 1100, 1300, 125711),
(3, 3, 1500, 1700, 126207),
(4, 4, 900, 1100, 125561),
(4, 4, 1200, 1400, 125706),
(5, 5, 1000, 1200, 126200),
(5, 5, 1400, 1600, 126515);

INSERT INTO course (plan_id, days, start_time, end_time, content_id) VALUES
(3, 2, 9, 11, 135083),
(1, 4, 12, 14, 1748100),
(6, 3, 11, 13, 2734236),
(2, 1, 14, 16, 2710808),
(5, 2, 13, 15, 2855508),
(4, 3, 10, 12, 2732402),
(7, 1, 15, 17, 136358),
(3, 4, 8, 10, 643038),
(1, 3, 11, 13, 2791977),
(6, 2, 10, 12, 1757894),
(2, 4, 13, 15, 522298),
(5, 1, 14, 16, 2732402),
(4, 2, 9, 11, 1021171),
(7, 3, 12, 14, 131824),
(3, 1, 15, 17, 2678726),
(1, 2, 10, 12, 130003),
(6, 4, 13, 15, 1932641),
(2, 3, 8, 10, 2749871),
(5, 4, 11, 13, 134095),
(4, 1, 12, 14, 2832553);

select * from course;

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