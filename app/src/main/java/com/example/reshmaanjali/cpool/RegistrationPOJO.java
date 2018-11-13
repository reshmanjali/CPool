package com.example.reshmaanjali.cpool;

import java.io.Serializable;

public class RegistrationPOJO {
    public String name;//=nameReg.getText().toString();
    public String profsn;//=profsnReg.getText().toString();
    public String clgid;//=clgIdReg.getText().toString();
   public String phnNo;//=pnNoReg.getText().toString();
    public String adhr;//=adhrReg.getText().toString();
    public String mail;//=mailReg.getText().toString();
    public String pwd;//=pwdReg.getText().toString();

    RegistrationPOJO(){

    }
    public RegistrationPOJO(String name, String profsn, String clgid, String phnNo, String adhr, String mail, String pwd) {
        this.name = name;
        this.profsn = profsn;
        this.clgid = clgid;
        this.phnNo = phnNo;
        this.adhr = adhr;
        this.mail = mail;
        this.pwd = pwd;
    }

}
