package com.project.ems.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MasterDto {
    private boolean status;

    private String message;

    private Object Data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public MasterDto(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        Data = data;
    }

    public MasterDto() {
    }
}
