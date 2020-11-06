package com.delivery.dao;

import com.delivery.entity.BusinessNote;
import org.apache.ibatis.annotations.Param;
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

    BusinessNote AssociationsOrderOnDbl(int id);

    List<BusinessNote> getNoAssociationsOrder();

    List<BusinessNote> getHasAssociationsOrder();

    boolean setStaffById(@Param("uuid") String uuid,@Param("id1") int id1);

    BusinessNote getBusinessNotesByUuid(@Param("orderUuid") String orderUuid);

    boolean setBusinessNoteStaffIsNull();
}
