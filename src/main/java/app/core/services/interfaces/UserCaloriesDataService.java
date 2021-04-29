package app.core.services.interfaces;

import app.core.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface UserCaloriesDataService {
    Response getDataCalories(long userId);

    Response setDataCalories(long userId, int weight, int growth, Long gender);
}
