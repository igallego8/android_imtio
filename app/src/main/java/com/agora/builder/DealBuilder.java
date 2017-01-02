package com.agora.builder;

import com.agora.entity.Category;
import com.agora.entity.Company;
import com.agora.entity.Deal;
import com.agora.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ivan on 6/10/15.
 */
public class DealBuilder implements Builder {
    @Override
    public Object build(JSONObject o) throws JSONException {
        Long key=Long.valueOf(o.getString("dealKey"));
        String status= o.getString("status");
        Long needKey= o.getLong("needKey");
        Long bidKey= o.getLong("bidKey");
        Deal deal= new Deal();
        deal.setDealKey(key);
        deal.setStatus(status);
       // deal.setBidKey(bidKey);
       // deal.setNeedKey(needKey);
        JSONObject jsonCompany= o.getJSONObject("company");
        Company company = (Company) BuilderFactory.getBuilder(CompanyBuilder.class.getName()).build(jsonCompany);
        deal.setCompany(company);
        JSONObject jsonUser= o.getJSONObject("user");
        User user = (User) BuilderFactory.getBuilder(UserBuilder.class.getName()).build(jsonUser);
        deal.setUser(user);
        return deal;
    }
}
