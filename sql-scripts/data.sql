INSERT INTO user (username, passwd, USER_TYPE) VALUES ('Egg', 'Egg', 'Customer');
INSERT INTO user (username, passwd, USER_TYPE) VALUES ('Admin', 'Admin', 'Owner');
INSERT INTO aimodel (model_name, trained_price, untrained_price, description, available) VALUES ('Remove Background', '10.9', '5.9', 'twinkle twinkle little star, how i wonder what you are, up above the world so high, like a diamond in the sky. Twinkle twinkle little star, how i wonder what you are.', TRUE);
INSERT INTO aimodel (model_name, trained_price, untrained_price, description, available) VALUES ('Recognition', '28.8', '25.8', 'hey i just met you, and this is crazy, but heres my number, so call me maybe. its hard to look right, at you babyyyyy, but heres my number, so call me maybe', TRUE);
INSERT INTO aimodel (model_name, trained_price, untrained_price, description, available) VALUES ('Landmark', '11.9', '10.9', 'i love you, you love me, we are happy family', FALSE);
INSERT INTO aimodel (model_name, trained_price, untrained_price, description, available) VALUES ('Detection', '35.6', '21.8', 'london bridge is falling down, falling down, falling down', TRUE);
INSERT INTO aimodel (model_name, trained_price, untrained_price, description, available) VALUES ('Pose', '10.9', '5.9', 'twinkle twinkle little star, how i wonder what you are, up above the world so high, like a diamond in the sky. Twinkle twinkle little star, how i wonder what you are.', TRUE);
INSERT INTO aimodel (model_name, trained_price, untrained_price, description, available) VALUES ('Email importance', '28.8', '25.8', 'hey i just met you, and this is crazy, but heres my number, so call me maybe. its hard to look right, at you babyyyyy, but heres my number, so call me maybe', TRUE);
INSERT INTO aimodel (model_name, trained_price, untrained_price, description, available) VALUES ('Content Detection', '10.9', '5.9', 'twinkle twinkle little star, how i wonder what you are, up above the world so high, like a diamond in the sky. Twinkle twinkle little star, how i wonder what you are.', TRUE);
INSERT INTO aimodel (model_name, trained_price, untrained_price, description, available) VALUES ('Text Interrogator', '28.8', '25.8', 'hey i just met you, and this is crazy, but heres my number, so call me maybe. its hard to look right, at you babyyyyy, but heres my number, so call me maybe', TRUE);
INSERT INTO aimodel (model_name, trained_price, untrained_price, description, available) VALUES ('Document image classifier', '10.9', '5.9', 'twinkle twinkle little star, how i wonder what you are, up above the world so high, like a diamond in the sky. Twinkle twinkle little star, how i wonder what you are.', TRUE);
INSERT INTO aimodel (model_name, trained_price, untrained_price, description, available) VALUES ('Article Recommendation', '28.8', '25.8', 'hey i just met you, and this is crazy, but heres my number, so call me maybe. its hard to look right, at you babyyyyy, but heres my number, so call me maybe', TRUE);
INSERT INTO orders (id, order_date, state, customer_id, payment_id) VALUES (11, '08-08-08', 0, 1, '1#20201011143822');
INSERT INTO order_ordered_models (order_id, model_id, price) VALUES (11,1,23.7);