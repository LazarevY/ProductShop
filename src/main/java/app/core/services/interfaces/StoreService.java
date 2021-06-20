package app.core.services.interfaces;

import app.data.modeles.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {

    List<Store> getAllStores();

}
