package com.magiccode.myotp;

import com.google.gson.annotations.SerializedName;

public class Mensagem {
    @SerializedName("cell")
    private String cell;

    @SerializedName("message")
    private String message;

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
