package app.core.services.impl;

import app.core.services.interfaces.StoreService;
import app.data.mappers.StoreMapper;
import app.data.modeles.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public List<Store> getAllStores() {
        return storeMapper.findAll();
    }
}
