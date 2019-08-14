CREATE TABLE `my_inventory` (
  `sku` varchar(400) NOT NULL,
  `asin` varchar(400) DEFAULT NULL,
  `name` text,
  `cost` double NOT NULL,
  `user-id` varchar(1000) NOT NULL,
  `seller-id` varchar(1000) DEFAULT NULL,
  `marketplace-id` varchar(1000) DEFAULT NULL,
  `current-timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sku`,`user-id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
