CREATE TABLE `finances_item_fees_refunds` (
  `order_id` varchar(200) NOT NULL,
  `seller_order_id` varchar(200) DEFAULT NULL,
  `marketplace_name` varchar(400) DEFAULT NULL,
  `posted_date` datetime DEFAULT NULL,
  `sku` varchar(200) DEFAULT NULL,
  `order_item_id` varchar(200) NOT NULL,
  `quantity_shipped` int(11) DEFAULT '0',
  `fee_type` varchar(200) NOT NULL,
  `fee_amount` float DEFAULT NULL,
  `fee_currency` varchar(45) DEFAULT NULL,
  `user_id` varchar(400) NOT NULL,
  `seller_id` varchar(400) NOT NULL,
  `marketplace_id` varchar(200) NOT NULL,
  `current_timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`,`user_id`,`order_item_id`,`fee_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
