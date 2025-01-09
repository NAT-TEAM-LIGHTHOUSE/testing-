package com.lhsws.gak.service;
import com.lhsws.gak.DAO.ServerDetailsDao;
import com.lhsws.gak.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author akash.meshram
 *
 */
@Service
public class DataService {

    private final ServerDetailsDao serverDetailsDao;

    @Autowired
    public DataService(ServerDetailsDao serverDetailsDao) {
        this.serverDetailsDao = serverDetailsDao;
    }

    public ResponseData getAuthServerDetails(String appKey, String deviceId, String deviceName, String platform) {
        return serverDetailsDao.getAuthServerDetails(appKey, deviceId, deviceName, platform);
    }
}
