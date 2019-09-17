create table medic
(CNP_medic char(14),
nume char(30),
varsta int(2),
specialitate char(50),
constraint medici_CNP_medic_pk primary key (CNP_medic));

create table pacient
(CNP_pacient char(14),
nume char(30),
varsta int(2),
CNP_medic char(14),
constraint pacienti_CNP_pacient_pk primary key (CNP_pacient));


create table FisaPacientului
(data_fisa date,
grupa_sange char (4),
rh char(15),
CNP_pacient char(14),
id_fisa int(3),
CONSTRAINT FisaPAcientului_id_fisa_pk primary key (id_fisa));

create table alergii
(data_alergie date,
CNP_pacient char(14),
grad_alergie char(30),
id_alergie int(7),
tip char(20),
nume char(30),
constraint  alergii_id_alergie_pk primary key (id_alergie));

create table user_passwords
(username char(40),
password_user char(40),
id_user int(3),
constraint user_id_user_pk primary key (id_user));



insert into medic values
(2990713170025,'Gelu',45,'ortopedie');

insert into medic values
(2990713170045,'Carmen',36,'stomatologie');

insert into medic values
(2990723170025,'Popescu',51,'cardiologie');



insert into pacient values
(2990713170024,'Ionescu',24, 2990713170025);

insert into pacient values
(2990813170021,'Doru',75, 2990723170025);

insert into pacient values
(2990813170022,'Aghel',46, 2990713170045);

insert into pacient values
(2990813170023,'Dorel',77, 2990723170025);



insert into fisapacientului values
(str_to_date('12/04/2015', '%d/%m/%Y'), 'A2','pozitiv', 2990713170024, 100);

insert into fisapacientului values
(str_to_date('13/04/2015','%d/%m/%Y'), 'B3','pozitiv', 2990813170021, 101);

insert into alergii values
(str_to_date('15/04/2010','%d/%m/%Y'), 2990813170023,'slab', 200,'respiratorie', 'rinita alergica');

insert into user_passwords values
('admin', 'ADMIN',1);

insert into user_passwords values
('gelu', '2990713170025',2);

insert into user_passwords values
('carmen', '2990713170045',3);

insert into user_passwords values
('Popescu', '2990723170025',4);
commit ;