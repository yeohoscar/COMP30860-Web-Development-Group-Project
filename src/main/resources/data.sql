INSERT INTO User (username, passwd, USER_TYPE) VALUES ('Egg', 'Egg', 'Customer');
INSERT INTO AIModel (model_name, trained_price, untrained_price, description, photoURL, available) VALUES ('Remove Background', '10.9', '5.9', 'twinkle twinkle little star, how i wonder what you are, up above the world so high, like a diamond in the sky. Twinkle twinkle little star, how i wonder what you are.', '/image/RemoveBG.jpg', 'true');
INSERT INTO AIModel (model_name, trained_price, untrained_price, description, photoURL, available) VALUES ('Recognition', '28.8', '25.8', 'hey i just met you, and this is crazy, but heres my number, so call me maybe. its hard to look right, at you babyyyyy, but heres my number, so call me maybe', '/image/recognition.jpeg', 'true');
INSERT INTO ORDERS (id, order_date, state, customer_id) VALUES (11, '08-08-08',0,1);
INSERT INTO Order_ordered_models (order_id, model_id, price) VALUES (11,1,23.7);
