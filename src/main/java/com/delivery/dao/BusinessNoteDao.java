package com.delivery.dao;

import com.delivery.entity.BusinessNote;
import org.springframework.stereotype.Repository;

/**
 * @author wenJ
 * @Description
 * @Classname BusinessNoteDao
 * @Date 2020/11/1 23:24
 */
@Repository
public interface BusinessNoteDao {
    boolean businessNoteAdd(BusinessNote businessNote);
}
