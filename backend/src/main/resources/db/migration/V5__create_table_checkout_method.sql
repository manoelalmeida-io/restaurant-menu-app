CREATE TABLE checkout_method (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50)
);

INSERT INTO checkout_method (name) VALUES
  ('SHOP'),
  ('DELIVERY');