package com.msg;

public enum IsUpdate {
    TRUE,
    FALSE;

    public static Boolean  alreadyUpdate(IsUpdate isUpdate) {
        switch (isUpdate) {
            case TRUE:
                return true;
            case FALSE:
                return false;
        }
        return false;
    }
}
