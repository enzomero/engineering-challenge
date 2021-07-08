package eu.senla.status_api.service.api;

import eu.senla.status_api.model.Profile;

public interface StatusApiService {
    Profile getProfile(String uuid);
}
