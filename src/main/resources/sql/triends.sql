-- Triends DB
-- create database triends;

use triends;

select * from attraction_info;

create table users(
    user_id int auto_increment primary key,
    id varchar(30) not null,
    password varchar(30) not null,
    name varchar(30) default 'user',
    tel varchar(30),
    profileimg varchar(255),
    email varchar(100) not null,
    admin tinyint default 0
);

create table friends(
    friends_id int auto_increment primary key,
    user1 int,
    user2 int
);

alter table friends add constraint users_friends_fk_1 foreign key (user1) references users(user_id) on delete cascade on update cascade;
alter table friends add constraint users_friends_fk_2 foreign key (user2) references users(user_id) on delete cascade on update cascade;

create table rates(
    rates_id int auto_increment primary key,
    user_id int,
    content_id int,
    score int
);

alter table rates add constraint users_rates_fk foreign key (user_id) references users(user_id) on delete cascade on update cascade;
alter table rates add constraint attraction_rates_fk foreign key (content_id) references attraction_info(content_id) on delete cascade on update cascade;

create table plan(
    plan_id int auto_increment primary key,
    title varchar(100),
    start_date date,
    end_date date,
    center_lat decimal(13, 10),
    center_lng decimal(13, 10),
    thumbnail varchar(255)
);

create table review(
    review_id int auto_increment primary key,
    title varchar(100),
    content mediumtext,
    user_id int,
    plan_id int,
    created_at timestamp default now(),
    modified_at timestamp default now(),
    likes int default 0,
    scrapped int default 0
);

alter table review add constraint users_review_fk foreign key (user_id) references users(user_id) on delete cascade on update cascade;
alter table review add constraint plan_review_fk foreign key (plan_id) references plan(plan_id) on delete cascade on update cascade;

create table category
(
    category_id int auto_increment primary key,
    name varchar(100)
);

create table attraction_category
(
    attraction_category_id int auto_increment primary key,
    content_id int,
    category_id int
);

alter table attraction_category add constraint attraction_attraction_category_fk foreign key (content_id) references attraction_info(content_id) on delete cascade on update cascade;
alter table attraction_category add constraint category_attraction_category_fk foreign key (category_id) references category(category_id) on delete cascade on update cascade;

create table course(
    course_id int auto_increment primary key,
    plan_id int,
    days int,
    start_time int,
    end_time int,
    content_id int
);

alter table course add constraint plan_course_fk foreign key (plan_id) references plan(plan_id) on delete cascade on update cascade;
alter table course add constraint attraction_info_course_fk foreign key (content_id) references attraction_info(content_id) on delete cascade on update cascade;

create table members(
    members_id int auto_increment primary key,
    plan_id int,
    user_id int
);

alter table members add constraint plan_members_fk foreign key (plan_id) references plan(plan_id) on delete cascade on update cascade;
alter table members add constraint users_members_fk foreign key (user_id) references users(user_id) on delete cascade on update cascade;

create table memo(
    memo_id int auto_increment primary key,
    user_id int,
    plan_id int,
    content_id int,
    content mediumtext
);

alter table memo add constraint plan_memo_fk foreign key (plan_id) references plan(plan_id) on delete cascade on update cascade;
alter table memo add constraint users_memo_fk foreign key (user_id) references users(user_id) on delete cascade on update cascade;
alter table memo add constraint attraction_info_memo_fk foreign key (content_id) references attraction_info(content_id) on delete cascade on update cascade;

create table comment
(
    comment_id int auto_increment primary key,
    review_id int,
    user_id int,
    name varchar(30),
    content mediumtext,
    created_at timestamp default now()
);

alter table comment add constraint review_comments_fk foreign key (review_id) references review(review_id) on delete cascade on update cascade;
alter table comment add constraint users_comment_fk foreign key (user_id) references users(user_id) on delete cascade on update cascade;

create table notify
(
    notify_id int auto_increment primary key,
    user_id int,
    sender int,
    notify_type varchar(45),
    isread tinyint default 0,
    additional_info varchar(255)
);

alter table notify add constraint users_notify_fk foreign key (user_id) references users(user_id) on delete cascade on update cascade;
alter table notify add constraint users_notify_sender_fk foreign key (sender) references users(user_id) on delete cascade on update cascade;

create table notice
(
    notice_id int auto_increment primary key,
    user_id int,
    subject varchar(100),
    content mediumtext,
    created_at timestamp default now()
);

alter table notice add constraint users_notice_fk foreign key (user_id) references users(user_id) on delete cascade on update cascade;

create table userpreference
(
	userpreference_id int auto_increment primary key,
    user_id int,
    category_id int
);

alter table userpreference add constraint users_userpreference_fk foreign key (user_id) references users(user_id) on delete cascade on update cascade;
alter table userpreference add constraint category_userpreference_fk foreign key (category_id) references category(category_id) on delete cascade on update cascade;

