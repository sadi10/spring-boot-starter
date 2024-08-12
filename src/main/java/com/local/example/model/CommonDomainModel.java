package com.local.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonDomainModel<T> {


    private int pageNo;
    private int pageSize;
    private int totalElements;
    //private Object  data;
    T data;


}
