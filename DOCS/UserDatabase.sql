CREATE DATABASE chatroom;
use chatroom
CREATE TABLE `user` (
  `user_id` int(8) NOT NULL AUTO_INCREMENT,
  `user_mail` varchar(45) NOT NULL unique,
  `user_pwd` varchar(45) NOT NULL,
  `user_nickname` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);
INSERT INTO `user`(`user_id` ,`user_mail`,`user_pwd`, `user_nickname`) VALUES (1, 'dispnt@dispnt.com', 'f18011433','Yu');


CREATE TABLE `group`  (
  `group_id` int NOT NULL AUTO_INCREMENT,
  `group_admin_id` int(255) NOT NULL,
  `group_create_time` datetime NOT NULL,
  `group_intro` varchar(255) NULL,
  `group_name` varchar(255) NOT NULL,
  PRIMARY KEY (`group_id`),
  FOREIGN KEY (`group_admin_id` ) REFERENCES user(user_id)
);
INSERT INTO `chatroom`.`group`(`group_id`,`group_admin_id`, `group_create_time`, `group_intro`, `group_name`) VALUES (1,1,  '2020-12-29 21:59:33',`学术讨论群`，`数据库实验`)
INSERT INTO `chatroom`.`group`(`group_admin_id`, `group_create_time`, `group_intro`, `group_name`) VALUES (1, '2020-12-29 22:12:33', '期末交流', 'JavaWeb');


CREATE TABLE `usergroup` (
  `usergroup_id` int NOT NULL AUTO_INCREMENT,
  `usergroup_user_id` int NOT NULL,
  `usergroup_group_id` int NOT NULL,
  PRIMARY KEY (`usergroup_id`),
  FOREIGN KEY (usergroup_user_id) REFERENCES user(user_id),
  foreign key(usergroup_group_id) references `group`(`group_id`)
);
INSERT INTO `usergroup`(`usergroup_id`, `usergroup_user_id`, `usergroup_group_id`) VALUES (1, 1, 1)
INSERT INTO `usergroup`(`usergroup_user_id`, `usergroup_group_id`) VALUES (1, 2)

CREATE TABLE `messages`  (
  `msg_id` int NOT NULL AUTO_INCREMENT,
  `msg_group_id` int NULL,
  `msg_user_id` int NOT NULL,
  `msg_content` varchar(255) NOT NULL,
  `msg_time` datetime NULL,
  PRIMARY KEY (`msg_id`),
  FOREIGN KEY (`msg_group_id`) REFERENCES `group`(`group_id`),
  FOREIGN KEY (`msg_user_id`) REFERENCES `user`(`user_id`)
);
INSERT INTO `messages`(`msg_id`, `msg_group_id`, `msg_user_id`, `msg_content`, `msg_time`) VALUES (1, 1, 1, '1群成员1消息', '2020-12-29 22:21:49')
INSERT INTO `chatroom`.`messages`(`msg_group_id`, `msg_user_id`, `msg_content`, `msg_time`) VALUES (2, 1, '2群成员1消息', '2020-12-29 22:27:18')
INSERT INTO `chatroom`.`messages`(`msg_group_id`, `msg_user_id`, `msg_content`, `msg_time`) VALUES (1, 2, '1群成员2消息', '2020-12-29 22:28:21')