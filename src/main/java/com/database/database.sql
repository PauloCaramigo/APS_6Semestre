-- SQLBook: Code
use apsdatabase;

CREATE TABLE users(
    userName VARCHAR(16) not null,
    password VARCHAR(16) ,
    email VARCHAR(100) not null,
    biometry VARCHAR(255) not null,
    permission INT(11) not null,
    state VARCHAR(100) not null
    PRIMARY KEY (userName)
);

CREATE TABLE images(
    id INT(11) not null AUTO_INCREMENT,
    address VARCHAR(100) not null,
    permission INT(11) not null,
    PRIMARY KEY (id)
);

CREATE TABLE agrotoxicos(
    estado VARCHAR(100) not null,
    herbicidas INT(8) not null,
    fungicidas INT(8) not null,
    inseticidas INT(8) not null,
    acaricidas INT(8) not null,
    outros INT(8) not null,
    total INT(8) not null
);

INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("São Paulo", 11716, 5747, 4002, 7960, 1423, 30848);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Paraná", 15010, 2353, 2575, 222, 2330, 22490);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Rio Grande do Sul", 14004, 1602, 1215, 92, 1139, 18052);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Mato Grosso", 10234, 957, 3326, 36, 2173, 16726);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Minas Gerais", 6143, 3599, 3127, 296, 721, 13886);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Goiás", 8414, 1118, 1630, 56, 1175, 12393);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Mato Grosso do Sul", 5665, 299, 1094, 13, 939, 8010);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Santa Catarina", 2978, 970, 354, 48, 399, 4749);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Bahia", 1696, 814, 723, 80, 356, 3669);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Espírito Santo", 1696, 814, 723, 80, 356, 3669);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Pernambuco", 962, 205, 172, 81, 114, 1534);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Maranhão", 683, 70, 101, 0, 66, 920);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Rio de Janeiro", 154, 262, 129, 22, 46, 613);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Pará", 295, 32, 37, 3, 31, 398);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Rio Grande do Norte", 79, 93, 85, 7, 12, 276);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Distrito Federal", 109, 69, 21, 5, 19, 223);
INSERT INTO agrotoxicos (estado, herbicidas, fungicidas, inseticidas, acaricidas, outros, total) VALUES ("Brasil", 81862, 19072, 19447, 8985, 11107, 140473);

INSERT INTO images (address, permission) VALUES ('src/main/java/com/Images/BiometryShiro.jpg', 3);
INSERT INTO images (address, permission) VALUES ('src/main/java/com/Images/BiometryVih.jpg', 3);
INSERT INTO images (address, permission) VALUES ('src/main/java/com/Images/Teste.jpeg', 2);

INSERT INTO users (userName, password, email, biometry, permission, state) VALUES ('Paulo', 'Senha', 'paulo@teste.com', 'src/main/java/com/Images/BiometryShiro.jpg', 3, 'São Paulo');
INSERT INTO users (userName, password, email, biometry, permission, state) VALUES ('Vitor', 'Senha', 'vitor@teste.com', 'src/main/java/com/Images/BiometryVih.jpg', 2, 'São Paulo');
INSERT INTO users (userName, password, email, biometry, permission, state) VALUES ('Vanessa', 'Senha', 'vanessa@teste.com', 'src/main/java/com/Images/Teste.jpeg', 1, 'São Paulo');