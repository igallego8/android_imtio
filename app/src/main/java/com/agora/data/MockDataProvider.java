package com.agora.data;

import android.graphics.Bitmap;

import com.agora.app.AppConfig;
import com.agora.entity.Auth;
import com.agora.entity.Category;
import com.agora.entity.Conversation;
import com.agora.entity.Bid;
import com.agora.entity.Client;
import com.agora.entity.Company;
import com.agora.entity.Deal;
import com.agora.entity.Employee;
import com.agora.entity.Message;
import com.agora.entity.Msg;
import com.agora.entity.Need;
import com.agora.entity.ProductBid;
import com.agora.entity.ServiceBid;
import com.agora.entity.Topic;
import com.agora.entity.User;
import com.agora.mockup.DTOBuilder;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ivan on 17/09/15.
 */
public class MockDataProvider  implements IDataProvider {
    @Override
    public JSONObject getCategoryPrediction(String... params) {
        return null;
    }

    @Override
    public Company findCompanyByCompanyKey(Long companyKey) {
        return null;
    }

    @Override
    public List<Category> fetchProductCategories() {
        return null;
    }

    @Override
    public List<Category> fetchSubCategories(Long categoryKey) {
        return null;
    }

    @Override
    public List<Need> fetchNeedsByUserKey(Long userKey) {
       return DTOBuilder.getNeedArray();

    }

    @Override
    public Need fetchNeedByNeedKey(Long needKey) {
        return null;
    }

    @Override
    public Deal findDealByNeedKey(Long needKey) {
        return null;
    }

    @Override
    public Long createDeal(Deal deal) {
        return null;
    }


    @Override
    public void bidsRead(Long needKey) {

    }

    @Override
    public void messagesRead(Long needKey, Integer messagesNumber) {

    }

    @Override
    public Need findNeedByNeedKey(Long needKey) {
        return null;
    }

    @Override
    public void getNeedDetail(Need need) {
        DTOBuilder.getNeedDetail(need);
    }

    @Override
    public void uploadImage(HashMap<String, String> params) {

    }

    @Override
    public void uploadImage(FileInputStream stream) {

    }

    @Override
    public Long sendMessage(Msg msg) {
        return -1L;
    }

    @Override
    public void setMessageStatus(Long messageKey, String status) {

    }

    @Override
    public User signIn(Auth auth) {
        return  null;
    }

    @Override
    public Long createNeed(Need need, Long userkey) {
         DTOBuilder.createNeed(need);
        return 0L;
    }

    @Override
    public boolean deleteNeedByNeedKey(Long needKey) {
        return DTOBuilder.removeNeed(needKey);

    }


    @Override
    public List<Conversation> fetchConversationByNeedKey(Long needKey) {
        return DTOBuilder.getConversationNeedKeyArray(needKey);
    }


    @Override
    public List<Conversation> fetchConversationByUserKey(Long userKey) {
        return DTOBuilder.getConversationUserKeyArray(userKey);
    }

    @Override
    public List<Conversation> fetchConversationByCompanyKey(Long companyKey) {
        return DTOBuilder.getConversationCompanyKeyArray(companyKey);
    }

    @Override
    public List<Message> fetchMessagesByNeedKey(Long needKey) {
        return null;
    }

    @Override
    public void sendClientMessage(Message message, Need need, Client client) {

    }

    @Override
    public void sendCompanyMessage(Message message, Need need, Employee employee) {

    }


    @Override
    public List<Bid> fetchBidByNeedKey(Long need) {
       // return  DTOBuilder.getBidArray(need);
        return new ArrayList<Bid>();
    }

    @Override
    public List<Bid> fetchBidByCompanyKey(Long companyKey) {
        return null;
    }

    @Override
    public boolean createBid(Bid bid, Long User) {
        return false;
    }

    @Override
    public boolean deleteBidByBidKey(Long bidKey) {
        return false;
    }

    @Override
    public Bid findBidByBidKey(Long bidKey) {
        return null;
    }



    @Override
    public boolean cancelDealByDealKey(Long deal) {
        return false;
    }

    @Override
    public List<Deal> fetchDealsByUserKey(Long userKey) {
        return null;
    }

    @Override
    public List<Deal> fetchDealByCompanyKey(Long companyKey) {
        return null;
    }

    @Override
    public Deal findDealByDealKey(Long dealKey) {
        return null;
    }

    @Override
    public List<Company> fetchFavoritiesByUserKey(Long userKey) {
        return null;
    }

    @Override
    public void setFavorityCompany(Long companyKey, Long userKey) {

    }

    @Override
    public boolean login(String id, String password) {
        return false;
    }

    @Override
    public boolean createAccount(Client client) {
        return false;
    }

    @Override
    public Long createUser(User user) {
        return null;
    }

    @Override
    public Client findClientByUserKey(Long userKey) {
        return null;
    }

    @Override
    public User findUserByUserKey(Long userKey) {
        return null;
    }

    @Override
    public List<Topic> fetchTopics() {
        return null;
    }

    @Override
    public void notifyMessagesRead(List<Long> messageKeys) {

    }

    @Override
    public void notifyMessagesReceived(Long messageKey) {

    }

    @Override
    public byte[] imageLowQuality(String file) {
        return new byte[0];
    }

    @Override
    public byte[] imageHighQuality(String file) {
        return new byte[0];
    }

    @Override
    public Bitmap getBitmap(String urlString) {
        return null;
    }
}
