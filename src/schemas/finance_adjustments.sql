CREATE TABLE `finances_adjustments` (
  `adjustment_type` varchar(2000) NOT NULL,
  `posted_date` datetime NOT NULL,
  `adjustment_amount` float DEFAULT NULL,
  `adjustment_currency` varchar(45) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `per_unit_amount` float DEFAULT NULL,
  `per_unit_amount_currency` varchar(45) DEFAULT NULL,
  `total_amount` float DEFAULT NULL,
  `total_amount_currency` varchar(45) DEFAULT NULL,
  `sku` varchar(200) NOT NULL,
  `product_description` text,
  `user_id` varchar(400) NOT NULL,
  `seller_id` varchar(400) NOT NULL,
  `marketplace_id` varchar(200) NOT NULL,
  `current_timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`adjustment_type`,`posted_date`,`sku`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
