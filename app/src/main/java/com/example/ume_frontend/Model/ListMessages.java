package com.example.ume_frontend.Model;

import java.util.List;

public class ListMessages
{
    public static class Data
    {
        private String idUser;

        private String code;

        private String idMessage;

        private String IisGim;

        private Data viewOn;

        private String createOn;

        private String isActive;

        private String toUserId;

        private String content;

        private Data status;

        public Data() {
        }

        public Data(String idUser, String code, String idMessage, String iisGim, ListMessages.Data viewOn, String createOn, String isActive, String toUserId, String content, ListMessages.Data status) {
            this.idUser = idUser;
            this.code = code;
            this.idMessage = idMessage;
            IisGim = iisGim;
            this.viewOn = viewOn;
            this.createOn = createOn;
            this.isActive = isActive;
            this.toUserId = toUserId;
            this.content = content;
            this.status = status;
        }

        public String getIdUser ()
        {
            return idUser;
        }

        public void setIdUser (String idUser)
        {
            this.idUser = idUser;
        }

        public String getCode ()
        {
            return code;
        }

        public void setCode (String code)
        {
            this.code = code;
        }

        public String getIdMessage ()
        {
            return idMessage;
        }

        public void setIdMessage (String idMessage)
        {
            this.idMessage = idMessage;
        }

        public String getIisGim ()
        {
            return IisGim;
        }

        public void setIisGim (String IisGim)
        {
            this.IisGim = IisGim;
        }

        public Data getViewOn ()
    {
        return viewOn;
    }

        public void setViewOn (Data viewOn)
        {
            this.viewOn = viewOn;
        }

        public String getCreateOn ()
        {
            return createOn;
        }

        public void setCreateOn (String createOn)
        {
            this.createOn = createOn;
        }

        public String getIsActive ()
        {
            return isActive;
        }

        public void setIsActive (String isActive)
        {
            this.isActive = isActive;
        }

        public String getToUserId ()
        {
            return toUserId;
        }

        public void setToUserId (String toUserId)
        {
            this.toUserId = toUserId;
        }

        public String getContent ()
        {
            return content;
        }

        public void setContent (String content)
        {
            this.content = content;
        }

        public Data getStatus ()
    {
        return status;
    }

        public void setStatus (Data status)
        {
            this.status = status;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [idUser = "+idUser+", code = "+code+", idMessage = "+idMessage+", IisGim = "+IisGim+", viewOn = "+viewOn+", createOn = "+createOn+", isActive = "+isActive+", toUserId = "+toUserId+", content = "+content+", status = "+status+"]";
        }
    }
    private String Message;

    private List<Data> Data;

    public String getMessage ()
    {
        return Message;
    }

    public void setMessage (String Message)
    {
        this.Message = Message;
    }

    public List<Data> getData ()
    {
        return Data;
    }

    public void setData (List<Data> Data)
    {
        this.Data = Data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Message = "+Message+", Data = "+Data+"]";
    }
}
