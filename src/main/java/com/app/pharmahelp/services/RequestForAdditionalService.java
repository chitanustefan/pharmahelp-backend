package com.app.pharmahelp.services;

import com.app.pharmahelp.entities.AdminNotifications;
import com.app.pharmahelp.entities.Drug;
import com.app.pharmahelp.entities.RequestForAdditional;
import com.app.pharmahelp.entities.User;
import com.app.pharmahelp.repository.RequestRepo;
import com.app.pharmahelp.services.interfaces.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RequestForAdditionalService implements IRequestService {

    @Autowired
    RequestRepo requestRepository;
    @Autowired
    DrugService drugService;
    @Autowired
    UserService userService;

    @Override
    public List<RequestForAdditional> getAllRequests() {
        List<RequestForAdditional> result = requestRepository.findAll();
        return result;
    }

    @Override
    public RequestForAdditional saveRequest(RequestForAdditional requestForAdditional) {
        RequestForAdditional saved = this.requestRepository.save(requestForAdditional);
        return saved;
    }

    @Override
    @Transactional
    public RequestForAdditional updateRequest(Long id, RequestForAdditional requestForAdditional) {
        Optional<RequestForAdditional> optional = requestRepository.findById(id);
        RequestForAdditional result = optional.orElse(requestForAdditional);
        return result;
    }

    @Override
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public RequestForAdditional requestDrug(String name, String location, Long idUser){
        Drug drug=drugService.findByName(name);
        if(drug==null)
            return null;
        else
        {
            Optional<User> u=userService.findById(idUser);
            User user=u.get();
            if(user==null)
                return null;
            else
            {
                RequestForAdditional requestForAdditional=new RequestForAdditional();
                requestForAdditional.setStatus("pending");
                requestForAdditional.setUser(user);
                requestForAdditional.setDrug(drug);
                requestForAdditional.setLocation(location);
                return this.saveRequest(requestForAdditional);
            }
        }
    }
    @Override
    public List<RequestForAdditional> getAllByLocation(String location){
        return requestRepository.getAllByLocation(location);
    }

    public List<RequestForAdditional> mostReqeustDrug(String location){
        System.out.println(location);
        return requestRepository.mostReqeustDrug(location);
    }

}
