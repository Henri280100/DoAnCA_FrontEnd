package com.example.ume_frontend.Model;


import java.io.Serializable;
import java.util.List;

public class ListUser
    {
        public ListUser(String message, List<Data> data) {
            this.data = data;
            this.message = message;
        }

        public class Data implements Serializable
        {
            public Data(String birthDay, String code, String sex, String urlAvarta, String isOnline, String isActive, String userName, String idUser, String password, String phoneNumber, String createOn, String updateOn, String email) {
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

            private String birthDay;

            private String code;

            private String sex;

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

            public String getBirthDay ()
        {
            return  birthDay;
        }

            public void setBirthDay (String birthDay)
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

            public String getSex ()
            {
                return sex;
            }

            public void setSex (String sex)
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
                return "ClassPojo [birthDay = "+birthDay+", code = "+code+"," +
                        " sex = "+sex+", urlAvarta = "+urlAvarta+", " +
                        "isOnline = "+isOnline+", isActive = "+isActive+", " +
                        "userName = "+userName+", idUser = "+idUser+", " +
                        "password = "+password+", phoneNumber = "+phoneNumber+", " +
                        "createOn = "+createOn+", updateOn = "+updateOn+", email = "+email+"]";
            }
        }
        private List<Data> data;

        private String message;

        public List<Data> getData ()
        {
            return data;
        }

        public void setData (List<Data> data)
        {
            this.data = data;
        }

        public String getMessage ()
        {
            return message;
        }

        public void setMessage (String message)
        {
            this.message = message;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [data = "+data+", message = "+message+"]";
        }

}
