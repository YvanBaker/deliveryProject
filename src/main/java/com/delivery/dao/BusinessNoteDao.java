package com.delivery.dao;

import com.delivery.entity.BusinessNote;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wenJ
 * @Description
 * @Classname BusinessNoteDao
 * @Date 2020/11/1 23:24
 */
@Repository
public interface BusinessNoteDao {
    boolean businessNoteAdd(BusinessNote businessNote);

    List<BusinessNote> getBusinessNotes();

    List<BusinessNote> AssociationsOrderOnDbl(int id);

    List<BusinessNote> getNoAssociationsOrder();

    List<BusinessNote> getHasAssociationsOrder();

}
