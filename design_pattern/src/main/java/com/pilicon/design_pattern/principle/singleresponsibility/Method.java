package com.pilicon.design_pattern.principle.singleresponsibility;

public class Method {
    private void updateUserInfo(String userName,String address){
        userName = "Geely";
        address = "北京";
    }

    //将上面的两个更新方法拆分成两个更新
    private void updateUserName(String userName){
        userName = "Geely";
    }

    private void updateAddress(String address){
        address = "北京";
    }

}
