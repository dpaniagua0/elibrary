INSERT INTO Usuario (correo_electronico, nombre, password, activo)
VALUES ('admin@uvaq.edu.mx', 'Administrador', 'admin', TRUE);

INSERT INTO Rol (rol) VALUES ('administrador');
INSERT INTO Rol (rol) VALUES ('estudiante');

INSERT INTO Usuario_Rol (correo_electronico, rol)
VALUES ('admin@uvaq.edu.mx', 'administrador');

INSERT INTO Autor (id, nombre)
VALUES (1, 'Desconocido');

INSERT INTO Categoria (id, nombre)
VALUES (1, 'Desconocida');

INSERT INTO Editorial (id, nombre)
VALUES (1, 'Desconocida');

INSERT INTO Serie (id, nombre)
VALUES (1, 'Desconocida');

INSERT INTO Libro (id, isbn, titulo, fecha_publicacion, id_serie)
VALUES (1, '123456', 'Libro de prueba', '2010-01-01', 1);

INSERT INTO Libro (isbn, titulo, fecha_publicacion, id_serie)
VALUES ('123456', 'Libro de prueba', '2010-01-01', 1);

INSERT INTO Libro_Autor (id_autor, id_libro)
VALUES (1, 1);

INSERT INTO Libro_Categoria (id_categoria, id_libro)
VALUES (1, 1);

INSERT INTO Libro_Editorial (id_editorial, id_libro)
VALUES (1, 1);
