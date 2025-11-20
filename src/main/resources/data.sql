INSERT INTO `book` (`id`, `author_name`, `category`, `isbn`, `publication_date`, `title`) VALUES
(1000, 'Casper MAGNUS', 'NARRATIVE', 1235, '2024-12-10', 'Book 1'),
(1001, 'Casper DUPONT', 'EPISTOLARY', 32414, '2024-12-11', 'Book 2'),
(1002, 'Albert CAMUS', 'ARGUMENTATIVE', 1245, '2024-12-12', 'Book 3'),
(1003, 'Dominique JEAN', 'NARRATIVE', 1147, '2024-12-13', 'Book 4'),
(1004, 'Jean PATRY', 'POETRY', 1148, '2024-12-14', 'Book 5');

/*
 * password: azerty
*/
INSERT INTO `member` (`id`, `address`, `email`, `name`, `password`, `phone_number`, `registration_date`) VALUES
(2000, 'Avenue de Chambery', 'thierry@yahoo.fr', 'Thierry FRANCOIS', '$2a$10$KyLiUpvb7q3fOHzSS0UAquzSdgRmPYOg6g/okNSYQcNrEPo4xG6CK', '0785214781', NULL),
(2001, 'Avenue du Rhone', 'trevor.clevenot@yahoo.fr', 'Trevor CLEVENOT', '$2a$10$KyLiUpvb7q3fOHzSS0UAquzSdgRmPYOg6g/okNSYQcNrEPo4xG6CK', '0685214781', NULL),
(2002, 'Avenue du Pole', 'robert@yahoo.fr', 'Robert MARCHAND', '$2a$10$KyLiUpvb7q3fOHzSS0UAquzSdgRmPYOg6g/okNSYQcNrEPo4xG6CK', '0885214781', NULL),
(2003, 'Avenue du Chome', 'stephane@yahoo.fr', 'Stephane ROGER', '$2a$10$KyLiUpvb7q3fOHzSS0UAquzSdgRmPYOg6g/okNSYQcNrEPo4xG6CK', '0985214781', NULL);

INSERT INTO `role` (`id`, `role` ) VALUES (300, 'ADMIN'), (301, 'USER');

INSERT INTO `member_role` (`user_id`, `role_id` ) VALUES (2000, 300), (2001, 300),
(2002, 301), (2003, 301);
