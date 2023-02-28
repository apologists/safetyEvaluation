/*
Navicat MySQL Data Transfer

Source Server         : AI
Source Server Version : 50646
Source Host           : 47.103.149.168:3306
Source Database       : cc

Target Server Type    : MYSQL
Target Server Version : 50646
File Encoding         : 65001

Date: 2020-06-20 14:35:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `clazz_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `clazz` varchar(50) NOT NULL DEFAULT '' COMMENT '班级名称',
  `college_id` int(10) NOT NULL DEFAULT '0' COMMENT '院id',
  `college` varchar(50) NOT NULL DEFAULT '' COMMENT '院',
  `specialty_id` int(10) NOT NULL DEFAULT '0' COMMENT '专业id',
  `specialty` varchar(50) NOT NULL DEFAULT '' COMMENT '专业',
  `number` int(10) NOT NULL DEFAULT '0' COMMENT '人数',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0：未删除 1：已删除',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`clazz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='班级';

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('1', '2016-03', '8', '信息工程学院', '2', '电子商务', '37', '0', '2020-01-18 10:53:55', '2020-03-11 11:50:18');
INSERT INTO `clazz` VALUES ('2', '2015-03', '8', '信息工程学院', '2', '电子商务', '37', '0', '2020-01-18 10:53:55', '2020-03-11 11:50:19');
INSERT INTO `clazz` VALUES ('3', '2014-03', '8', '信息工程学院', '2', '电子商务', '37', '0', '2020-03-18 10:53:55', '2020-03-31 21:41:59');
INSERT INTO `clazz` VALUES ('4', '2015-03', '8', '信息工程学院', '1', '软件工程', '0', '0', '2020-01-18 10:55:57', '2020-02-11 11:50:19');
INSERT INTO `clazz` VALUES ('5', '2015-02', '8', '信息工程学院', '1', '软件工程', '0', '0', '2020-01-18 10:55:57', '2020-02-11 11:50:20');

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `college_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `college_name` varchar(50) NOT NULL COMMENT '院',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0：未删除 1：已删除',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`college_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='学院';

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('1', '管理学院', '0', '2020-01-18 10:26:55', '2020-01-18 10:26:55');
INSERT INTO `college` VALUES ('2', '经济学院', '0', '2020-01-18 10:26:55', '2020-01-18 10:26:55');
INSERT INTO `college` VALUES ('3', '机械工程学院', '0', '2020-01-18 10:26:55', '2020-01-18 10:26:55');
INSERT INTO `college` VALUES ('4', '生物技术与食品科学学院', '0', '2020-01-18 10:26:55', '2020-01-18 10:26:55');
INSERT INTO `college` VALUES ('5', '法学院', '0', '2020-01-18 10:26:55', '2020-01-18 10:26:55');
INSERT INTO `college` VALUES ('6', '公共管理学院', '0', '2020-01-18 10:26:55', '2020-01-18 10:26:55');
INSERT INTO `college` VALUES ('7', '马克思主义学院', '1', '2020-02-11 20:54:04', '2020-02-11 20:54:12');
INSERT INTO `college` VALUES ('8', '信息工程学院', '0', '2020-01-18 10:26:55', '2020-01-18 10:26:55');
INSERT INTO `college` VALUES ('9', '国际教育合作学院', '0', '2020-01-18 10:26:55', '2020-01-18 10:26:55');
INSERT INTO `college` VALUES ('10', '艺术学院', '0', '2020-01-18 10:26:56', '2020-01-18 10:26:56');
INSERT INTO `college` VALUES ('11', '外国语学院', '0', '2020-01-18 10:26:56', '2020-01-18 10:26:56');
INSERT INTO `college` VALUES ('12', '理学院', '0', '2020-01-18 10:26:56', '2020-01-18 10:26:56');
INSERT INTO `college` VALUES ('13', '会计学院', '0', '2020-01-18 10:26:56', '2020-01-18 10:26:56');
INSERT INTO `college` VALUES ('14', '大学外语教学部', '1', '2020-01-18 10:26:56', '2020-02-11 21:05:18');
INSERT INTO `college` VALUES ('15', '体育教学部', '1', '2020-01-18 10:26:56', '2020-02-11 21:05:41');

-- ----------------------------
-- Table structure for computer
-- ----------------------------
DROP TABLE IF EXISTS `computer`;
CREATE TABLE `computer` (
  `computer_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `computer_no` int(11) NOT NULL DEFAULT '0' COMMENT '计算机编号，计算机的身份证(3011001:前三位是机房号，中间两位是行号，最后两位是列号)',
  `room_no` varchar(50) NOT NULL DEFAULT '' COMMENT '教室编号，哪间教室',
  `is_error` tinyint(4) NOT NULL DEFAULT '0' COMMENT '标记故障（0=无故障，1=有故障）',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0：未删除 1：已删除',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`computer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='计算机表';

-- ----------------------------
-- Records of computer
-- ----------------------------
INSERT INTO `computer` VALUES ('1', '3011001', '信息交流中心301', '0', '0', '2020-01-15 18:36:03', '2020-04-19 15:04:19');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `feedback_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '反馈标题',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '类型：0=个人建议，1=计算机异常，2=系统问题',
  `context` varchar(200) NOT NULL COMMENT '反馈内容',
  `user_id` int(10) NOT NULL DEFAULT '0' COMMENT '用户id',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户',
  `user_no` varchar(50) NOT NULL DEFAULT '' COMMENT '用户学号/工号',
  `is_deal_with` tinyint(1) NOT NULL DEFAULT '0' COMMENT '管理员是否已处理（0=未处理，1=已处理）',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0：未删除 1：已删除',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='反馈表';

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('1', '登录问题', '1', '到了机房账号登录不上，导致无法按时赴约，希望管理员老师查看，本人并非故意爽约，按理不该扣除信用积分', '15', '王晓红', '20164789', '0', '0', '2020-02-24 19:02:05', '2020-05-24 18:14:50');
INSERT INTO `feedback` VALUES ('2', '计算机卡顿', '1', '怎么回事系统一直登录不上去', '14', '张晓茗', '20164799', '1', '0', '2020-02-24 19:02:05', '2020-05-24 18:09:22');
INSERT INTO `feedback` VALUES ('3', '希望通知及时', '0', '到了机房账号登录不上，导致无法按时赴约，希望管理员老师查看，本人并非故意爽约，按理不该扣除信用积分', '15', '王晓红', '20164789', '0', '0', '2020-02-24 19:02:05', '2020-05-24 18:14:49');
INSERT INTO `feedback` VALUES ('4', '预约不上', '2', '什么都不想干就想反馈一下', '15', '王晓红', '20164789', '1', '0', '2020-02-24 19:02:05', '2020-05-24 18:14:50');
INSERT INTO `feedback` VALUES ('5', '登录问题', '1', '键盘有点脏，怎么回事', '14', '张晓茗', '20164799', '0', '0', '2020-02-24 19:02:05', '2020-05-24 18:11:45');
INSERT INTO `feedback` VALUES ('6', '登录问题', '1', '来晚了不好意思，希望管理员老师可以不扣我分', '14', '张晓茗', '20164799', '0', '0', '2020-02-24 19:02:05', '2020-05-24 18:11:44');
INSERT INTO `feedback` VALUES ('7', '登录问题', '1', '没有反馈了，凑凑数据量', '15', '王晓红', '20164789', '0', '0', '2020-02-24 19:02:05', '2020-05-24 18:14:50');
INSERT INTO `feedback` VALUES ('8', '希望通知及时', '0', '到了机房账号登录不上，导致无法按时赴约，希望管理员老师查看，本人并非故意爽约，按理不该扣除信用积分', '14', '张晓茗', '20164799', '0', '0', '2020-02-24 19:02:05', '2020-05-24 18:08:56');
INSERT INTO `feedback` VALUES ('9', '预约不上', '2', '怎么回事系统一直登录不上去', '15', '王晓红', '20164789', '1', '0', '2020-02-24 19:02:05', '2020-05-24 18:14:49');
INSERT INTO `feedback` VALUES ('10', '登录问题', '1', '到了机房账号登录不上，导致无法按时赴约，希望管理员老师查看，本人并非故意爽约，按理不该扣除信用积分', '14', '张晓茗', '20164799', '0', '0', '2020-02-24 19:02:05', '2020-05-24 18:08:56');
INSERT INTO `feedback` VALUES ('11', '登录问题', '1', '什么都不想干就想反馈一下', '14', '张晓茗', '20164799', '0', '0', '2020-02-24 19:02:05', '2020-05-24 18:11:45');
INSERT INTO `feedback` VALUES ('12', '登录问题', '1', '键盘有点脏，怎么回事', '15', '王晓红', '20164789', '0', '0', '2020-02-24 19:02:05', '2020-05-24 18:14:49');
INSERT INTO `feedback` VALUES ('13', '希望通知及时', '0', '来晚了不好意思，希望管理员老师可以不扣我分', '14', '张晓茗', '20164799', '0', '0', '2020-02-24 19:02:05', '2020-05-24 18:11:44');
INSERT INTO `feedback` VALUES ('14', '预约不上', '2', '没有反馈了，凑凑数据量', '14', '张晓茗', '20164799', '1', '0', '2020-02-24 19:02:05', '2020-05-24 18:11:45');
INSERT INTO `feedback` VALUES ('15', '预约不上', '2', '今天是系统维修吗，一直登录不上', '15', '王晓红', '20164789', '0', '0', '2020-05-24 18:21:16', '2020-05-24 18:21:16');

-- ----------------------------
-- Table structure for layout
-- ----------------------------
DROP TABLE IF EXISTS `layout`;
CREATE TABLE `layout` (
  `layout_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `clazz` varchar(200) NOT NULL DEFAULT '' COMMENT '班级,数组格式,可能是合班上课',
  `college_id` int(10) NOT NULL DEFAULT '0' COMMENT '院id',
  `college` varchar(50) NOT NULL DEFAULT '' COMMENT '院',
  `specialty_id` int(10) NOT NULL DEFAULT '0' COMMENT '专业id',
  `specialty` varchar(50) NOT NULL DEFAULT '' COMMENT '专业',
  `course` varchar(50) NOT NULL DEFAULT '' COMMENT '课程名',
  `number` int(10) NOT NULL DEFAULT '0' COMMENT '人数',
  `started` datetime DEFAULT NULL COMMENT '上课日期',
  `clazz_hour` varchar(20) DEFAULT '' COMMENT '课时（第一节、第二节、晚自习）',
  `room_no` varchar(50) NOT NULL DEFAULT '' COMMENT '安排机房(教室)',
  `teacher` varchar(50) NOT NULL DEFAULT '' COMMENT '上课教师',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '教师联系方式',
  `admin_id` int(10) NOT NULL DEFAULT '0' COMMENT '排课管理员id',
  `admin` varchar(50) NOT NULL DEFAULT '' COMMENT '排课管理员',
  `admin_mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '排课管理员电话',
  `description` varchar(200) NOT NULL DEFAULT '' COMMENT '备注 / 描述 / 详情',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0：未删除 1：已删除',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`layout_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='排课';

-- ----------------------------
-- Records of layout
-- ----------------------------
INSERT INTO `layout` VALUES ('1', '[{\"value\":\"2016-01\"},{\"value\":\"2016-02\"}]', '0', '信息工程学院', '0', '电子商务 ', 'Java编程基础', '72', '2020-05-16 00:00:00', '第一节', '信息交流中心301', '毕向东', '18225796532', '0', '', '', '正常上课，无特殊要求', '0', '2020-05-16 18:02:59', '2020-05-24 01:37:49');
INSERT INTO `layout` VALUES ('2', '[{\"value\":\"2016-01\"}]', '0', '信息工程学院', '0', '电子商务 ', 'MySQL数据库设计', '105', '2020-06-04 00:00:00', '第二节', '信息交流中心303', '崔希凡', '18225796534', '0', '', '', '正常上课，无特殊要求', '0', '2020-05-16 18:02:59', '2020-05-24 14:06:56');
INSERT INTO `layout` VALUES ('4', '[{\"value\":\"2016-02\"},{\"value\":\"2016-03\"}]', '0', '机械工程学院', '0', '软件工程', '基于浏览器的WEB编程', '70', '2020-06-01 00:00:00', '第一节', '信息交流中心301', '果颖', '15986666536', '0', '', '', '没有需要叮嘱的', '0', '2020-05-24 02:38:49', '2020-05-24 02:38:49');
INSERT INTO `layout` VALUES ('6', '[{\"value\":\"2016-01\"},{\"value\":\"2016-03\"}]', '0', '信息工程学院', '0', '电子商务', 'C语言编程基础', '70', '2020-06-01 00:00:00', '第三节', '信息交流中心303', '翟自强', '18569663256', '0', '', '', '不需要其它要求，正常开门就可以', '0', '2020-05-24 02:45:09', '2020-05-24 02:45:09');
INSERT INTO `layout` VALUES ('7', '[{\"value\":\"2016-01\"},{\"value\":\"2016-02\"},{\"value\":\"2016-03\"}]', '0', '机械工程学院', '0', '软件工程', 'C#面向对象编程', '105', '2020-06-01 00:00:00', '第二节', '信息交流中心304', '翟自强', '18569663256', '0', '', '', '班级人数有点多，请管理员摆好座椅', '0', '2020-05-24 02:47:40', '2020-05-24 02:47:40');
INSERT INTO `layout` VALUES ('8', '[{\"value\":\"2016-02\"},{\"value\":\"2016-01\"},{\"value\":\"2016-03\"}]', '0', '信息工程学院', '0', '电子商务', 'ASP.NET程序设计', '105', '2020-06-01 00:00:00', '第一节', '信息交流中心302', '李金靖', '18566223369', '0', '', '', '开学第一课', '0', '2020-05-24 02:52:06', '2020-05-24 02:52:06');
INSERT INTO `layout` VALUES ('9', '[{\"value\":\"2016-01\"},{\"value\":\"2016-02\"},{\"value\":\"2016-03\"}]', '0', '机械工程学院', '0', '软件工程', 'PhotoShop美工与设计', '105', '2020-06-02 00:00:00', '第一节', '信息交流中心301', '刘丽娟', '15863236963', '0', '', '', '无备注', '0', '2020-05-24 02:54:23', '2020-05-24 02:54:23');
INSERT INTO `layout` VALUES ('10', '[{\"value\":\"2016-01\"},{\"value\":\"2016-02\"},{\"value\":\"2016-03\"}]', '0', '信息工程学院', '0', '电子商务', 'Java编程基础', '105', '2020-06-02 00:00:00', '第二节', '信息交流中心301', '毕向东', '18225796532', '0', '', '', '毕老师详解Java基础', '0', '2020-05-24 02:55:53', '2020-05-24 02:55:53');
INSERT INTO `layout` VALUES ('11', '[{\"value\":\"2016-02\"},{\"value\":\"2016-03\"}]', '0', '机械工程学院', '0', '软件工程', 'ASP.NET程序设计', '70', '2020-06-10 00:00:00', '第一节', '信息交流中心303', '李金靖', '18566223369', '0', '', '', '无', '0', '2020-05-24 02:57:15', '2020-05-24 14:06:56');
INSERT INTO `layout` VALUES ('12', '[{\"value\":\"2016-02\"},{\"value\":\"2016-03\"}]', '0', '信息工程学院', '0', '电子商务', '软件测试实践应用', '70', '2020-06-02 00:00:00', '第二节', '信息交流中心304', '果颖', '15986666536', '0', '', '', '哈哈', '0', '2020-05-24 02:59:02', '2020-05-24 12:25:36');
INSERT INTO `layout` VALUES ('13', '[{\"value\":\"2016-01\"},{\"value\":\"2016-02\"},{\"value\":\"2016-03\"}]', '0', '信息工程学院', '0', '电子商务', 'Linux操作系统基础', '105', '2020-06-03 00:00:00', '第二节', '信息交流中心302', '周阳', '13745535633', '0', '', '', '好好学习，天天向上', '0', '2020-05-24 03:00:50', '2020-05-24 03:39:17');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT '标题',
  `content` text COMMENT '通知内容',
  `admin_mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '发布人电话',
  `admin` varchar(50) NOT NULL DEFAULT '' COMMENT '发布人',
  `admin_id` int(10) NOT NULL DEFAULT '0' COMMENT '发布人id',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0：未删除 1：已删除',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='公告表';

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '闭馆通知，系统大版本升级，同学们需要合理安排', '各位同学注意了，周六周日机房设备整体升级，暂不开放', '13896589658', '翟老师', '2', '0', '2020-01-14 11:17:24', '2020-05-24 19:42:51');
INSERT INTO `notice` VALUES ('2', '天津商业大学计算中心，温馨提示，同学们注意了', '同学们，明天有大暴雨来临，希望同学们合理安排预约时间', '13896589658', '翟老师', '2', '0', '2020-05-24 18:43:42', '2020-05-24 19:42:51');
INSERT INTO `notice` VALUES ('3', '天津商业大学计算中心，开馆时间调整', '因为考虑到同学们快要毕业，都在做毕业设计，计算中心开放时间为早上8.00到晚上22.00', '13896589658', '翟老师', '2', '0', '2020-05-24 18:56:54', '2020-05-24 19:42:51');
INSERT INTO `notice` VALUES ('4', '天津商业大学计算中心，开馆时间调整', '冬季来临了，计算机也冬眠了，同学们体谅一下', '13896589658', '翟老师', '2', '0', '2020-05-24 18:59:40', '2020-05-24 19:42:52');
INSERT INTO `notice` VALUES ('5', '天津商业大学计算中心，温馨提示，同学们注意了', '请同学们不要带食物进入机房，可以带饮料和矿泉水', '13896589658', '翟老师', '2', '0', '2020-05-24 19:00:20', '2020-05-24 19:42:51');
INSERT INTO `notice` VALUES ('6', '天津商业大学计算中心，闭馆通知', '2020-06-20，计算中心要安排全校学生英语口语考试', '13896589658', '翟老师', '2', '0', '2020-05-24 19:02:07', '2020-05-24 19:42:51');

-- ----------------------------
-- Table structure for oper_log
-- ----------------------------
DROP TABLE IF EXISTS `oper_log`;
CREATE TABLE `oper_log` (
  `oper_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NOT NULL DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `oper_name` varchar(50) NOT NULL DEFAULT '' COMMENT '操作人员',
  `request_method` varchar(10) NOT NULL DEFAULT '' COMMENT '请求方式',
  `oper_location` varchar(255) NOT NULL DEFAULT '' COMMENT '操作地点',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `oper_ip` varchar(50) NOT NULL DEFAULT '' COMMENT '主机地址',
  `oper_url` varchar(255) NOT NULL DEFAULT '' COMMENT '请求URL',
  `oper_param` varchar(2000) NOT NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) NOT NULL DEFAULT '' COMMENT '返回参数',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0：未删除 1：已删除',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- ----------------------------
-- Records of oper_log
-- ----------------------------
INSERT INTO `oper_log` VALUES ('1', '新增推送记录表', '1', 'super_test', 'POST', '浙江 杭州', '1', '115.206.133.104', '/pushRecord/save', '{\"pageSize\":10,\"pageNum\":1}', '', '2020-01-17 16:01:21', '2020-02-26 20:39:30', '0');
INSERT INTO `oper_log` VALUES ('2', '新增公告记录', '1', 'super_test', 'POST', '浙江 杭州', '1', '115.206.133.104', '/pushRecord/save', '{\"pageSize\":10,\"pageNum\":1}', '', '2020-01-17 16:01:21', '2020-02-26 20:39:30', '0');
INSERT INTO `oper_log` VALUES ('3', '修改上机预约', '1', 'super_test', 'PUT', '浙江 杭州', '1', '115.206.133.104', '/pushRecord/save', '{\"pageSize\":10,\"pageNum\":1}', '', '2020-01-17 16:01:21', '2020-02-26 20:39:30', '0');
INSERT INTO `oper_log` VALUES ('4', '新增排课信息', '1', 'super_test', 'POST', '浙江 杭州', '1', '115.206.133.104', '/pushRecord/save', '{\"pageSize\":10,\"pageNum\":1}', '', '2020-01-17 16:01:21', '2020-02-26 20:39:30', '0');
INSERT INTO `oper_log` VALUES ('5', '删除系统用户', '1', 'super_test', 'DELETE', '浙江 杭州', '1', '115.206.133.104', '/pushRecord/save', '{\"pageSize\":10,\"pageNum\":1}', '', '2020-01-17 16:01:21', '2020-02-26 20:39:30', '0');
INSERT INTO `oper_log` VALUES ('6', '修改用户信息', '1', 'super_test', 'PUT', '浙江 杭州', '1', '115.206.133.104', '/pushRecord/save', '{\"pageSize\":10,\"pageNum\":1}', '', '2020-01-17 16:01:21', '2020-02-26 20:39:30', '0');

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `reservation_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT '0' COMMENT '预约人id',
  `user_no` varchar(50) NOT NULL DEFAULT '' COMMENT '学生学号',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '预约人姓名',
  `user_sex` int(1) NOT NULL DEFAULT '0' COMMENT '预约人性别',
  `user_college` varchar(50) NOT NULL DEFAULT '' COMMENT '预约人院',
  `user_specialty` varchar(50) NOT NULL DEFAULT '' COMMENT '预约人专业',
  `user_clazz_id` int(10) DEFAULT '0' COMMENT '班级id',
  `user_clazz` varchar(50) NOT NULL DEFAULT '' COMMENT '预约人班级',
  `user_photo` varchar(200) NOT NULL DEFAULT '' COMMENT '用户头像',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '预约状态（0=默认，1=预约待审核，2=审核通过，3=审核失败，4=变更待审核，5=变更审核通过，6=变更审核未通过，7=上机中，8=已下机，9=取消预约）',
  `step` text COMMENT '状态环节 Json 数组',
  `re_start_time` datetime DEFAULT NULL COMMENT '预约开始时间',
  `re_end_time` datetime DEFAULT NULL COMMENT '预约结束时间',
  `computer_id` int(10) NOT NULL DEFAULT '0' COMMENT '预约计算机id',
  `computer_no` int(11) NOT NULL DEFAULT '0' COMMENT '预约计算机编号',
  `room_no` varchar(50) NOT NULL DEFAULT '' COMMENT '计算机所在教室（试验室）',
  `is_on_time` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否准时来（0=未按时上机，1=准时上机）',
  `on_start_time` datetime DEFAULT NULL COMMENT '上机开始时间',
  `on_end_time` datetime DEFAULT NULL COMMENT '上机结束时间',
  `update_start_time` datetime DEFAULT NULL COMMENT '变更开始时间',
  `update_end_time` datetime DEFAULT NULL COMMENT '变更结束时间',
  `update_computer_id` int(10) NOT NULL DEFAULT '0' COMMENT '变更预约计算机id',
  `update_computer_no` varchar(50) NOT NULL DEFAULT '' COMMENT '变更预约计算机编号',
  `update_room_no` varchar(50) NOT NULL DEFAULT '' COMMENT '计算机所在教室（试验室）',
  `admin_id` int(10) NOT NULL DEFAULT '0' COMMENT '审核人id',
  `admin_name` varchar(50) NOT NULL DEFAULT '' COMMENT '审核人姓名',
  `description` varchar(100) NOT NULL DEFAULT '' COMMENT '备注 / 描述 / 详情（预约人发布时填写）',
  `check_no_pass_desc` varchar(100) NOT NULL DEFAULT '' COMMENT '预约审核无效详情（管理员填写）',
  `update_desc` varchar(100) NOT NULL DEFAULT '' COMMENT '变更详情（预约人变更或取消时填写）',
  `update_no_pass_desc` varchar(100) NOT NULL DEFAULT '' COMMENT '变更审核未通过详情（管理员填写）',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0：未删除 1：已删除',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`reservation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='预约表';

-- ----------------------------
-- Records of reservation
-- ----------------------------
INSERT INTO `reservation` VALUES ('1', '14', '20164799', '张晓茗', '0', '信息工程', '电子商务', '1', '2016-01', '', '2', null, '2020-05-24 13:19:03', '2020-05-24 15:19:06', '0', '3030111', '信息交流中心303', '0', null, null, null, null, '0', '0', '0', '0', '', '', '', '', '', '0', '2020-01-18 10:02:41', '2020-05-24 15:04:13');
INSERT INTO `reservation` VALUES ('2', '14', '20164799', '张晓茗', '0', '信息工程', '电子商务', '0', '2016-01', 'http://guaidonggua.cn/cc/img/yoona_03.73006c12.jpg', '2', null, '2020-05-24 17:00:00', '2020-05-24 18:00:00', '0', '3010110', '信息交流中心303', '0', null, null, null, null, '0', '', '', '0', '', '我想去上机做实验', '', '', '', '0', '2020-05-24 14:46:41', '2020-05-24 15:52:57');
INSERT INTO `reservation` VALUES ('3', '15', '20164789', '王晓红', '0', '机械工程', '机械设计制造及自动化', '0', '2016-02', 'http://guaidonggua.cn/cc/img/yoona_02.7b5b70ce.jpg', '2', null, '2020-05-31 09:00:00', '2020-05-31 11:00:00', '0', '3020608', '信息交流中心302', '0', null, null, null, null, '0', '', '', '0', '', '周末上机做毕业设计', '', '', '', '0', '2020-05-24 15:03:03', '2020-05-24 15:03:03');
INSERT INTO `reservation` VALUES ('4', '14', '20164799', '张晓茗', '0', '信息工程', '电子商务', '0', '2016-01', 'http://guaidonggua.cn/cc/img/yoona_03.73006c12.jpg', '2', null, '2020-05-29 10:00:00', '2020-05-31 12:00:00', '0', '3010202', '信息交流中心301', '0', null, null, null, null, '0', '', '', '0', '', '哈哈', '', '', '', '0', '2020-05-24 15:56:12', '2020-05-24 15:56:12');
INSERT INTO `reservation` VALUES ('5', '14', '20164799', '张晓茗', '0', '信息工程', '电子商务', '0', '2016-01', 'http://guaidonggua.cn/cc/img/yoona_03.73006c12.jpg', '2', null, '2020-05-24 16:16:49', '2020-05-24 18:16:51', '0', '3040811', '信息交流中心304', '0', null, null, null, null, '0', '', '', '0', '', '哈哈', '', '', '', '0', '2020-05-24 16:17:13', '2020-05-24 16:17:13');
INSERT INTO `reservation` VALUES ('6', '15', '20164789', '王晓红', '0', '机械工程', '机械设计制造及自动化', '0', '2016-02', 'http://guaidonggua.cn/cc/img/yoona_02.7b5b70ce.jpg', '2', null, '2020-05-24 16:22:23', '2020-05-24 18:22:27', '0', '3040506', '信息交流中心304', '0', null, null, null, null, '0', '', '', '0', '', '中国历史文化学习', '', '', '', '0', '2020-05-24 16:23:08', '2020-05-24 16:23:08');
INSERT INTO `reservation` VALUES ('7', '15', '20164789', '王晓红', '0', '机械工程', '机械设计制造及自动化', '0', '2016-02', 'http://guaidonggua.cn/cc/img/yoona_02.7b5b70ce.jpg', '2', null, '2020-05-24 20:49:13', '2020-05-24 22:49:19', '0', '3010307', '信息交流中心301', '0', null, null, null, null, '0', '', '', '0', '', 'qqqqqq', '', '', '', '0', '2020-05-24 20:51:50', '2020-05-24 20:51:50');
INSERT INTO `reservation` VALUES ('8', '15', '20164789', '王晓红', '0', '机械工程', '机械设计制造及自动化', '0', '2016-02', 'http://guaidonggua.cn/cc/img/yoona_02.7b5b70ce.jpg', '2', null, '2020-05-24 15:00:00', '2020-05-24 17:00:00', '0', '3020508', '信息交流中心302', '0', null, null, null, null, '0', '', '', '0', '', '目目目目目目目目目目目止', '', '', '', '0', '2020-05-24 20:53:29', '2020-05-24 20:53:29');
INSERT INTO `reservation` VALUES ('9', '15', '20164789', '王晓红', '0', '机械工程', '机械设计制造及自动化', '0', '2016-02', 'http://guaidonggua.cn/cc/img/yoona_02.7b5b70ce.jpg', '2', null, '2020-05-17 08:00:00', '2020-05-17 09:00:00', '0', '3010409', '信息交流中心301', '0', null, null, null, null, '0', '', '', '0', '', '一公里一公里一公里', '', '', '', '0', '2020-05-24 21:00:11', '2020-05-24 21:00:11');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `room_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `room_no` varchar(50) NOT NULL COMMENT '教室名',
  `build` varchar(50) NOT NULL DEFAULT '' COMMENT '此教室 什么楼/哪一幢',
  `floor` int(1) NOT NULL DEFAULT '1' COMMENT '此教室第几层',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0：未删除 1：已删除',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教室表';

-- ----------------------------
-- Records of room
-- ----------------------------

-- ----------------------------
-- Table structure for specialty
-- ----------------------------
DROP TABLE IF EXISTS `specialty`;
CREATE TABLE `specialty` (
  `specialty_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `specialty` varchar(50) NOT NULL COMMENT '专业',
  `college_id` int(10) NOT NULL DEFAULT '0' COMMENT '所属院id',
  `college` varchar(50) NOT NULL DEFAULT '' COMMENT '所属院',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0：未删除 1：已删除',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`specialty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COMMENT='专业';

-- ----------------------------
-- Records of specialty
-- ----------------------------
INSERT INTO `specialty` VALUES ('1', '软件工程', '8', '信息工程学院', '0', '2020-01-18 10:51:38', '2020-02-11 20:44:20');
INSERT INTO `specialty` VALUES ('2', '电子商务', '8', '信息工程学院', '0', '2020-01-18 10:52:09', '2020-02-11 20:44:20');
INSERT INTO `specialty` VALUES ('7', '信息管理与信息系统', '8', '信息工程学院', '0', '2020-02-11 20:45:14', '2020-02-11 20:45:14');
INSERT INTO `specialty` VALUES ('8', '计算机科学与技术', '8', '信息工程学院', '0', '2020-02-11 20:45:15', '2020-02-11 20:45:15');
INSERT INTO `specialty` VALUES ('9', '自动化', '8', '信息工程学院', '0', '2020-02-11 20:45:15', '2020-02-11 20:45:15');
INSERT INTO `specialty` VALUES ('10', '通信工程', '8', '信息工程学院', '0', '2020-02-11 20:45:15', '2020-02-11 20:45:15');
INSERT INTO `specialty` VALUES ('11', '工商管理', '1', '管理学院', '0', '2020-02-11 20:46:07', '2020-02-11 20:47:18');
INSERT INTO `specialty` VALUES ('12', '市场营销', '1', '管理学院', '0', '2020-02-11 20:47:18', '2020-02-11 20:47:18');
INSERT INTO `specialty` VALUES ('13', '人力资源管理', '1', '管理学院', '0', '2020-02-11 20:47:18', '2020-02-11 20:47:18');
INSERT INTO `specialty` VALUES ('14', '物流管理', '1', '管理学院', '0', '2020-02-11 20:47:18', '2020-02-11 20:47:18');
INSERT INTO `specialty` VALUES ('15', '宝石及材料工艺学', '1', '管理学院', '0', '2020-02-11 20:47:18', '2020-02-11 20:47:18');
INSERT INTO `specialty` VALUES ('16', '旅游管理', '1', '管理学院', '0', '2020-02-11 20:47:18', '2020-02-11 20:47:18');
INSERT INTO `specialty` VALUES ('17', '酒店管理', '1', '管理学院', '0', '2020-02-11 20:47:19', '2020-02-11 20:47:19');
INSERT INTO `specialty` VALUES ('18', '工程管理', '1', '管理学院', '0', '2020-02-11 20:47:19', '2020-02-11 20:47:19');
INSERT INTO `specialty` VALUES ('19', '经济学', '2', '经济学院', '0', '2020-02-11 20:48:19', '2020-02-11 20:48:19');
INSERT INTO `specialty` VALUES ('20', '国际经济与贸易', '2', '经济学院', '0', '2020-02-11 20:48:19', '2020-02-11 20:48:19');
INSERT INTO `specialty` VALUES ('21', '财政学', '2', '经济学院', '0', '2020-02-11 20:48:19', '2020-02-11 20:48:19');
INSERT INTO `specialty` VALUES ('22', '金融学', '2', '经济学院', '0', '2020-02-11 20:48:20', '2020-02-11 20:48:20');
INSERT INTO `specialty` VALUES ('23', '信用管理', '2', '经济学院', '0', '2020-02-11 20:48:20', '2020-02-11 20:48:20');
INSERT INTO `specialty` VALUES ('24', '能源与动力工程', '3', '机械工程学院', '0', '2020-02-11 20:49:28', '2020-02-11 20:49:28');
INSERT INTO `specialty` VALUES ('25', '建筑环境与能源应用工程', '3', '机械工程学院', '0', '2020-02-11 20:49:28', '2020-02-11 20:49:28');
INSERT INTO `specialty` VALUES ('26', '包装工程', '3', '机械工程学院', '0', '2020-02-11 20:49:28', '2020-02-11 20:49:28');
INSERT INTO `specialty` VALUES ('27', '机械设计制造及自动化', '3', '机械工程学院', '0', '2020-02-11 20:49:28', '2020-02-11 20:49:28');
INSERT INTO `specialty` VALUES ('28', '食品科学与工程', '4', '生物技术与食品科学学院', '0', '2020-02-11 20:50:59', '2020-02-11 20:50:59');
INSERT INTO `specialty` VALUES ('29', '生物工程', '4', '生物技术与食品科学学院', '0', '2020-02-11 20:50:59', '2020-02-11 20:50:59');
INSERT INTO `specialty` VALUES ('30', '制药工程', '4', '生物技术与食品科学学院', '0', '2020-02-11 20:50:59', '2020-02-11 20:50:59');
INSERT INTO `specialty` VALUES ('31', '生物技术', '4', '生物技术与食品科学学院', '0', '2020-02-11 20:50:59', '2020-02-11 20:50:59');
INSERT INTO `specialty` VALUES ('32', '食品质量与安全', '4', '生物技术与食品科学学院', '0', '2020-02-11 20:50:59', '2020-02-11 20:50:59');
INSERT INTO `specialty` VALUES ('33', '药事管理', '4', '生物技术与食品科学学院', '0', '2020-02-11 20:51:00', '2020-02-11 20:51:00');
INSERT INTO `specialty` VALUES ('34', '应用化学', '4', '生物技术与食品科学学院', '0', '2020-02-11 20:51:00', '2020-02-11 20:51:00');
INSERT INTO `specialty` VALUES ('35', '法学', '5', '法学院', '0', '2020-02-11 20:51:35', '2020-02-11 20:51:35');
INSERT INTO `specialty` VALUES ('36', '应用心理学', '5', '法学院', '0', '2020-02-11 20:51:36', '2020-02-11 20:51:36');
INSERT INTO `specialty` VALUES ('37', '行政管理', '6', '公共管理学院', '0', '2020-02-11 20:52:30', '2020-02-11 20:52:30');
INSERT INTO `specialty` VALUES ('38', '公共事业管理', '6', '公共管理学院', '0', '2020-02-11 20:52:30', '2020-02-11 20:52:30');
INSERT INTO `specialty` VALUES ('39', '土地资源管理', '6', '公共管理学院', '0', '2020-02-11 20:52:30', '2020-02-11 20:52:30');
INSERT INTO `specialty` VALUES ('40', '会展经济与管理', '6', '公共管理学院', '0', '2020-02-11 20:52:30', '2020-02-11 20:52:30');
INSERT INTO `specialty` VALUES ('41', '财务管理（中澳合作）', '9', '国际教育合作学院', '0', '2020-02-11 20:55:14', '2020-02-11 20:55:14');
INSERT INTO `specialty` VALUES ('42', '旅游管理（高尔夫经营管理方向）', '9', '国际教育合作学院', '0', '2020-02-11 20:55:14', '2020-02-11 20:55:14');
INSERT INTO `specialty` VALUES ('43', '人力资源管理（中美合作）', '9', '国际教育合作学院', '0', '2020-02-11 20:55:15', '2020-02-11 20:55:15');
INSERT INTO `specialty` VALUES ('44', '视觉传达设计', '10', '艺术学院', '0', '2020-02-11 21:01:08', '2020-02-11 21:01:08');
INSERT INTO `specialty` VALUES ('45', '环境设计', '10', '艺术学院', '0', '2020-02-11 21:01:08', '2020-02-11 21:01:08');
INSERT INTO `specialty` VALUES ('46', '产品设计', '10', '艺术学院', '0', '2020-02-11 21:01:08', '2020-02-11 21:01:08');
INSERT INTO `specialty` VALUES ('47', '动画', '10', '艺术学院', '0', '2020-02-11 21:01:08', '2020-02-11 21:01:08');
INSERT INTO `specialty` VALUES ('48', '绘画', '10', '艺术学院', '0', '2020-02-11 21:01:08', '2020-02-11 21:01:08');
INSERT INTO `specialty` VALUES ('49', '英语', '11', '外国语学院', '0', '2020-02-11 21:03:13', '2020-02-11 21:03:13');
INSERT INTO `specialty` VALUES ('50', '日语', '11', '外国语学院', '0', '2020-02-11 21:03:13', '2020-02-11 21:03:13');
INSERT INTO `specialty` VALUES ('51', '信息与计算科学', '12', '理学院', '0', '2020-02-11 21:04:08', '2020-02-11 21:04:08');
INSERT INTO `specialty` VALUES ('52', '数学与应用数学', '12', '理学院', '0', '2020-02-11 21:04:08', '2020-02-11 21:04:08');
INSERT INTO `specialty` VALUES ('53', '应用物理学', '12', '理学院', '0', '2020-02-11 21:04:08', '2020-02-11 21:04:08');
INSERT INTO `specialty` VALUES ('54', '统计学', '12', '理学院', '0', '2020-02-11 21:04:08', '2020-02-11 21:04:08');
INSERT INTO `specialty` VALUES ('55', '应用统计学', '12', '理学院', '0', '2020-02-11 21:04:08', '2020-02-11 21:04:08');
INSERT INTO `specialty` VALUES ('56', '会计学', '13', '会计学院', '0', '2020-02-11 21:04:34', '2020-02-11 21:04:34');
INSERT INTO `specialty` VALUES ('57', '财务管理', '13', '会计学院', '0', '2020-02-11 21:04:35', '2020-02-11 21:04:35');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '类型，（0-未设置（也就是未开通），1=管理员、2=学生）',
  `user_specialty` varchar(50) DEFAULT '' COMMENT '用户专业',
  `user_college` varchar(50) DEFAULT '' COMMENT '用户院',
  `clazz_id` int(10) NOT NULL DEFAULT '0' COMMENT '班级id',
  `clazz` varchar(50) NOT NULL DEFAULT '' COMMENT '班级',
  `sex` int(1) NOT NULL DEFAULT '0' COMMENT '性别（1-男，0-女）',
  `user_no` varchar(50) NOT NULL DEFAULT '' COMMENT '账户（学号 / 工号）',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `photo` varchar(200) NOT NULL DEFAULT '' COMMENT '头像照片',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '电话',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱',
  `violation` int(1) NOT NULL DEFAULT '0' COMMENT '违规次数，超过3次，本学期不可进行预约',
  `last_time` datetime DEFAULT NULL COMMENT '上次上机时间（自主上机）',
  `next_time` datetime DEFAULT NULL COMMENT '预约下次时间',
  `use_hour` int(10) NOT NULL DEFAULT '0' COMMENT '上机时长',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除，0：未删除 1：已删除',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '翟老师', '1', '电子商务', '信息工程', '0', '2015-03', '0', '11111111', '123456', 'http://guaidonggua.cn/cc/img/timg.8f684b34.png', '13896589658', '96589658@qq.com', '0', null, null, '0', '0', '2019-12-29 16:49:14', '2020-05-24 18:33:54');
INSERT INTO `user` VALUES ('14', '张晓茗', '2', '电子商务', '信息工程', '0', '2016-01', '0', '20164799', '123456', 'http://guaidonggua.cn/cc/img/yoona_03.73006c12.jpg', '18225739788', 'wuji@qq.com', '0', null, null, '0', '0', '2020-05-16 21:53:44', '2020-05-24 15:03:59');
INSERT INTO `user` VALUES ('15', '王晓红', '2', '机械设计制造及自动化', '机械工程', '0', '2016-02', '0', '20164789', '123456', 'http://guaidonggua.cn/cc/img/yoona_02.7b5b70ce.jpg', '13994033223', 'hong@qq.com', '0', null, null, '0', '0', '2020-05-24 14:54:31', '2020-05-24 14:54:31');
