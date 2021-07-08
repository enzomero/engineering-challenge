package eu.senla.status_api.service;

import eu.senla.status_api.dao.UserDao;
import eu.senla.status_api.model.Profile;
import eu.senla.status_api.service.api.StatusApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusApiServiceImpl implements StatusApiService {

    private final UserDao userDao;

    @Override
    public Profile getProfile(final String uuid){
        return userDao.findByUuid(uuid);
    }

}
