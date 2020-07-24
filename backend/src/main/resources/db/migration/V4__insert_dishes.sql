-- Bebidas quentes
INSERT INTO DISH (name, price, image_filename, fk_category) VALUES
  ('Cappuccino', 12.90, 'q1.png', 1),
  ('Cappuccino Moca Mix', 12.90, 'q2.png', 1),
  ('Chocolate Quente', 15.90, 'q3.png', 1),
  ('Espresso Curto', 21.90, 'q4.png', 1),
  ('Espresso Duplo', 16.90, 'q5.png', 1),
  ('Macchiato', 22.90, 'q6.png', 1),
  ('Moca Menta', 15.90, 'q7.png', 1),
  ('Submarino', 21.90, 'q8.png', 1),
  ('Vanilla Latte', 15.90, 'q9.png', 1),
  ('Café Latte', 15.90, 'q10.png', 1),
  ('Café Chantilly', 12.90, 'q11.png', 1),
  ('Americano', 12.90, 'q12.png', 1);

-- Bebidas frias
INSERT INTO DISH (name, price, image_filename, fk_category) VALUES
  ('Iced Mix Cookies', 12.90, 'f1.png', 2),
  ('Chocolate Frio', 12.90, 'f2.png', 2),
  ('Iced Mix Café', 12.90, 'f3.png', 2),
  ('Iced Mix Frutas Vermelhas', 12.90, 'f4.png', 2),
  ('Iced Mix Brownie', 12.90, 'f5.png', 2),
  ('Iced Moca Mix', 12.90, 'f6.png', 2);

-- Bolos
INSERT INTO DISH (name, price, image_filename, fk_category) VALUES
  ('Bolo de Bolacha', 12.90, 'b1.png', 3),
  ('Bolo de Chocolate', 12.90, 'b2.png', 3);

-- Pastelaria
INSERT INTO DISH (name, price, image_filename, fk_category) VALUES
  ('Sonho Simples', 12.90, 'p1.png', 4),
  ('Sonho com Topping de Caramelo', 12.90, 'p2.png', 4),
  ('Sonho com Topping de Chocolate', 12.90, 'p3.png', 4),
  ('Cookie com Pepitas de Chocolate', 12.90, 'p4.png', 4),
  ('Muffin Topper Chocolate', 12.90, 'p5.png', 4),
  ('Muffin Topper de Limão com Sementes de Papoula', 12.90, 'p6.png', 4),
  ('Macaron de Caramelo', 12.90, 'p7.png', 4),
  ('Macaron de Chocolate', 12.90, 'p8.png', 4),
  ('Macaron de Framboesa', 12.90, 'p9.png', 4),
  ('Croissant de Chocolate', 12.90, 'p10.png', 4);

-- Salgados
INSERT INTO DISH (name, price, image_filename, fk_category) VALUES
  ('Pão com Fiambre', 12.90, 's1.png', 5),
  ('Pão com Queijo', 12.90, 's2.png', 5),
  ('Pão Misto', 12.90, 's3.png', 5),
  ('Pão com Manteiga', 12.90, 's4.png', 5),
  ('Pão de Cereais com Fiambre', 12.90, 's5.png', 5),
  ('Pão de Cereais com Queijo', 12.90, 's6.png', 5),
  ('Pão de Cereais Misto', 12.90, 's7.png', 5),
  ('Pão de Cereais com Manteiga', 12.90, 's8.png', 5),
  ('Tosta Mista', 12.90, 's9.png', 5),
  ('Croissant', 12.90, 's10.png', 5),
  ('Croissant com Fiambre', 12.90, 's11.png', 5),
  ('Croissant com Queijo', 12.90, 's12.png', 5),
  ('Croissant Misto', 12.90, 's13.png', 5),
  ('Torrada (com manteiga)', 12.90, 's14.png', 5),
  ('Meia Torrada (com manteiga)', 12.90, 's15.png', 5);
