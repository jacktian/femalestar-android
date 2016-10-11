package cn.st.android.femalestar.data;

import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by coolearth on 16-10-11.
 */

public class Product {
    private String name;
    private String code;
    private Date pDate;
    private Date eDate;

    private String pDateStr;

    private String eDateStr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setpDate(Date pDate) {
        this.pDate = pDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public Product(String name, String code, Date pDate, Date eDate) {
        this.name = name;
        this.code = code;
        this.pDate = pDate;
        this.eDate = eDate;
    }

    public String getpDateStr() {
        return DateFormat.format("yyyy-MM-dd",pDate).toString();
    }

    public String geteDateStr() {
        return DateFormat.format("yyyy-MM-dd",eDate).toString();
    }
}
