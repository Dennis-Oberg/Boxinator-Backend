# boxnox

Har skapat egna klasser för att connecta mysql. För att connecta så behöver man ändra filepathen i SQLConnection.java till sin lokala path till dbcredentials.properties som ligger i paketet DBConn. och då även ändra username och password till sitt egna i dbcredentials.properties

Projektet var kul. Det var första gången jag byggde i spring även fast jag vet om MVC sedan tidigare. Är små saker jag har funderat över under utförandet, exempelvis, vart är praxis/"rätt" att lägga businesslogiken? Har kört igenom api-et med tester i Insomnia, vet inte om det är "rätt väg" att göra heller.

DB

CREATE TABLE box ( id int NOT NULL AUTO_INCREMENT, container_colour varchar(255) NOT NULL, receiver varchar(255) NOT NULL, box_name varchar(255) NOT NULL, shipping_cost double NOT NULL, weight_in_kilo_grams double NOT NULL, PRIMARY KEY (id) ) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
