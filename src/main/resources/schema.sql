create schema if not exists test;
use test;

CREATE TABLE IF NOT EXISTS url_converter (
id INT AUTO_INCREMENT PRIMARY KEY,
long_url VARCHAR(1000),
short_url VARCHAR(100) DEFAULT NULL,
call_count INT DEFAULT 1
);
