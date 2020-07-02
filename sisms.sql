/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : simss

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2019-08-29 15:15:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseNunber` varchar(20) NOT NULL,
  `courseName` varchar(20) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `length` float DEFAULT NULL,
  `auditing` tinyint(1) DEFAULT NULL,
  `requirment` int(11) DEFAULT NULL,
  `providing` varchar(4) DEFAULT NULL,
  `semeter` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`courseNunber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('001', '母猪的产后护理', '13', '4500', '1', null, null, null);
INSERT INTO `course` VALUES ('002', '上单的抗压教学', '8', '6000', '0', null, null, null);
INSERT INTO `course` VALUES ('003', '中单的支援技巧', '5', '2000', '1', null, null, null);
INSERT INTO `course` VALUES ('004', '射手的发育途径', '6', '7000', '0', null, null, null);
INSERT INTO `course` VALUES ('005', '打野的支援路线', '9', '2000', '1', null, null, null);
INSERT INTO `course` VALUES ('006', '两周当上HR', '1', '5000', '1', null, null, null);
INSERT INTO `course` VALUES ('007', '腮红的涂抹技巧', '3', '5800', '0', null, null, null);
INSERT INTO `course` VALUES ('008', '跟CXK学篮球', '10', '5800', '1', null, null, null);
INSERT INTO `course` VALUES ('009', '计算机环境工程学', '8', '4000', '0', null, null, null);
INSERT INTO `course` VALUES ('010', '软件工程师成仙攻略', '13', '30', '1', null, null, null);

-- ----------------------------
-- Table structure for `money`
-- ----------------------------
DROP TABLE IF EXISTS `money`;
CREATE TABLE `money` (
  `payNumber` varchar(20) NOT NULL,
  `staffID` varchar(20) NOT NULL,
  `payPrice` int(11) DEFAULT NULL,
  `isPay` tinyint(1) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `payDate` date DEFAULT NULL,
  `payKind` varchar(20) DEFAULT NULL,
  `staffName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`payNumber`),
  KEY `FK_student_money` (`staffID`),
  CONSTRAINT `FK_student_money` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of money
-- ----------------------------
INSERT INTO `money` VALUES ('1', '2017303213', '-1200', '1', '2019-09-04', '2019-08-29', '办公室桌位', '邓子凡');
INSERT INTO `money` VALUES ('10', '201504', '5800', '0', '2019-09-04', null, '工资到账', '壮三持');
INSERT INTO `money` VALUES ('11', '2017303213', '20000', '1', null, '2019-08-30', '季度奖金', '邓子凡');
INSERT INTO `money` VALUES ('12', '2017303183', '18000', '1', null, '2019-09-01', '工资到账', '王子益');
INSERT INTO `money` VALUES ('2', '2017303213', '-400', '1', '2019-09-04', '2019-08-29', '杂费', '邓子凡');
INSERT INTO `money` VALUES ('3', '201501', '-5', '1', '2019-09-04', '2019-08-29', '停车费', '王一博');
INSERT INTO `money` VALUES ('4', '2017303183', '-1200', '0', '2019-09-04', null, '住宿费', '王子益');
INSERT INTO `money` VALUES ('5', '201502', '-2400', '0', '2019-09-04', null, '住宿费', '料此');
INSERT INTO `money` VALUES ('6', '2017303177', '58', '1', null, '2019-08-31', '工资到账', '任卓力');
INSERT INTO `money` VALUES ('7', '2017303185', '-800', '1', '2019-09-04', '2019-09-03', '违反规定罚金', '王生泽');
INSERT INTO `money` VALUES ('8', '2017303199', '5000', '0', null, null, '工资到账', '刘广厚');
INSERT INTO `money` VALUES ('9', '2017303186', '8000', '1', null, '2019-08-30', '工资到账', '许卓');

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `noticeID` varchar(20) NOT NULL,
  `context` text,
  PRIMARY KEY (`noticeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1501', '<div>公告：各学院及相关教学单位：</div><div>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 2018～2019学年秋季学期第三周至第四周，将对2017～2018学年春季学期开设的课程（部分不安排补考的课程除外）安排补考，现将有关事项通知如下：</div><div>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 1.根据规定，在校学生（2015级以后）不需要学生个人报名补考，系统将自动生成补考学生应考名单，补考仍未通过的课程只能通过重修且考核合格后取得相应学分；在校学生（2013和2014级延长学制）可通过报名补考或通过重修方式参与课程考核，考核合格后取得相应学分，要密切留意、及时确认和反馈课程编码更换等原因造成的补考课程信息不全的情况，以免造成考试科目遗漏。</div><div>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 2.结业生返校补考：请在9月3日前到所在学院报名（报名信息包括：学号、姓名、课程号、课程名称、学分），学院整理汇总后9月4日下班前交教务处学生注册中心。</div><div>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 3.在校学生8月27日至9月3日期间，务必要认真核对补考报名情况，以免造成考试科目遗漏。9月6日（第一周周四）开课学院本科教学管理办公室打印报名名单，提交给相关课程教学负责人，按照实际报名人数印刷试卷。学生注册中心于9月6日（第一周周四）安排补考课程的时间地点。正式考试时间从9月17日（第三周周一）开始。已报名学生按照考试日程安排表参加考试，学生可在新教务系统中查询自己报名的考试安排情况。</div><div>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 4.补考结束后，开课学院教务员指定老师录入补考成绩，各开课学院确保在10月10日之前完成补考成绩录入工作，并于10月11日前将纸质成绩单送至学生所在学院，并按照相应要求完成成绩单和考试材料的归档工作。</div><div>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 教务处&nbsp;</div><div>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;2018年7月12日</div>');

-- ----------------------------
-- Table structure for `office`
-- ----------------------------
DROP TABLE IF EXISTS `office`;
CREATE TABLE `office` (
  `officeNumber` varchar(20) NOT NULL,
  `officeKind` int(11) DEFAULT NULL,
  `tableNumber` int(11) DEFAULT NULL,
  `isFull` tinyint(1) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`officeNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of office
-- ----------------------------
INSERT INTO `office` VALUES ('01', '3', '1200', '0', '研发部门');
INSERT INTO `office` VALUES ('02', '3', '2400', '1', '实验室');
INSERT INTO `office` VALUES ('03', '3', '1200', '1', '后勤');
INSERT INTO `office` VALUES ('04', '3', '1200', '1', '研发部门');
INSERT INTO `office` VALUES ('05', '3', '1200', '0', '实验室');

-- ----------------------------
-- Table structure for `officeapplication`
-- ----------------------------
DROP TABLE IF EXISTS `officeapplication`;
CREATE TABLE `officeapplication` (
  `applyNumber` varchar(30) NOT NULL,
  `staffID` varchar(20) NOT NULL,
  `applyKind` varchar(10) DEFAULT NULL,
  `other` varchar(60) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`applyNumber`),
  KEY `FK_stuent_dormApplication` (`staffID`),
  CONSTRAINT `FK_stuent_dormApplication` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of officeapplication
-- ----------------------------
INSERT INTO `officeapplication` VALUES ('0001', '2017303213', '报修', '电脑进咖啡', '0', '2019-9-1');
INSERT INTO `officeapplication` VALUES ('0002', '2017303183', '人事', '与王生泽打架', '0', '2019-9-1');
INSERT INTO `officeapplication` VALUES ('0003', '2017303185', '人事', '与王子益打架', '1', '2019-9-1');
INSERT INTO `officeapplication` VALUES ('0004', '2017303177', '办公室调换', '部门调动调至研发部办公室', '0', '2019-8-30');
INSERT INTO `officeapplication` VALUES ('0005', '2017303186', '报修', 'VPN损坏无法上外网', '0', '2019-8-29');
INSERT INTO `officeapplication` VALUES ('0006', '2017303199', '报修', 'web工作环境无法配置', '0', '2019-8-31');

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `projectID` varchar(20) NOT NULL,
  `staffID` varchar(20) NOT NULL,
  `projectName` varchar(20) DEFAULT NULL,
  `projectMoney` int(11) DEFAULT NULL,
  PRIMARY KEY (`projectID`),
  KEY `FK_student_borrow` (`staffID`),
  CONSTRAINT `FK_student_borrow` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------

-- ----------------------------
-- Table structure for `staff`
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `staffID` varchar(30) NOT NULL,
  `officeNumber` varchar(20) NOT NULL,
  `staffName` varchar(20) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `identifyID` varchar(20) NOT NULL,
  `onJob` tinyint(1) DEFAULT NULL,
  `projectKind` varchar(10) DEFAULT NULL,
  `isAdmin` tinyint(1) DEFAULT NULL,
  `leaveDate` varchar(20) DEFAULT NULL,
  `groupID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`staffID`),
  KEY `FK_student_dorm` (`officeNumber`),
  CONSTRAINT `FK_student_dorm` FOREIGN KEY (`officeNumber`) REFERENCES `office` (`officeNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('201501', '04', '王一博', '男', '100000001', '1', '研发', '1', '2019-6-23', '01');
INSERT INTO `staff` VALUES ('201502', '05', '料此', '男', '200000002', '0', '实验室', '0', '2021-9-11', '02');
INSERT INTO `staff` VALUES ('201503', '05', '詹母期', '男', '300000003', '0', '实验室', '0', '2021-9-11', '03');
INSERT INTO `staff` VALUES ('201504', '04', '壮三持', '女', '400000004', '1', '研发', '0', '2021-9-11', '01');
INSERT INTO `staff` VALUES ('2017303168', '03', '全芷玥', '女', '199977777', '0', '后勤', '0', '2021-9-11', '03');
INSERT INTO `staff` VALUES ('2017303177', '02', '任卓力', '女', '199922222', '0', '实验室', '1', '2021-9-11', '02');
INSERT INTO `staff` VALUES ('2017303182', '03', '田嘉毅', '女', '199933333', '0', '后勤', '0', '2021-9-11', '03');
INSERT INTO `staff` VALUES ('2017303183', '01', '王子益', '男', '199944444', '1', '研发', '0', '2021-9-11', '01');
INSERT INTO `staff` VALUES ('2017303185', '02', '王生泽', '仙', '199999999', '0', '实验室', '0', '2021-9-11', '02');
INSERT INTO `staff` VALUES ('2017303186', '02', '许卓', '男', '199966666', '0', '实验室', '0', '2021-9-11', '02');
INSERT INTO `staff` VALUES ('2017303189', '03', '方亚群', '女', '199955555', '0', '后勤', '1', '2021-9-11', '03');
INSERT INTO `staff` VALUES ('2017303199', '04', '刘广厚', '女', '199988888', '0', '研发', '1', '2021-9-11', '01');
INSERT INTO `staff` VALUES ('2017303213', '01', '邓子凡', '男', '199911111', '0', '研发', '0', '2021-9-11', '01');
