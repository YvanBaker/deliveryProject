package com.delivery.util;

import com.delivery.entity.BusinessNote;
import com.delivery.service.impl.BusinessNoteServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        /*BusinessNoteServiceImpl businessNoteService = new BusinessNoteServiceImpl();
        BusinessNote businessNote = new BusinessNote();
        businessNoteService.addbusinessNote(businessNote);*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //Date date = new Date(new java.util.Date().getTime());
        //String format = simpleDateFormat.format(date);
        Date time = DateTimeUtil.StringToDateTime("1977-22-01 11:33:22");
        System.out.println("time = " + time);

    }
}
