package com.zerg.tiamat.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author : xuyang
 * @date : 2019-11-21 01:05
 */

public class StatementDTO implements Serializable {
    private List<MarketDataDTO> market;
    private PropertyDTO property;
    private ExtraDTO extra;

    public StatementDTO() {
    }

    public List<MarketDataDTO> getMarket() {
        return market;
    }

    public void setMarket(List<MarketDataDTO> market) {
        this.market = market;
    }

    public PropertyDTO getProperty() {
        return property;
    }

    public void setProperty(PropertyDTO property) {
        this.property = property;
    }

    public ExtraDTO getExtra() {
        return extra;
    }

    public void setExtra(ExtraDTO extra) {
        this.extra = extra;
    }
}
