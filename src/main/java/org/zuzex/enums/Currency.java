package org.zuzex.enums;

public enum Currency {
    USD("$"),
    RUB("₽"),
    EUR("€"),
    JPY("¥"),
    GBP("£"),
    CAD("C$"),
    HKD("HK$"),
    CHF("₣");

    public final String name;

    Currency(String operation) {
        this.name = operation;
    }


    /**
     * @param name of currency
     * @return value of currency
     */
    public static Currency valueOfCurrency(String name) {
        for (Currency e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return null;
    }
}
