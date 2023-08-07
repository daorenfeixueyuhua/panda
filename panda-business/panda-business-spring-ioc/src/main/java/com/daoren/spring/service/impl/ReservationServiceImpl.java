package com.daoren.spring.service.impl;

import com.daoren.spring.enums.SportType;
import com.daoren.spring.service.ReservationService;
import org.springframework.stereotype.Service;

/**
 * @author peng_da
 * @date 2022/12/1 10:02
 */
@Service
public class ReservationServiceImpl implements ReservationService {
    @Override
    public SportType getSportType(int typeId) {
        switch (typeId) {
            case 1:
                return SportType.TENNIS;
            case 2:
                return SportType.SOCCER;
            default:
                return null;
        }
    }
}
