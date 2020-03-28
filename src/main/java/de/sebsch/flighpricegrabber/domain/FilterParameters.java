package de.sebsch.flighpricegrabber.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FilterParameters {

    private Integer limit;

    private SortType sortType;

    private Boolean sortInAscendingOrder;

    public String toUriParameters() {
        return "limit" + "=" + limit.toString() + "&" +
                "sortType" + "=" + sortType.toString() + "&" +
                "asc" + "=" + (sortInAscendingOrder ? 1 : 0);
    }

    public enum SortType {
        DURATION, QUALITY, DATE, PRICE
    }


}
