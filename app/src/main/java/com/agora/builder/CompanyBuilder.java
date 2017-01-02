package com.agora.builder;

import com.agora.entity.Category;
import com.agora.entity.Company;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ivan on 6/10/15.
 */
public class CompanyBuilder implements Builder {
    @Override
    public Object build(JSONObject o) throws JSONException {
        Long key=Long.valueOf(o.getString("companyKey"));
        String address= o.getString("address");
        String phone= o.getString("phone");
        String rut= o.getString("rut");
        String email= o.optString("email");
        String webpage= o.optString("webpage");
        String name= o.getString("companyName");
        String country= o.getString("country");
        String city= o.getString("city");
        String image= o.optString("logo");
        Company company= new Company();
        company.setLogo(image);
        company.setCompanyName(name);
        company.setAddress(address);
        company.setWebpage(webpage);
        company.setEmail(email);
        company.setRut(rut);
        company.setPhone(phone);
        company.setCountry(country);
        company.setCity(city);
        company.setCompanyKey(key);
        return company;
    }
}
