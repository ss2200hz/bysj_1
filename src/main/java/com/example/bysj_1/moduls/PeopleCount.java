package com.example.bysj_1.moduls;

/**
 * 统计登录人数
 */
public class PeopleCount {
    private static PeopleCount peopleCount = new PeopleCount();
    public int loginCount = 0;
    public static PeopleCount getInstence(){
        return peopleCount;
    }
    private PeopleCount(){};
}
