create database `high-school`;
use `high-school`;
create table Person(
	dni int(8) not null,
	firstName char(20) not null,
	lastName char(20) not null,
	dateOfBirth date not null,
	constraint PK_DNI primary key (dni)			
);

create table Student(
	registrationNumber int not null,
	dni int(8) not null,
	constraint PK_REGISTRATION_NUMBER primary key(registrationNumber),
	constraint FK_DNI foreign key (dni) references Person (dni)
);

create table Course(
	courseNumber int not null auto_increment,
	dni int(8) not null,
	name char(20) not null,
	constraint PK_COURSE_NUMBER primary key (courseNumber),
	constraint FK_TEACHER_ASSIGNED foreign key (dni) references Person (dni)
);

create table Schedule(
	scheduleNumber int not null auto_increment,
	courseNumber int not null,
	dayOfTheWeek char(9) not null,
	startingTime time not null,
	endingTime time not null,
	constraint PK_SCHEDULE_NUMBER primary key (scheduleNumber),
	constraint FK_COURSE_NUMBER foreign key (courseNumber) references Course (courseNumber)
);

create table Enrollment(
	registrationNumber int not null,
	courseNumber int not null,
	firstNote decimal(2,2),
	secondNote decimal(2,2),
	thirdNote decimal(2,2),
	finalNote decimal(2,2),
	constraint PK_REGISTRATION_COURSE primary key (registrationNumber, courseNumber),
	constraint FK_REGISTRATION_NUMBER foreign key (registrationNumber) references Student (registrationNumber),
	constraint FK_COURSE_NUMBER2 foreign key (courseNumber) references Course (courseNumber)
);