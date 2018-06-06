package com.drrepository.main.model;

public class CoinModel {

    /**
     * id : 1
     * name : Bitcoin
     * symbol : BTC
     * website_slug : bitcoin
     * rank : 1
     * circulating_supply : 1.7078075E7
     * total_supply : 1.7078075E7
     * max_supply : 2.1E7
     * quotes : {"USD":{"price":7636.85,"volume_24h":4.71862E9,"market_cap":1.30422697064E11,"percent_change_1h":0.37,"percent_change_24h":2.7,"percent_change_7d":1.61}}
     * last_updated : 1528267474
     */

    private int id;
    private String name;
    private String symbol;
    private String website_slug;
    private int rank;
    private double circulating_supply;
    private double total_supply;
    private double max_supply;
    private QuotesBean quotes;
    private long last_updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getWebsite_slug() {
        return website_slug;
    }

    public void setWebsite_slug(String website_slug) {
        this.website_slug = website_slug;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(double circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public double getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(double total_supply) {
        this.total_supply = total_supply;
    }

    public double getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(double max_supply) {
        this.max_supply = max_supply;
    }

    public QuotesBean getQuotes() {
        return quotes;
    }

    public void setQuotes(QuotesBean quotes) {
        this.quotes = quotes;
    }

    public long getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(long last_updated) {
        this.last_updated = last_updated;
    }

    public static class QuotesBean {
        /**
         * USD : {"price":7636.85,"volume_24h":4.71862E9,"market_cap":1.30422697064E11,"percent_change_1h":0.37,"percent_change_24h":2.7,"percent_change_7d":1.61}
         */

        private USDBean USD;

        public USDBean getUSD() {
            return USD;
        }

        public void setUSD(USDBean USD) {
            this.USD = USD;
        }

        public static class USDBean {
            /**
             * price : 7636.85
             * volume_24h : 4.71862E9
             * market_cap : 1.30422697064E11
             * percent_change_1h : 0.37
             * percent_change_24h : 2.7
             * percent_change_7d : 1.61
             */

            private double price;
            private double volume_24h;
            private double market_cap;
            private double percent_change_1h;
            private double percent_change_24h;
            private double percent_change_7d;

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getVolume_24h() {
                return volume_24h;
            }

            public void setVolume_24h(double volume_24h) {
                this.volume_24h = volume_24h;
            }

            public double getMarket_cap() {
                return market_cap;
            }

            public void setMarket_cap(double market_cap) {
                this.market_cap = market_cap;
            }

            public double getPercent_change_1h() {
                return percent_change_1h;
            }

            public void setPercent_change_1h(double percent_change_1h) {
                this.percent_change_1h = percent_change_1h;
            }

            public double getPercent_change_24h() {
                return percent_change_24h;
            }

            public void setPercent_change_24h(double percent_change_24h) {
                this.percent_change_24h = percent_change_24h;
            }

            public double getPercent_change_7d() {
                return percent_change_7d;
            }

            public void setPercent_change_7d(double percent_change_7d) {
                this.percent_change_7d = percent_change_7d;
            }
        }
    }
}
