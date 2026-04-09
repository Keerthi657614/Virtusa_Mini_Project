CREATE DATABASE virtusa;
USE virtusa;
CREATE TABLE Products(
product_id INT PRIMARY KEY  AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
category VARCHAR(100) NOT NULL,
price DECIMAL(10,2) NOT NULL check (price>=0)
);

CREATE TABLE Customers(
customer_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
city VARCHAR(30) NOT NULL
);

CREATE TABLE Orders(
order_id INT PRIMARY KEY  AUTO_INCREMENT,
customer_id INT NOT NULL ,
date DATE DEFAULT (CURRENT_DATE),
FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE Order_items(
order_id INT,
product_id INT,
quantity INT NOT NULL CHECK (quantity > 0),
PRIMARY KEY(order_id, product_id),
FOREIGN KEY(order_id) REFERENCES Orders(order_id) ON DELETE CASCADE,
FOREIGN KEY(product_id) REFERENCES Products(product_id)
);

SELECT * from Customers;
SELECT * from Products;
SELECT * from Orders;
SELECT * FROM Order_items;

#Find top-selling products
SELECT p.product_id,p.name,SUM(oi.quantity) AS total_quantity
FROM Products p INNER JOIN Order_items oi 
ON oi.product_id = p.product_id
GROUP BY p.product_id
ORDER BY total_quantity DESC;

#Identify most valuable customers
SELECT c.customer_id,c.name,SUM(oi.quantity*p.price) AS total_spent
FROM Customers  c JOIN Orders o ON c.customer_id=o.customer_id
JOIN Order_items oi ON o.order_id=oi.order_id
JOIN Products p ON oi.product_id=p.product_id
GROUP BY c.customer_id,c.name
ORDER BY total_spent DESC;

#Monthly revenue calculation
SELECT YEAR(o.date) AS order_year,MONTH(o.date) AS order_month,SUM(oi.quantity*p.price) AS revenue
FROM Orders o JOIN Order_items  oi ON o.order_id=oi.order_id
JOIN Products p ON oi.product_id=p.product_id
GROUP BY order_year,order_month
ORDER BY order_year,order_month;

#Category-wise sales analysis
SELECT p.category,SUM(oi.quantity) AS quantity,SUM(oi.quantity*p.price) AS total_sales
FROM Order_items oi JOIN Products p ON oi.product_id=p.product_id
GROUP BY p.category
ORDER BY total_sales DESC;

SELECT c.customer_id,c.name 
FROM Customers c LEFT JOIN Orders o ON c.customer_id=o.customer_id 
WHERE o.order_id IS NULL;

#Detect inactive customers
SELECT c.customer_id,c.name 
FROM Customers c JOIN Orders o ON c.customer_id=o.customer_id 
GROUP BY c.customer_id,c.name
HAVING MAX(date) < (CURRENT_DATE - INTERVAL 6 MONTH);
