
CREATE TABLE USUARIO(
 secc_usu int  NOT NULL,
 codg_usu varchar(10) NOT NULL,
 pwd_usu varchar(20) NOT NULL,
 nomb_usu varchar(50) NOT NULL,
 apel_usu varchar(50) NOT NULL
) ;

INSERT INTO USUARIO VALUES (1,'ADMIN','ADMIN','Administador','');


CREATE TABLE CLIENTE(
 secc_cli int  NOT NULL,
 codg_cli varchar(10) NOT NULL,
 pwd_cli varchar(20) NOT NULL,
 nomb_cli varchar(50) NOT NULL,
 apel_cli varchar(50) NOT NULL,
 telf_cli varchar(200) NOT NULL
) ;


INSERT INTO CLIENTE VALUES (1,'MLUIS','MLUIS','Miguel Alberto','Luis Cordova','123-4567');
INSERT INTO CLIENTE VALUES (2,'RGALVEZ','RGALVEZ','Ricardo','Galvez','123-4567');
INSERT INTO CLIENTE VALUES (3,'JREYES','JREYES','Joel','Reyes','123-4567');



CREATE TABLE TIPO_HABITACION(
 secc_tiph int  NOT NULL,
 codg_tiph varchar(10) NOT NULL,
 nomb_tiph varchar(50) NOT NULL,
 precio_tiph decimal(7,2) NOT NULL
) ;

INSERT INTO TIPO_HABITACION VALUES (1,'GOB','Gobernador',800);
INSERT INTO TIPO_HABITACION VALUES (2,'GCL','Grand Clase',900);
INSERT INTO TIPO_HABITACION VALUES (3,'PRE','Presidencial',1100);
INSERT INTO TIPO_HABITACION VALUES (4,'SUI','Suite',1300);


CREATE TABLE HABITACION(
 secc_hab int  NOT NULL,
 secc_tiph int NOT NULL,
 codg_hab varchar(10) NOT NULL,
 flg_esta int NOT NULL
) ;
INSERT INTO HABITACION VALUES (1,1,'101',1);
INSERT INTO HABITACION VALUES (2,1,'102',1);
INSERT INTO HABITACION VALUES (3,1,'103',1);


INSERT INTO HABITACION VALUES (6,2,'201',1);
INSERT INTO HABITACION VALUES (7,2,'202',1);

INSERT INTO HABITACION VALUES (11,3,'301',1);
INSERT INTO HABITACION VALUES (12,3,'302',1);
INSERT INTO HABITACION VALUES (13,3,'303',1);

INSERT INTO HABITACION VALUES (14,4,'401',1);
INSERT INTO HABITACION VALUES (15,4,'402',1);


CREATE TABLE RESERVA(
 secc_res int  NOT NULL,
 secc_cli int NOT NULL,
 secc_tiph int NOT NULL,
 secc_hab int ,
 codg_res varchar(10) NOT NULL,
 precio decimal(7,2) NOT NULL,
 fech_ini date NOT NULL,
 fech_fin date NOT NULL,
 flag_estado int NOT NULL
) ;



CREATE TABLE FACTURACION(
 secc_fac int  NOT NULL,
 secc_res int NOT NULL,
 monto_estadia decimal(10,2) NOT NULL,
 monto_consumo decimal(10,2) NOT NULL
) ;


ALTER TABLE CLIENTE ADD CONSTRAINT PK_CLIENTE PRIMARY KEY (SECC_CLI);
ALTER TABLE FACTURACION ADD CONSTRAINT PK_FACTURACION PRIMARY KEY (SECC_FAC);
ALTER TABLE HABITACION ADD CONSTRAINT PK_HABITACION PRIMARY KEY (SECC_HAB);
ALTER TABLE RESERVA ADD CONSTRAINT PK_RESERVA PRIMARY KEY (SECC_RES);
ALTER TABLE TIPO_HABITACION ADD CONSTRAINT PK_TIPO_HABITACION PRIMARY KEY (SECC_TIPH);



ALTER TABLE RESERVA ADD CONSTRAINT FK_RESERVA_HABITACION FOREIGN KEY (SECC_HAB) REFERENCES HABITACION (SECC_HAB);
ALTER TABLE FACTURACION ADD CONSTRAINT FK_FACTURACION_RESERVA FOREIGN KEY (secc_res) REFERENCES RESERVA (secc_res);
ALTER TABLE HABITACION ADD CONSTRAINT FK_HABITACION_TIPO_HABITACION FOREIGN KEY (SECC_TIPH) REFERENCES TIPO_HABITACION (SECC_TIPH);
ALTER TABLE RESERVA ADD CONSTRAINT FK_RESERVA_TIPO_HABITACION FOREIGN KEY (SECC_TIPH) REFERENCES TIPO_HABITACION (SECC_TIPH);
ALTER TABLE RESERVA ADD CONSTRAINT FK_CLIENTE_TIPO_HABITACION FOREIGN KEY (SECC_CLI) REFERENCES CLIENTE (SECC_CLI);

