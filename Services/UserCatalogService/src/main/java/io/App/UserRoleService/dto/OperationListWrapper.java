package io.App.UserRoleService.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.App.UserRoleService.roleComponent.Operation;

public class OperationListWrapper {

    @JsonProperty("listOperations")
    private ArrayList<Operation> list;


    public OperationListWrapper() {
        //For REST Only
    }

    public ArrayList<Operation> getList() {
        return list;
    }

    public void setList(ArrayList<Operation> someList) {
        this.list = someList;
    }
}
