package ru.job4j.cars.repository;

public enum PostHQLs {

    LAST_DAY_POSTS("FROM Post p WHERE p.created >= NOW() - INTERVAL '24 HOURS'"),
    POSTS_WITH_PHOTO("FROM Post WHERE photo IS NOT NULL"),
    POSTS_BY_BRAND("FROM Post as post LEFT JOIN Car as car WITH car.model = :fModel");


    private String hql;

    PostHQLs(String hql) {
        this.hql = hql;
    }

    public String getHql() {
        return hql;
    }

    public void setHql(String hql) {
        this.hql = hql;
    }
}
