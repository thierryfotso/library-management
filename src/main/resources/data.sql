INSERT INTO `book` (`id`, `author_name`, `category`, `isbn`, `publication_date`, `title`) VALUES
(1, NULL, 'NARRATIVE', 1235, '2024-12-10', 'Book 1'),
(2, 'Casper DUPONT', 'EPISTOLARY', 32414, '2024-12-11', 'Book 2'),
(3, 'Albert CAMUS', 'ARGUMENTATIVE', 1245, '2024-12-12', 'Book 3'),
(4, 'Dominique JEAN', 'NARRATIVE', 1147, '2024-12-13', 'Book 4'),
(5, 'Jean PATRY', 'POETRY', 1148, '2024-12-14', 'Book 5');


INSERT INTO `member` (`id`, `address`, `email`, `name`, `password`, `phone_number`, `registration_date`) VALUES
(1, 'Avenue de Chambery', 'thierry@yahoo.fr', 'Thierry FRANCOIS', 'myPassword', '0785214781', NULL),
(2, 'Avenue du Rhone', 'trevor.clevenot@yahoo.fr', 'Trevor CLEVENOT', 'myPassword', '0685214781', NULL);
