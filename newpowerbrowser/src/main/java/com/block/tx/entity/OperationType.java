package com.block.tx.entity;

public enum OperationType {
    //用户相关
    CREATE_USER(0, TxOperationContent.USERREGISTER, "createUser"),
    //企业相关
    CREATE_ENTERPRISE(1, TxOperationContent.PASSENTERPRISEAUDIT, "createEnterprise"),
    UPDATE_ENTERPRISE(2, TxOperationContent.PASSENTERPRISEUPDATEAUDIT, "updateEnterprise"),
    //项目相关
    CREATE_ASSET(3, TxOperationContent.PASSPROJECTAUDIT, "createAsset"),
    UPDATE_ASSET(4, TxOperationContent.PASSPROJECTUPDATEAUDIT, "updateAsset"),
    UPDATE_DAILY_POWER_GENERATION(9, TxOperationContent.COLLECTPOWERGENERATION, "dailyPowerGeneration"),
    //合约相关
    CREATE_DATA_CONTRACT(5, TxOperationContent.STARTDATACONTRACT, "createContract"),
    CREATE_SERVICE_CONTRACT(6, TxOperationContent.STARTSERVICECONTRACT, "createContract"),
    CREATE_BUSINESS_CONTRACT(7, TxOperationContent.STARTBUSINESSCONTRACT, "createContract"),
    EXECUTE_CONTRACT(8, TxOperationContent.EXECCONTRACT, "executeContract"),
    //系统相关
    SYSTEM_DEPLOY(10,TxOperationContent.SYSTEMDEPLOY,"systemDeploy")
    ;


    Integer index;
    String content;
    String operationType;

    OperationType(Integer index, String content, String operationType) {
        this.index = index;
        this.content = content;
        this.operationType = operationType;
    }

    public static Boolean isBusinessOperation(String operation) {
        for (OperationType operationType : OperationType.values()) {
            if (operation.equals(operationType.getOperationType())) {
                return true;
            }
        }
        return false;
    }

    public static Boolean isProjectOperation(OperationType operationType) {
        if (operationType == CREATE_ASSET||operationType==UPDATE_ASSET||operationType==UPDATE_DAILY_POWER_GENERATION){
            return true;
        }
        return false;
    }

    public static String getOperatorType(OperationType operationType){
        if (operationType == CREATE_ASSET||operationType==UPDATE_ASSET||operationType==UPDATE_DAILY_POWER_GENERATION){
            return "project";
        }
        if (operationType == CREATE_USER){
            return "user";
        }
        if (operationType == CREATE_ENTERPRISE||operationType==UPDATE_ENTERPRISE){
            return "enterPrise";
        }
        if (operationType == CREATE_DATA_CONTRACT||operationType==CREATE_SERVICE_CONTRACT||operationType==CREATE_BUSINESS_CONTRACT||operationType==EXECUTE_CONTRACT){
            return "contract";
        }
        if (operationType==SYSTEM_DEPLOY){
            return "system";
        }
        return null;
    }

    public Integer getIndex() {
        return index;
    }

    public String getContent() {
        return content;
    }

    public String getOperationType() {
        return operationType;
    }

}
