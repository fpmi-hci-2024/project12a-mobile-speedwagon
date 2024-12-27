package com.shashank.platform.busbookingappui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {
    // Города
    public static final List<String> cities = new ArrayList<>();

    // Маршруты (ключ - пара "откуда-куда", значение - список маршрутов)
    public static final HashMap<String, List<Route>> routes = new HashMap<>();

    // Статический блок для инициализации данных
    static {
        // Добавляем города
        cities.add("Минск");
        cities.add("Гомель");
        cities.add("Брест");
        cities.add("Гродно");
        cities.add("Могилев");

        // Добавляем маршруты
        addRoute("Минск", "Гомель", new Route("8:00", "12:00", 20, 15));
        addRoute("Минск", "Гомель", new Route("10:00", "14:00", 15, 10));
        addRoute("Минск", "Брест", new Route("9:00", "13:00", 25, 20));
        addRoute("Гродно", "Могилев", new Route("7:00", "12:00", 30, 10));
        addRoute("Брест", "Гомель", new Route("6:00", "11:00", 30, 15));
    }

    // Метод для добавления маршрута
    private static void addRoute(String from, String to, Route route) {
        String key = from + "-" + to; // Ключ-фраза "Откуда-Куда"
        if (!routes.containsKey(key)) {
            routes.put(key, new ArrayList<>());
        }
        routes.get(key).add(route);
    }

    // Класс маршрута
    public static class Route {
        public String departureTime;
        public String arrivalTime;
        public int price; // Цена за место
        public int availableSeats; // Количество свободных мест

        public Route(String departureTime, String arrivalTime, int price, int availableSeats) {
            this.departureTime = departureTime;
            this.arrivalTime = arrivalTime;
            this.price = price;
            this.availableSeats = availableSeats;
        }
    }
}

