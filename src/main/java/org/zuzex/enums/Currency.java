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

    public static Currency valueOfOperation(String name) {
        for (Currency e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return null;
    }
}
