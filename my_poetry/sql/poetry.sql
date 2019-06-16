/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : poetry

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-05-06 20:33:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `collect_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) DEFAULT NULL,
  `mp_id` int(11) DEFAULT NULL,
  `datetime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`collect_id`),
  UNIQUE KEY `index_user` (`user_id`,`mp_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `mp_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `from_userid` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '144', '11', 'orH0F0d_XbOKGaGtmrVE2JLx3uck', '2019-4-17 18:58:29');
INSERT INTO `comment` VALUES ('2', '144', '11', 'orH0F0d_XbOKGaGtmrVE2JLx3uck', '2019-4-17 19:14:47');
INSERT INTO `comment` VALUES ('3', '145', '11', 'orH0F0d_XbOKGaGtmrVE2JLx3uck', '2019-4-17 19:16:24');
INSERT INTO `comment` VALUES ('4', '145', '11', 'orH0F0d_XbOKGaGtmrVE2JLx3uck', '2019-4-17 19:16:27');
INSERT INTO `comment` VALUES ('5', '147', '11', 'orH0F0d_XbOKGaGtmrVE2JLx3uck', '2019-4-24 18:53:26');
INSERT INTO `comment` VALUES ('6', '145', '33', 'orH0F0d_XbOKGaGtmrVE2JLx3uck', '2019-5-3 9:12:33');
INSERT INTO `comment` VALUES ('7', '144', '22', 'orH0F0d_XbOKGaGtmrVE2JLx3uck', '2019-5-3 9:29:16');
INSERT INTO `comment` VALUES ('8', '144', '啊啊', 'orH0F0d_XbOKGaGtmrVE2JLx3uck', '2019-5-3 9:29:28');
INSERT INTO `comment` VALUES ('9', '146', '11', 'orH0F0d_XbOKGaGtmrVE2JLx3uck', '2019-5-3 9:33:37');

-- ----------------------------
-- Table structure for modern_poetry
-- ----------------------------
DROP TABLE IF EXISTS `modern_poetry`;
CREATE TABLE `modern_poetry` (
  `mp_id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `mp_title` varchar(30) DEFAULT NULL COMMENT '题目',
  `mp_content` varchar(200) DEFAULT NULL COMMENT '部分内容',
  `mp_img` text COMMENT '图片',
  `mp_author` varchar(30) DEFAULT NULL COMMENT '作者',
  `mp_datetime` varchar(30) DEFAULT NULL COMMENT '时间',
  `mp_detail` text COMMENT '全文',
  `pin_yin` varchar(255) DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  PRIMARY KEY (`mp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8 COMMENT='现代诗歌表';

-- ----------------------------
-- Records of modern_poetry
-- ----------------------------
INSERT INTO `modern_poetry` VALUES ('144', '面朝大海,春暖花开', '从明天起, 做一个幸福的人 喂马, 劈柴, 周游世界 从明天起, 关心粮食和蔬菜 我有一所房子, 面朝大海, 春暖花开', 'c6ae6951-c442-4610-b978-a552c71c5e14.jpg', '海子', '2019-4-5 17:38:08', '从明天起, 做一个幸福的人\r\n喂马, 劈柴, 周游世界\r\n从明天起, 关心粮食和蔬菜\r\n我有一所房子, 面朝大海, 春暖花开\r\n\r\n从明天起, 和每一个亲人通信\r\n告诉他们我的幸福\r\n那幸福的闪电告诉我的\r\n我将告诉每一个人\r\n\r\n给每一条河每一座山取一个温暖的名字\r\n陌生人, 我也为你祝福\r\n愿你有一个灿烂的前程\r\n愿你有情人终成眷属\r\n愿你在尘世获得幸福\r\n我只愿面朝大海, 春暖花开', 'haizi', '33');
INSERT INTO `modern_poetry` VALUES ('145', '七月不远 ————给青海湖请熄灭我的爱情', '七月不远 性别的诞生不远 爱情不远————马鼻子下 湖泊含盐  因此青海湖不远 湖畔一捆捆蜂箱 使我显得凄凄迷人 青草开满鲜花。  青海湖上 我的孤独如天堂的马匹 (因此 天堂的马匹不远)', 'd390b68e-6b6e-4ab5-875f-2d74efd04ce7.jpg', '海子', '2019-4-5 17:38:56', '七月不远\r\n性别的诞生不远\r\n爱情不远————马鼻子下\r\n湖泊含盐\r\n\r\n因此青海湖不远\r\n湖畔一捆捆蜂箱\r\n使我显得凄凄迷人\r\n青草开满鲜花。\r\n\r\n青海湖上\r\n我的孤独如天堂的马匹\r\n(因此 天堂的马匹不远)\r\n\r\n我就是那个情种： 诗中吟唱的野花\r\n天堂的马肚子里唯一含毒的野花\r\n(青海湖 请熄灭我的爱情！)\r\n\r\n野花青梗不远 医箱内古老姓氏不远\r\n(其他的浪子 治好了疾病\r\n已回原籍 我这就想去见你们)\r\n\r\n因此爬山涉水死亡不远\r\n骨骼挂遍我身体\r\n如同蓝色水上的树枝\r\n\r\n啊！ 青海湖 暮色苍茫的水面\r\n一切如在眼前！\r\n\r\n只有五月生命的鸟群早已飞去\r\n只有饮我宝石的头一只鸟早已飞去\r\n只剩下青海湖 这宝石的尸体\r\n暮色苍茫的水面', 'haizi', '7');
INSERT INTO `modern_poetry` VALUES ('146', '黑夜的献诗 ——献给黑夜的女儿', '黑夜从大地上升起 遮住了光明的天空 丰收后荒凉的大地 黑夜从你内部升起  你从远方来, 我到远方去 遥远的路程经过这里 天空一无所有 为何给我安慰', '1b50d11e-b272-4531-ba12-41bd1a82831b.jpg', '海子', '2019-4-5 17:39:37', '黑夜从大地上升起\r\n遮住了光明的天空\r\n丰收后荒凉的大地\r\n黑夜从你内部升起\r\n\r\n你从远方来, 我到远方去\r\n遥远的路程经过这里\r\n天空一无所有\r\n为何给我安慰\r\n\r\n丰收之后荒凉的大地\r\n人们取走了一年的收成\r\n取走了粮食骑走了马\r\n留在地里的人, 埋的很深\r\n\r\n草叉闪闪发亮, 稻草堆在火上\r\n稻谷堆在黑暗的谷仓\r\n谷仓中太黑暗, 太寂静, 太丰收\r\n也太荒凉, 我在丰收中看到了阎王的眼睛\r\n\r\n黑雨滴一样的鸟群\r\n从黄昏飞入黑夜\r\n黑夜一无所有\r\n为何给我安慰\r\n\r\n走在路上\r\n放声歌唱\r\n大风刮过山岗\r\n上面是无边的天空', 'haizi', '4');
INSERT INTO `modern_poetry` VALUES ('147', '春天,十个海子', '春天, 十个海子全都复活 在光明的景色中 嘲笑这一个野蛮而悲伤的海子 你这么长久地沉睡到底是为了什么?  春天, 十个海子低低地怒吼 围着你和我跳舞、唱歌 扯乱你的黑头发, 骑上你飞奔而去, 尘土飞扬 你被劈开的疼痛在大地弥漫', '200a413e-4aff-4dcc-b0e0-f5c419cb6760.jpg', '海子', '2019-4-5 17:40:11', '春天, 十个海子全都复活\r\n在光明的景色中\r\n嘲笑这一个野蛮而悲伤的海子\r\n你这么长久地沉睡到底是为了什么?\r\n\r\n春天, 十个海子低低地怒吼\r\n围着你和我跳舞、唱歌\r\n扯乱你的黑头发, 骑上你飞奔而去, 尘土飞扬\r\n你被劈开的疼痛在大地弥漫\r\n\r\n在春天, 野蛮而北上的海子\r\n就剩这一个, 最后一个\r\n这是一个黑夜的孩子, 沉浸于冬天, 倾心死亡\r\n不能自拔, 热爱寒冷而空虚的乡村\r\n\r\n那里的谷物高高堆起, 遮住了窗子\r\n它们一半用于一家六口人的嘴, 吃和胃\r\n一半用于农业, 他们自己的繁殖\r\n大风从东吹到西, 从北刮到南, 不论黑夜和黎明\r\n你所说的曙光究竟是什么意思', 'haizi', '3');
INSERT INTO `modern_poetry` VALUES ('148', '月光', '今夜美丽的月光 你看多好！ 照着月光 饮水和盐的马 和声音  今夜美丽的月光 你看多美丽 羊群中 生命和死亡宁静的声音 我在倾听！', '1b9a3f95-2929-4dd6-8290-3e6fe5be59ab.jpg', '海子', '2019-4-5 17:41:02', '今夜美丽的月光 你看多好！\r\n照着月光\r\n饮水和盐的马\r\n和声音\r\n\r\n今夜美丽的月光 你看多美丽\r\n羊群中 生命和死亡宁静的声音\r\n我在倾听！\r\n\r\n这是一支大地和水的歌谣 月光！\r\n不要说 你是灯中之灯 月光！\r\n不要说心中有一个地方\r\n那是我一直不敢梦见的地方\r\n不要问 桃子对桃花的珍藏\r\n不要问 打麦大地 处女 桂花和村镇\r\n今夜美丽的月光 你看多好！\r\n\r\n不要说死亡的烛光何须倾倒\r\n生命依然生长在忧愁的河水上\r\n月光照着月光 月光普照\r\n今夜美丽的月光合在一起流淌', 'haizi', '2');
INSERT INTO `modern_poetry` VALUES ('149', '女孩子', '她走来 断断续续走来 洁净的脚 沾满清凉的露水', 'ce315cfb-b8bb-403e-b42f-32841e72147f.jpg', '海子', '2019-4-5 17:43:15', '她走来\r\n断断续续走来\r\n洁净的脚\r\n沾满清凉的露水\r\n\r\n她有些忧郁\r\n望望用泥草筑起的房屋\r\n望望父亲\r\n她用双手分开黑发\r\n一支野桃花斜插着默默无语\r\n另一支送给了谁\r\n却从来没人问起\r\n\r\n春天是风\r\n秋天是月亮\r\n在我感觉到时\r\n她已去了另一个地方\r\n那里雨后的篱笆象一条蓝色的\r\n小溪', 'haizi', '1');

-- ----------------------------
-- Table structure for poet
-- ----------------------------
DROP TABLE IF EXISTS `poet`;
CREATE TABLE `poet` (
  `poet_id` int(11) NOT NULL AUTO_INCREMENT,
  `poet_name` varchar(255) DEFAULT NULL,
  `poet_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`poet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of poet
-- ----------------------------
INSERT INTO `poet` VALUES ('41', '顾城', 'd965788e-ad6d-4a83-b695-5cccb73c91a5.jpg');
INSERT INTO `poet` VALUES ('42', '海子', '579fea75-e74b-4fcb-97fa-5786af260cef.jpg');
INSERT INTO `poet` VALUES ('43', '汪国真', '517da1f1-fb0d-4420-b894-e5f3efc0e233.jpg');
INSERT INTO `poet` VALUES ('44', '戴望舒', 'd7455558-64d2-4e49-b559-56c36572a06b.jpg');
INSERT INTO `poet` VALUES ('45', '林徽因', '352cd5b0-bd1e-47af-92f3-54362d9019e3.jpg');
INSERT INTO `poet` VALUES ('46', '徐志摩', 'e86bb977-fae1-4a1b-92a5-d2a260d5051a.jpg');
INSERT INTO `poet` VALUES ('48', '北岛', 'e99ddf67-fc37-4b63-a048-e2a17a9a2fb9.jpg');
INSERT INTO `poet` VALUES ('49', '仓央嘉措', '5f5d32f3-7654-42c8-a800-6df204657ccc.jpg');
INSERT INTO `poet` VALUES ('51', '冯唐', 'e634398a-6989-4fa9-98b8-07056c9cf5f3.jpg');
INSERT INTO `poet` VALUES ('52', '2', '57f70461-88bc-4a63-9846-84e372f8889e.jpg');
INSERT INTO `poet` VALUES ('54', '席慕容', 'f3973132-df0a-481e-9db4-f9a834c87230.jpg');

-- ----------------------------
-- Table structure for root_user
-- ----------------------------
DROP TABLE IF EXISTS `root_user`;
CREATE TABLE `root_user` (
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of root_user
-- ----------------------------
INSERT INTO `root_user` VALUES ('1', '11', '11');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `update_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('orH0F0d_XbOKGaGtmrVE2JLx3uck', 'orH0F0d_XbOKGaGtmrVE2JLx3uck', 'https://wx.qlogo.cn/mmopen/vi_32/AsicA5y6qXjaryR01AibXCicV55zE3HqlLWkQ7U6c2kkqcsysInV69N7gLksUepOFnCYDiaib8Y3tSBKS6w2iam40asQ/132', '万州', '卡布卡布', '1', '重庆', null, null);
