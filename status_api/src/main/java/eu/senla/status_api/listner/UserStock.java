package eu.senla.status_api.listner;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.status_api.dao.UserDao;
import eu.senla.status_api.dto.UserDto;
import eu.senla.status_api.model.Profile;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserStock {

    private final UserDao userDao;
    private final ObjectMapper objectMapper;

    @Transactional
    @KafkaListener(topics = {"create", "save"}, groupId = "test",containerFactory = "userStock")
    public void listener(final UserDto userDto){
        log.info("Get dto: " + userDto.toString());
        final Profile savedProfile = userDao.findByUuid(userDto.getUuid().toString());
        if (savedProfile == null){
            userDao.save(objectMapper.convertValue(userDto, Profile.class));
            return;
        }
        savedProfile.setUserState(userDto.getUserState().toString());
        savedProfile.setExtraData(userDto.getExtraData() == null ? savedProfile.getExtraData() : userDto.getExtraData());
        savedProfile.setContract(userDto.getContract() == null ? savedProfile.getContract() : userDto.getContract());
        savedProfile.setApprover(userDto.getApprover() == null ? savedProfile.getApprover() : userDto.getApprover());
        log.info(String.format("User [%s] saved", savedProfile.toString()));
    }
}
