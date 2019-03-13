package com.example.bysj_1.moduls.response;

/**
 * 实验室信息
 */
public class Laboratory {
    /**
     * 实验室编号
     */
    private String laboratory_no;
    /**
     * 实验室名称
     */
    private String laboratory_name;
    /**
     * 实验室类型
     */
    private String laboratory_type;
    /**
     * 实验室可容纳人数
     */
    private int person_num;

    public String getLaboratory_no() {
        return laboratory_no;
    }

    public void setLaboratory_no(String laboratory_no) {
        this.laboratory_no = laboratory_no;
    }

    public String getLaboratory_name() {
        return laboratory_name;
    }

    public void setLaboratory_name(String laboratory_name) {
        this.laboratory_name = laboratory_name;
    }

    public String getLaboratory_type() {
        return laboratory_type;
    }

    public void setLaboratory_type(String laboratory_type) {
        this.laboratory_type = laboratory_type;
    }

    public int getPerson_num() {
        return person_num;
    }

    public void setPerson_num(int person_num) {
        this.person_num = person_num;
    }
}
