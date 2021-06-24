package cn.itcast.callrecord.bean;

public class Phone {
    private int id;
    private String phone;
    private String classify;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Phone(String phone, String classify, String date) {
        super();
        this.phone = phone;
        this.classify = classify;
        this.date = date;
    }

    public Phone(String phone, String date) {
        super();
        this.phone = phone;
        this.date = date;
    }

    public Phone() {

    }
}