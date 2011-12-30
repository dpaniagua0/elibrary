INSERT INTO Usuario (correo_electronico, name, password, active)
VALUES ('admin@uvaq.edu.mx', 'Administrador', 'admin', TRUE);

INSERT INTO Rol (rol) VALUES ('administrador');
INSERT INTO Rol (rol) VALUES ('estudiante');

INSERT INTO Usuario_Rol (correo_electronico, rol)
VALUES ('admin@uvaq.edu.mx', 'administrador');

INSERT INTO Autor (id, name)
VALUES (1, 'Desconocido');

INSERT INTO Categoria (id, name)
VALUES (1, 'Desconocida');

INSERT INTO Editorial (id, name)
VALUES (1, 'Desconocida');

INSERT INTO Serie (id, name)
VALUES (1, 'Desconocida');

INSERT INTO Libro (id, isbn, title, fecha_publicacion, id_serie)
VALUES (1, '123456', 'Libro de prueba', '2010-01-01', 1);

INSERT INTO Libro (isbn, title, fecha_publicacion, id_serie)
VALUES ('123456', 'Libro de prueba', '2010-01-01', 1);

INSERT INTO Libro_Autor (id_autor, id_libro)
VALUES (1, 1);

INSERT INTO Libro_Categoria (id_categoria, id_libro)
VALUES (1, 1);

INSERT INTO Libro_Editorial (id_editorial, id_libro)
VALUES (1, 1);
