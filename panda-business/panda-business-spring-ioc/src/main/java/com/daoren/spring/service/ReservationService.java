package com.daoren.spring.service;

import com.daoren.spring.enums.SportType;

/**
 * @author peng_da
 * @date 2022/12/1 9:51
 */
public interface ReservationService {
    /**
     * 获取 {@link SportType}
     *
     * @param typeId : typeId
     * @return com.daoren.spring.enums.SportType
     * @author peng_da
     * @since 2022/12/1 9:52
     */
    SportType getSportType(int typeId);
}
