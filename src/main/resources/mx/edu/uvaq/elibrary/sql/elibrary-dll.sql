DROP ALL OBJECTS;

CREATE TABLE Rol (
                rol VARCHAR(20) NOT NULL,
                CONSTRAINT Rol_pk PRIMARY KEY (rol)
);


CREATE TABLE Serie (
                id identity NOT NULL,
                nombre VARCHAR(50) NOT NULL,
                CONSTRAINT Serie_pk PRIMARY KEY (id)
);


CREATE TABLE Editorial (
                id identity NOT NULL,
                nombre VARCHAR(50) NOT NULL,
                CONSTRAINT Editorial_pk PRIMARY KEY (id)
);


CREATE TABLE Autor (
                id identity NOT NULL,
                nombre VARCHAR(50) NOT NULL,
                apellidos VARCHAR(100),
                CONSTRAINT Autor_pk PRIMARY KEY (id)
);


CREATE TABLE Categoria (
                id identity NOT NULL,
                nombre VARCHAR(50) NOT NULL,
                id_categoria_padre INTEGER,
                CONSTRAINT Categoria_pk PRIMARY KEY (id)
);


CREATE TABLE Libro (
                id identity NOT NULL,
                titulo VARCHAR(100) NOT NULL,
                fecha_publicacion DATE,
                isbn VARCHAR(20),
                id_serie INTEGER,
                archivo BLOB,
                imagen BLOB,
                CONSTRAINT Libro_pk PRIMARY KEY (id)
);

CREATE TABLE Libro_Categoria (
                id_categoria INTEGER NOT NULL,
                id_libro INTEGER NOT NULL,
                CONSTRAINT Libro_Categoria_pk PRIMARY KEY (id_categoria, id_libro)
);


CREATE TABLE Libro_Editorial (
                id_editorial INTEGER NOT NULL,
                id_libro INTEGER NOT NULL,
                CONSTRAINT Libro_Editorial_pk PRIMARY KEY (id_editorial, id_libro)
);

CREATE TABLE Libro_Autor (
                id_autor INTEGER NOT NULL,
                id_libro INTEGER NOT NULL,
                CONSTRAINT Libro_Autor_pk PRIMARY KEY (id_autor, id_libro)
);


CREATE TABLE Usuario (
                correo_electronico VARCHAR(50) NOT NULL,
                nombre VARCHAR(50) NOT NULL,
                apellidos VARCHAR(100) DEFAULT '' NOT NULL,
                password VARCHAR(50) NOT NULL,
                activo BOOLEAN DEFAULT FALSE,
                codigo_activacion VARCHAR(100),
                CONSTRAINT Usuario_pk PRIMARY KEY (correo_electronico)
);


CREATE TABLE Usuario_Rol (
                correo_electronico VARCHAR(50) NOT NULL,
                rol VARCHAR(20) NOT NULL,
                CONSTRAINT Usuario_Rol_pk PRIMARY KEY (correo_electronico, rol)
);


CREATE VIEW Usuarios_Activos AS SELECT correo_electronico, password FROM Usuario WHERE activo = TRUE;

ALTER TABLE Libro ADD CONSTRAINT Serie_Libro_fk
FOREIGN KEY (id_serie)
REFERENCES Serie (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Categoria ADD CONSTRAINT Categoria_Categoria_fk
FOREIGN KEY (id_categoria_padre)
REFERENCES Categoria (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Libro_Autor ADD CONSTRAINT Libro_Libro_Autor_fk
FOREIGN KEY (id_libro)
REFERENCES Libro (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Libro_Autor ADD CONSTRAINT Autor_Libro_Autor_fk
FOREIGN KEY (id_autor)
REFERENCES Autor (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Libro_Editorial ADD CONSTRAINT Editorial_Libro_Editorial_fk
FOREIGN KEY (id_editorial)
REFERENCES Editorial (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Libro_Editorial ADD CONSTRAINT Libro_Libro_Editorial_fk
FOREIGN KEY (id_libro)
REFERENCES Libro (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Libro_Categoria ADD CONSTRAINT Categoria_Libro_Categoria_fk
FOREIGN KEY (id_categoria)
REFERENCES Categoria (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Libro_Categoria ADD CONSTRAINT Libro_Libro_Categoria_fk
FOREIGN KEY (id_libro)
REFERENCES Libro (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Usuario_Rol ADD CONSTRAINT Rol_Usuario_Rol_fk
FOREIGN KEY (rol)
REFERENCES Rol (rol)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Usuario_Rol ADD CONSTRAINT Usuario_Usuario_Rol_fk
FOREIGN KEY (correo_electronico)
REFERENCES Usuario (correo_electronico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
