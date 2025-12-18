-- ----------------------------
-- Table structure for ums_shop
-- ----------------------------
DROP TABLE IF EXISTS `ums_shop`;
CREATE TABLE `ums_shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `owner_id` bigint(20) NOT NULL COMMENT 'Owner Member ID (ums_member.id)',
  `shop_name` varchar(255) NOT NULL COMMENT 'Shop Name',
  `logo` varchar(500) DEFAULT NULL COMMENT 'Shop Logo URL',
  `description` varchar(1000) DEFAULT NULL,
  `status` int(1) DEFAULT 0 COMMENT '0:Pending, 1:Active, 2:Suspended, 3:Closed',
  `commission_rate` decimal(10,2) DEFAULT '0.00' COMMENT 'Platform commission rate (%)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='Merchant Shop Profile';

-- ----------------------------
-- Add shop_id to pms_product
-- ----------------------------
ALTER TABLE `pms_product` 
ADD COLUMN `shop_id` bigint(20) DEFAULT NULL COMMENT 'Shop ID' AFTER `id`;

CREATE INDEX `idx_product_shop_id` ON `pms_product`(`shop_id`);

-- ----------------------------
-- Add shop_id to oms_order
-- ----------------------------
ALTER TABLE `oms_order` 
ADD COLUMN `shop_id` bigint(20) DEFAULT NULL COMMENT 'Shop ID' AFTER `member_id`;

CREATE INDEX `idx_order_shop_id` ON `oms_order`(`shop_id`);

-- ----------------------------
-- Table structure for ams_wallet (Financial)
-- ----------------------------
DROP TABLE IF EXISTS `ams_admin_wallet`;
CREATE TABLE `ams_shop_wallet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) NOT NULL COMMENT 'Shop ID',
  `balance` decimal(10,2) DEFAULT '0.00' COMMENT 'Current withdrawable balance',
  `frozen_amount` decimal(10,2) DEFAULT '0.00' COMMENT 'Frozen amount (e.g. pending orders)',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Shop Wallet';
