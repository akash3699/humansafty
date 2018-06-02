package com.pdfupload.example.dell.humansafty;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by HP on 3/15/2018.
 */

public class locationmodel {
    String id, latitude, longitude;
    private boolean isPresent;
    public locationmodel(JSONObject jsonObject) {
        try {
            this.id=""+jsonObject.getString("id");
            this.latitude=""+jsonObject.getString("Latitude");
            this.longitude=""+jsonObject.getString("Longitude");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.isPresent=false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


}
