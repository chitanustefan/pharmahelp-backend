package com.app.pharmahelp.services.interfaces;

import com.app.pharmahelp.entities.PharmacyStock;
import com.app.pharmahelp.entities.RequestForAdditional;

import java.util.List;

public interface IRequestService {

    List<RequestForAdditional> getAllByLocation(String location);

    List<RequestForAdditional> getAllRequests();
    RequestForAdditional saveRequest(RequestForAdditional requestForAdditional);
    RequestForAdditional updateRequest(Long id, RequestForAdditional requestForAdditional);
    void deleteRequest(Long id);
    RequestForAdditional requestDrug(String name, String Location, Long idUser);
}
