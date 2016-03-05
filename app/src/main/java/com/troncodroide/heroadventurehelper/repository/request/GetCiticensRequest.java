package com.troncodroide.heroadventurehelper.repository.request;

public class GetCiticensRequest {
    private String town;

    public GetCiticensRequest(String town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "GetCiticensRequest{" +
                "town='" + town + '\'' +
                '}';
    }

    public String getTown() {
        return town;
    }
}
