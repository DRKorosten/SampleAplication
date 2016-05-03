package sample.model;

import sample.dataBase.BDConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MaxWeightData {
      private User user;
      private LinkedHashMap<String,Double> data;
      private final static int TOTAL = 16;

    public MaxWeightData(User user, ArrayList<String> exNames){
        this.user = user;
        data = new LinkedHashMap<>();
        String sql = "SELECT * FROM public.\"MaxWeights\" WHERE \"user_id\"="+user.getID()+";";
        try {
            ResultSet resultSet= BDConnection.createSelectQuery(sql);
            resultSet.next();
            for (int i = 0; i < TOTAL; i++) {
              data.put(exNames.get(i),resultSet.getDouble("ex"+(i+1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public LinkedHashMap<String, Double> getData() {
        return data;
    }
    public User getUser(){
        return user;
    }

}
