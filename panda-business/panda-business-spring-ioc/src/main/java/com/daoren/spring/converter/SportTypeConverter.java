package com.daoren.spring.converter;

import com.daoren.spring.enums.SportType;
import com.daoren.spring.service.ReservationService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

/**
 * int to {@link SportType} 转换器
 *
 * @author peng_da
 * @date 2022/12/1 9:57
 */
public class SportTypeConverter implements Converter<String, SportType> {
    private final ReservationService reservationService;

    public SportTypeConverter(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public SportType convert(@NonNull String source) {
        final int sportTypeId = Integer.parseInt(source);
        return reservationService.getSportType(sportTypeId);
    }
}
