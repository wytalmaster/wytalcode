create database if not exists wytal_accounts;

CREATE USER 'wytal'@'localhost' IDENTIFIED BY 'success';
CREATE USER 'wytal'@'%' IDENTIFIED BY 'success';


GRANT ALL PRIVILEGES ON  wytal_accounts  TO 'wytal'@'localhost';


GRANT INSERT,UPDATE,DELETE,SELECT ON wytal_accounts.*  TO 'wytal'@'%' ;
