create table userInfo(
   usid number(10) primary key,
   uname varchar2(100) not null unique,
   pwd varchar2(100) not null
);

create sequence seq_userInfo_usid start with 1001;

create or replace trigger tri_userInfo_usid
before insert on userInfo
for each row
begin
   select seq_userInfo_usid.nextval into :new.usid from dual;
end;

drop table topics;
drop table topicItem;

create table topics(
   tid varchar2(40) primary key,
   tname varchar2(200) not null unique, -- 投票主题 
   usid number(10)
        constraint FK_topics_usid references userInfo(usid),
   topicType number(1) default 1, -- 1 表示单选， 2表示多选
   usids varchar2(4000) -- 已投票用户ID
);

create table topicItem(
   tno number(10) primary key,
   tid varchar2(40)
       constraint FK_topicItem_tid references topics(tid),
   iname varchar2(200) not null,
   nums number(10)  -- 票数
);

create sequence seq_topicItem_tno start with 1;

create or replace trigger tri_topicItem_tno
before insert on topicItem
for each row
begin
   select seq_topicItem_tno.nextval into :new.tno from dual;
end;
