package com.example.VendingMachine;

public class Currency {
    CurrencyType currencyType;
    int denomination;

    public Currency(CurrencyType currencyType, int denomination) {
        this.currencyType = currencyType;
        this.denomination = denomination;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }
    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }
}
