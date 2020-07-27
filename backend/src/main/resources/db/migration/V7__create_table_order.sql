CREATE TABLE user_order (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  fk_checkout_method INT,
  fk_payment_method INT,
  FOREIGN KEY (fk_checkout_method) REFERENCES checkout_method(id),
  FOREIGN KEY (fk_payment_method) REFERENCES payment_method(id)
);
