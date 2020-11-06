package com.delivery.service;

import com.delivery.entity.BusinessNote;
import com.delivery.entity.QpWorkorder;

import java.util.List;

/**
 * @author wenJ
 * @Description
 * @Classname BusinessNoteService
 * @Date 2020/11/1 23:22
 */

public interface BusinessNoteService {
    boolean addbusinessNote(BusinessNote businessNote);

    List<BusinessNote> getBusinessNote();

    List<BusinessNote> AssociationsOrderOnDbl(String id);

    List<BusinessNote> getNoAssociationsOrder();

    List<BusinessNote> getHasAssociationsOrder(String did);

}
