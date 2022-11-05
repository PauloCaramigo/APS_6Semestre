use apsdatabase;

CREATE TABLE users(
    userName VARCHAR(16) not null,
    password VARCHAR(16) ,
    email VARCHAR(100) not null,
    biometry VARCHAR(255) not null,
    PRIMARY KEY (userName)
);

CREATE TABLE images(
    id INT(11) not null AUTO_INCREMENT,
    address VARCHAR(255) not null,
    permission INT(11) not null,
    PRIMARY KEY (id)
);


insert into images (address,permission) value ('APS_AGORA-VAI\\APS\\src\\Images\\BiometryShiro.jpg',3);
insert into images (address,permission) value ('APS_AGORA-VAI\\APS\\src\\Images\\BiometryVih.jpg',3);
insert into images (address,permission) value ('APS_AGORA-VAI\\APS\\src\\Images\\Teste.jpeg',2);