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