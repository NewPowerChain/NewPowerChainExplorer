package com.block.tx.entity;

public class TxOperationContent {
    //系统相关
    public static String SYSTEMDEPLOY = "合约链码";
    //合约相关
    public static String STARTDATACONTRACT = "sender调用智能合约向receiver发起数据请求";
    public static String STARTSERVICECONTRACT = "sender调用智能合约向receiver发起服务请求";
    public static String STARTBUSINESSCONTRACT = "sender调用智能合约向receiver发起业务请求";
    public static String EXECCONTRACT = "sender合约";
    //企业相关
    public static String PASSENTERPRISEAUDIT = "通过认证审核,完成企业信息存证";
    public static String PASSENTERPRISEUPDATEAUDIT = "更新企业信息通过审核,完成企业信息存证";
    //项目相关  (3通过物联设备采集发电量后 4项目方上传发票后)
    public static String PASSPROJECTAUDIT = "通过审核,完成项目信息存证";
    public static String PASSPROJECTUPDATEAUDIT = "通过更新审核,完成项目信息存证";
    public static String COLLECTPOWERGENERATION= "发电量存证";
    public static String UPLOADINVOICE = "日期发电量存证";
    //用户相关
    public static String USERREGISTER = "user在broadcaster完成用户注册";
    //交易相关    (认购成功  转让成功  合约完成)
    public static String SUBSCRIPTIONSUCCESS = "broadcaster播报1笔交易存证";
    public static String TRANSFERSUCCESS = "broadcaster播报1笔交易存证";
    public static String CONTRACTFINISH = "broadcaster播报1笔交易存证";
}
