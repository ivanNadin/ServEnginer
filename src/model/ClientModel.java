package model;

/**
 * Created by ivan_ on 27.06.2017.
 */
public class ClientModel {
    private int id;
    private String name_c;
    private String adress_c;
    private String contPerson;
    private String phoneNum;

    public void setName_c(String name_c){
        this.name_c=name_c;
    }

    public void setAdress_c(String adress_c){
        this.adress_c=adress_c;
    }

    public void setContPerson(String contPerson){
        this.contPerson=contPerson;
    }

    public void setPhoneNum(String phoneNum){
        this.phoneNum=phoneNum;
    }

    public String getName_c(){
        return name_c;
    }

    public String getAdress_c(){
        return adress_c;
    }

    public String getContPerson(){
        return contPerson;
    }

    public String getPhoneNum(){
        return phoneNum;
    }

}
