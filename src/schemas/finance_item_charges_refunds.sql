CREATE TABLE `finances_item_charges_refunds` (
  `order_id` varchar(200) NOT NULL,
  `seller_order_id` varchar(200) DEFAULT NULL,
  `marketplace_name` varchar(400) DEFAULT NULL,
  `posted_date` datetime DEFAULT NULL,
  `sku` varchar(200) NOT NULL,
  `order_item_id` varchar(200) DEFAULT NULL,
  `quantity_shipped` int(11) DEFAULT '0',
  `charge_type` varchar(200) NOT NULL,
  `charge_amount` float DEFAULT NULL,
  `charge_currency` varchar(45) DEFAULT NULL,
  `user_id` varchar(400) NOT NULL,
  `seller_id` varchar(400) NOT NULL,
  `marketplace_id` varchar(200) NOT NULL,
  `current_timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`,`user_id`,`charge_type`,`sku`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
