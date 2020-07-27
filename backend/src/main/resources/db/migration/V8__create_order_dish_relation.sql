CREATE TABLE order_dish (
  fk_user_order BIGINT,
  fk_dish BIGINT,
  quantity INT NOT NULL,
  PRIMARY KEY (fk_user_order, fk_dish),
  FOREIGN KEY (fk_user_order) REFERENCES user_order(id),
  FOREIGN KEY (fk_dish) REFERENCES dish(id)
);