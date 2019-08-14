CREATE TABLE `active_inventory` (
  `sku` varchar(400) NOT NULL,
  `asin` varchar(400) DEFAULT NULL,
  `name` text,
  `description` text,
  `price` double DEFAULT '0',
  `user-id` varchar(1000) NOT NULL,
  `seller-id` varchar(1000) DEFAULT NULL,
  `marketplace-id` varchar(1000) DEFAULT NULL,
  `current-timestamp` datetime DEFAULT CURRENT_TIMESTAMP,
  `quantity` int(11) DEFAULT '0',
  `fulfillment-channel` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`sku`,`user-id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
