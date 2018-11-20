drop table tbl_request_token;
drop table tbl_consumer;
drop table tbl_users;

CREATE TABLE tbl_users
(
  	userid  	varchar2(20) NOT NULL,
  	password  	varchar2(20) NOT NULL,
  	username 	varchar2(40),
  	userno		number UNIQUE
);

alter table tbl_users add constraint pk_users  primary key (userid);

CREATE TABLE tbl_consumer
(
	consumerkey  	varchar2(128) NOT NULL,
	consumersecret	varchar2(128) NOT NULL,
	userid		varchar2(20),
	callbackurl	varchar2(500),
	appname		varchar2(100),
	regdate date default sysdate
);

alter table tbl_consumer add constraint pk_consumer  primary key (consumerkey);
alter table tbl_consumer add constraint fk_consumer foreign key (userid) references tbl_users (userid);

CREATE TABLE tbl_request_token
(
   	requesttoken		varchar2(128) NOT NULL,
	requesttokensecret	varchar2(128),
	consumerkey		varchar2(128),
	verifier		varchar2(128),
	callback		varchar(500),
	userno          number,
	regdate date default sysdate
);

alter table tbl_request_token add constraint pk_request_token  primary key (requesttoken);
alter table tbl_request_token add constraint fk_request_token foreign key (consumerkey) references tbl_consumer (consumerkey);


INSERT INTO tbl_users (userid, password, username, userno) values ('user1', '1234', 'Nolan', 1000001);
INSERT INTO tbl_users (userid, password, username, userno) values ('user2', '1234', 'Arnold', 1000002);
INSERT INTO tbl_users (userid, password, username, userno) values ('developer1', '1234', 'Bill', 1000003);
commit;



