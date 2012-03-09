INSERT into author (username, hashed_password, email, bio)
VALUES('aaron1', 'aaron1', 'aaron@pobox.com', 'Aaron is "The Dude".');

INSERT into author (username, hashed_password, email)
VALUES('barb2', 'barb2', 'barb@pobox.com');

INSERT into author (username, hashed_password, email, bio)
VALUES('carol3', 'carol3', 'carol@pobox.com', 'Carol is an avid atom-smasher and street luger.');

INSERT into blog (title, author_id)
VALUES('Why I am "The Dude"', (select id from author where username='aaron1'));

INSERT into blog (title, author_id)
VALUES('A Day in the Life of "The Dude"', (select id from author where username='aaron1'));

INSERT into blog (title, author_id)
VALUES('Sanity is my strong suit', (select id from author where username='barb2'));

INSERT into blog (title, author_id)
VALUES('I are smart?', (select id from author where username='carol3'));

INSERT into blog (title, author_id)
VALUES('The Large-Hadron Collider will not create a black hole that ends the universe', (select id from author where username='carol3'));
