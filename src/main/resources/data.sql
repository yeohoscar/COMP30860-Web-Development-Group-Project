INSERT INTO User (username, passwd, USER_TYPE) VALUES ('Egg', 'Egg', 'Customer');
INSERT INTO Aimodel (ID, AVAILABLE, DESCRIPTION, NAME, TRAINED_PRICE, UNTRAINED_PRICE) VALUES (1,true,'C', 'q', 10.8, 5.2);
INSERT INTO ORDERS (order_id, order_date, state, user_id) VALUES (11, '08-08-08',0,1);
INSERT INTO Order_ordered_models (order_order_id, model_id, price) VALUES (11,1,23.7);
