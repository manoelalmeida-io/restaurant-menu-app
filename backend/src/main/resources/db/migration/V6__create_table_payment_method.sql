CREATE TABLE payment_method (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50)
);

INSERT INTO payment_method (name) VALUES
  ('BILLET'),
  ('CREDIT_CARD');