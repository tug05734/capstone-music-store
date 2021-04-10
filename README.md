# Nu Music

Database entries

For Genre:
```
insert into genre(name) values("Strings");
insert into genre(name) values("Woodwind");
insert into genre(name) values("Brass");
insert into genre(name) values("Percussion");
insert into genre(name) values("Keyboard");
insert into genre(name) values("Rock");
insert into genre(name) values("Hip Hop");
insert into genre(name) values("Jazz");
insert into genre(name) values("Pop");
insert into genre(name) values("Blues");
insert into genre(name) values("Country");
insert into genre(name) values("Heavy Metal");
insert into genre(name) values("Folk");
insert into genre(name) values("Classical");
insert into genre(name) values("R&B");
insert into genre(name) values("EDM");
insert into genre(name) values("Soul");
insert into genre(name) values("Funk");
insert into genre(name) values("Disco");
insert into genre(name) values("Instrumental");
insert into genre(name) values("Techno");
insert into genre(name) values("Gospel");
insert into genre(name) values("Orchestra");
insert into genre(name) values("Dubstep");
insert into genre(name) values("Foreign");
insert into genre(name) values("Opera");
```

For Category:
```
insert into category(name) values("Album");
insert into category(name) values("Song");
insert into category(name) values("Instrument");
insert into category(name) values("Record");
insert into category(name) values("CD");
```

For Product:
```
insert into products(product_condition, img_path, price, product_name, category_id, genre_id) values ("New", "/images/album1.jpg", 10.99, "Album1", 1, 9);
insert into products(product_condition, img_path, price, product_name, category_id, genre_id) values ("New", "/images/album2.jpg", 10.99, "Album2", 1, 16);
insert into products(product_condition, img_path, price, product_name, category_id, genre_id) values ("New", "/images/album3.jpg", 10.99, "Album3", 1, 6);
insert into products(product_condition, img_path, price, product_name, category_id, genre_id) values ("New", "/images/song1.jpg", 1.99, "Song1", 2, 7);
insert into products(product_condition, img_path, price, product_name, category_id, genre_id) values ("Good", "/images/guitar.jpg", 85, "Guitar1", 3, 1);
insert into products(product_condition, img_path, price, product_name, category_id, genre_id) values ("Used", "/images/keyboard.jpg", 30, "Keyboard1", 3, 5);
insert into products(product_condition, img_path, price, product_name, category_id, genre_id) values ("New", "/images/record.jpg", 15, "Record1", 4, 8);
```
