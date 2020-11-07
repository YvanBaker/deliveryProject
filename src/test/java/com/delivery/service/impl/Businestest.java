package com.delivery.service.impl;

import com.delivery.entity.BusinessNote;
import com.delivery.service.BusinessNoteService;
import com.delivery.util.DateTimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author wenJ
 * @Description testBusiness
 * @Classname Business
 * @Date 2020/11/2 0:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Businestest {
    @Resource
    BusinessNoteService businessNoteService;

    @Test
    public void Busines() throws ParseException {
        BusinessNote businessNote = new BusinessNote();
        //用Date (util)可直接存tatetime类型，String类型的符合"yyyy-MM-dd hh:mm:ss"格式的也可以存入
        //Date parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).parse("2011-11-01 11:33:22");
        //businessNote.setProDate("2011.11.01 11/33/22");//"2011.11.01","2011-11-01"或"2011/11/01"存入datetime值显示时间2011-11-01 00:00:00
        businessNoteService.addbusinessNote(businessNote);
    }
}
