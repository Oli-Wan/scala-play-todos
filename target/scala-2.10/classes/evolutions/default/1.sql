# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table `Task` (`ID` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`LABEL` VARCHAR(254) NOT NULL,`COMPLETED` BOOLEAN NOT NULL,`OWNER_ID` BIGINT NOT NULL);
create table `User` (`ID` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`EMAIL` VARCHAR(254) NOT NULL);
alter table `Task` add constraint `OWNER` foreign key(`OWNER_ID`) references `User`(`ID`) on update NO ACTION on delete NO ACTION;

# --- !Downs

ALTER TABLE Task DROP FOREIGN KEY OWNER;
drop table `Task`;
drop table `User`;

