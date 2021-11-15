package com.example.ume_frontend.Model;

import java.io.Serializable;
import java.util.Date;

public class UserModel {
    private String message;

    private Account account;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    @Override
    public String toString() {
        return "ClassPojo [message = " + message + ", account = " + account + "]";
    }

    public static class Account implements Serializable{

        public void Account() {
        }

        private Date birthDay;

        private String code;

        private Boolean sex;

        private String urlAvarta;

        private String isOnline;

        private String isActive;

        private String userName;

        private String idUser;

        private String password;

        private String phoneNumber;

        private String createOn;

        private String updateOn;

        private String email;

        public Date getBirthDay ()
        {
            return birthDay;
        }

        public Account(Date birthDay, String code, Boolean sex, String urlAvarta, String isOnline, String isActive, String userName, String idUser, String password, String phoneNumber, String createOn, String updateOn, String email) {
            this.birthDay = birthDay;
            this.code = code;
            this.sex = sex;
            this.urlAvarta = urlAvarta;
            this.isOnline = isOnline;
            this.isActive = isActive;
            this.userName = userName;
            this.idUser = idUser;
            this.password = password;
            this.phoneNumber = phoneNumber;
            this.createOn = createOn;
            this.updateOn = updateOn;
            this.email = email;
        }

        public void setBirthDay (Date birthDay)
        {
            this.birthDay = birthDay;
        }

        public String getCode ()
        {
            return code;
        }

        public void setCode (String code)
        {
            this.code = code;
        }

        public Boolean getSex ()
        {
            return sex;
        }

        public void setSex (Boolean sex)
        {
            this.sex = sex;
        }

        public String getUrlAvarta ()
        {
            return urlAvarta;
        }

        public void setUrlAvarta (String urlAvarta)
        {
            this.urlAvarta = urlAvarta;
        }

        public String getIsOnline ()
        {
            return isOnline;
        }

        public void setIsOnline (String isOnline)
        {
            this.isOnline = isOnline;
        }

        public String getIsActive ()
        {
            return isActive;
        }

        public void setIsActive (String isActive)
        {
            this.isActive = isActive;
        }

        public String getUserName ()
        {
            return userName;
        }

        public void setUserName (String userName)
        {
            this.userName = userName;
        }

        public String getIdUser ()
        {
            return idUser;
        }

        public void setIdUser (String idUser)
        {
            this.idUser = idUser;
        }

        public String getPassword ()
        {
            return password;
        }

        public void setPassword (String password)
        {
            this.password = password;
        }

        public String getPhoneNumber ()
        {
            return phoneNumber;
        }

        public void setPhoneNumber (String phoneNumber)
        {
            this.phoneNumber = phoneNumber;
        }

        public String getCreateOn ()
        {
            return createOn;
        }

        public void setCreateOn (String createOn)
        {
            this.createOn = createOn;
        }

        public String getUpdateOn ()
        {
            return updateOn;
        }

        public void setUpdateOn (String updateOn)
        {
            this.updateOn = updateOn;
        }

        public String getEmail ()
        {
            return email;
        }

        public void setEmail (String email)
        {
            this.email = email;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [birthDay = "+birthDay+", code = "+code+", sex = "+sex+", urlAvarta = "+urlAvarta+", isOnline = "+isOnline+", isActive = "+isActive+", userName = "+userName+", idUser = "+idUser+", password = "+password+", phoneNumber = "+phoneNumber+", createOn = "+createOn+", updateOn = "+updateOn+", email = "+email+"]";
        }
    }
}
