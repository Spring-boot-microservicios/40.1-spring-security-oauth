-- Datos propuestos por spring ejemplo
--insert into users (username, password, enabled) VALUES
--    ('admin', 'to_be_encoded', true),
--    ('user', 'to_be_encoded', true);
--
--insert into authorities (username, authority) VALUES
--      ('admin', 'admin'),
--      ('user', 'user');


-- Propia configuracion
--insert into customers (email, pwd, rol) VALUES
--   ('super_user@gmail.com', 'to_be_encoded', 'admin'),
--    ('basic_user@gmail.com', 'to_be_encoded', 'user');


-- ROLES y PRIVILEGIOS
insert into customers (email, pwd) values
   ('account@gmail.com', 'to_be_encoded'),
   ('cards@gmail.com', 'to_be_encoded'),
   ('loans@gmail.com', 'to_be_encoded'),
   ('balance@gmail.com', 'to_be_encoded');

insert into roles(role_name, description, id_customer) values
   ('VIEW_ACCOUNT', 'cant view account endpoint', 1),
   ('VIEW_CARDS', 'cant view cards endpoint', 2),
   ('VIEW_LOANS', 'cant view loans endpoint', 3),
   ('VIEW_BALANCE', 'cant view balance endpoint', 4);