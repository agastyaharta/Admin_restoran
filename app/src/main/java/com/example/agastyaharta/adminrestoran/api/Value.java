package com.example.agastyaharta.adminrestoran.api;

import com.example.agastyaharta.adminrestoran.model.Admin;
import com.example.agastyaharta.adminrestoran.model.Hidangan;
import java.util.List;

public class Value {
    Boolean success;
    List<Hidangan> hidangan;
    List<Admin> admin;

    public Boolean getSuccess() { return success; }

    public List<Hidangan> getHidangan() { return hidangan; }

    public List<Admin> getAdmin() { return admin; }
}
