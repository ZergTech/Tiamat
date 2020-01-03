package com.zerg.tiamat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author : xuyang
 * @date : 2019-12-25 11:16
 */

@Data
@NoArgsConstructor
public class AlarmDTO {
    private String email;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal shocks;
}
