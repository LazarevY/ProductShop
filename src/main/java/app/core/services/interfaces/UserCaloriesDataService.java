package app.core.services.interfaces;

import app.core.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface UserCaloriesDataService {
    Response getDataCalories(String email);

    Response setDataCalories(String email, int weight, int growth, Long gender);
}
