/*用户表*/
create table userInfo
(
    username VARCHAR(32)  COMMENT '用户姓名',
    userId   VARCHAR(32) not null  COMMENT '用户账号',  /*登陆账号*/
    userPhone VARCHAR(32)  COMMENT '联系电话',
    password VARCHAR(32)  COMMENT '用户密码',
    forgetQue  VARCHAR(32)  COMMENT '忘记密码问题',
    forgetAns  VARCHAR(32)  COMMENT '忘记密码答案',
    shopName  VARCHAR(32)  COMMENT '店铺名称',
    rank VARCHAR(32)  COMMENT '等级',
    createTime VARCHAR(32)   COMMENT '创建时间',
    creator VARCHAR(32)  COMMENT '创建人',
    Id  VARCHAR(32) not null COMMENT 'ID',
    
    PRIMARY KEY (userId)  
) COMMENT='用户表'

/*客户表*/

create table customer
(

    userId   VARCHAR(32) not null  COMMENT '关联用户账号',
    customerId VARCHAR(32) not null  COMMENT '客户ID',
	customerName VARCHAR(32)  COMMENT '客户姓名',
	customerPhone VARCHAR(32)  COMMENT '联系电话',
	customerAddress VARCHAR(32)  COMMENT '客户地址',
	createTime VARCHAR(32)  COMMENT '创建时间',
    memo VARCHAR(32)  COMMENT '信息描述',
    sum INT(16)  COMMENT '金额',
    creator VARCHAR(32)  COMMENT '创建人',
    customerSataus VARCHAR(32)  COMMENT '状态', 
    goodsId  VARCHAR(32) COMMENT '关联商品ID外键',
    PRIMARY KEY (customerId)  
) COMMENT='客户表';

/*商品出入库表*/
create table goods
(

	userId   VARCHAR(32)   COMMENT '关联用户账号',
	goodsId VARCHAR(32) not null   COMMENT '商品ID',
	tradeName VARCHAR(32)  COMMENT '商品名称',
	identifier VARCHAR(64)   COMMENT '流水号',
	goodFrom VARCHAR(32)   COMMENT '来源',
	location VARCHAR(32)   COMMENT '所在位置',
	createTime VARCHAR(32)  COMMENT '创建时间',
	creator VARCHAR(32)  COMMENT '创建人',
    updater VARCHAR(32)  COMMENT '更新人',
    price INT(16)  COMMENT '单价',
    goodsInfoId VARCHAR(32)  COMMENT '商品信息表Id',
    quantity  INT(16)  COMMENT '数量',
    sum  INT(16)  COMMENT '总价',
    statusToString VARCHAR(32)  COMMENT '状态',
    status VARCHAR(32)  COMMENT '状态',  /*  区分是入库出库--come out*/
    customerId VARCHAR(32)  COMMENT '关联客户ID',
    wholesalerId VARCHAR(32)  COMMENT '关联批发商Id',
    wholesalerName VARCHAR(32)  COMMENT '关联批发商名称',
    customerName VARCHAR(32)  COMMENT '关联客户名称',

   PRIMARY KEY (goodsId)  
) COMMENT='商品记录表';
/*收入表*/
create table income
(   
    userId   VARCHAR(32) not null  COMMENT '关联用户账号',
    createTime VARCHAR(32)  COMMENT '创建时间',
    title VARCHAR(32)  COMMENT '标题',
    sum INT(16)  COMMENT '金额',
    memo VARCHAR(32)  COMMENT '描述',
    creator VARCHAR(32)  COMMENT '创建人',
    updater VARCHAR(32)  COMMENT '更新人',
    incomeId VARCHAR(32)  COMMENT '收入ID',
   /* profit INT(16)  COMMENT '利润',*/
PRIMARY KEY (incomeId) 
) COMMENT='支出表';

/*支出表*/
create table expend
(	

    userId   VARCHAR(32) not null  COMMENT '关联用户账号',
	createTime VARCHAR(32)  COMMENT '创建时间',
    sum INT(16)  COMMENT '金额',
    creator VARCHAR(32)  COMMENT '创建人',
    updater VARCHAR(32)  COMMENT '更新人',
    memo VARCHAR(32)  COMMENT '描述',
    espendId VARCHAR(32)  COMMENT '支出ID',
PRIMARY KEY (espendId) 
) COMMENT='支出表';
/*其他信息表*/
create table others
(	
    userId   VARCHAR(32) not null  COMMENT '关联用户账号',
	createTime VARCHAR(32)  COMMENT '创建时间',
	title VARCHAR(32)  COMMENT '标题',
    memo VARCHAR(32)  COMMENT '描述',
    tip  VARCHAR(32)  COMMENT '提示',
    creator VARCHAR(32)  COMMENT '创建人',
    updater VARCHAR(32)  COMMENT '更新人',
    othersPhone VARCHAR(32)  COMMENT '联系电话',  
    othersId VARCHAR(32)  COMMENT '其他信息表ID',
    statys VARCHAR(32)  COMMENT '状态',           /* 未读unread已读 read 垃圾箱 recycle*/
PRIMARY KEY (othersId) 

) COMMENT='代办信息';

create table Budget(
    userId   VARCHAR(32) not null  COMMENT '关联用户账号',
    createTime VARCHAR(32)  COMMENT '创建时间',
    inSum INT(16)  COMMENT '收入金额',
    outSum INT(16)  COMMENT '支出金额',
    creator VARCHAR(32)  COMMENT '创建人',
    updater VARCHAR(32)  COMMENT '更新人',
    memo VARCHAR(32)  COMMENT '描述',
    budgetId VARCHAR(32)  COMMENT '收支表ID',
    PRIMARY KEY (budgetId) 
)

create table goodsInfo(
    userId   VARCHAR(32) not null  COMMENT '关联用户账号',
    createTime VARCHAR(32)  COMMENT '创建时间',
    tradeName VARCHAR(32)  COMMENT '商品名称',
    type VARCHAR(32)  COMMENT '类别',
    profit INT(16)  COMMENT '利润',
    goodFrom VARCHAR(32)  COMMENT '来源',
    location VARCHAR(32)  COMMENT '存放位置',
    goodsInfoId VARCHAR(32)  COMMENT '商品信息Id',
    warmingNumber INT(16)  COMMENT '库存警告',
      number INT(16)  COMMENT '商品数量',
    PRIMARY KEY (goodsInfoId) 
)COMMENT='商品信息表';





create table wholesaler(
    userId   VARCHAR(32) not null  COMMENT '关联用户账号',
    createTime VARCHAR(32)  COMMENT '创建时间',
    wholesalerName VARCHAR(32)  COMMENT '批发商名称',
    linkMan VARCHAR(32)  COMMENT '联系人',
    phone VARCHAR(32)  COMMENT '联系电话',
    address VARCHAR(32)  COMMENT '联系地址',
    businessScope VARCHAR(32)  COMMENT '经营范围',
    memo VARCHAR(32)  COMMENT '备注信息',
    wholesalerId VARCHAR(32)  COMMENT 'ID',
    status VARCHAR(32)  COMMENT '状态-判断是客户还是批发商',
    PRIMARY KEY (wholesalerId) 
)COMMENT='批发商表';




create table RelationUserInfo(
    createTime VARCHAR(32) not null  COMMENT '时间',
    userId   VARCHAR(32) not null  COMMENT '关联用户账号',
    RelationUserInfoId VARCHAR(32)  COMMENT '子登录账户',
    password VARCHAR(32)  COMMENT '子登录密码',
    RelationUserInfoName VARCHAR(32)  COMMENT '用户子表姓名',
    level INT(16)  COMMENT '权限等级 0-1级，级数越高权限越高',
    goodslevel VARCHAR(32)  COMMENT '商品权限',
    readyDolevel VARCHAR(32)  COMMENT '待办权限',
    customerlevel VARCHAR(32)  COMMENT '客户权限',
    comeAndOutlevel VARCHAR(32)  COMMENT '收支权限',
    anaylyslevel VARCHAR(32)  COMMENT '数据分析权限',
    permissionlevel VARCHAR(32)  COMMENT '权限管理',
    id  VARCHAR(32)  COMMENT 'id标识',
    PRIMARY KEY (RelationUserInfoId)
)COMMENT='用户子表';