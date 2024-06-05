package com.heeha.domain.property.service;

import com.heeha.domain.property.dto.PropertyResponse;
import com.heeha.domain.property.entity.Property;
import com.heeha.domain.property.repository.PropertyRepository;
import com.heeha.global.config.BaseException;
import com.heeha.global.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    @Transactional
    public Map<String, List<PropertyResponse>> getAllProperties(Long livingTrustId) {

            Map<String, List<PropertyResponse>> propertiesMap = new HashMap<>();
            propertiesMap.put("cash", getCashProperty(livingTrustId));
            propertiesMap.put("security", getSecurityProperty(livingTrustId));
            propertiesMap.put("realty", getRealtyProperty(livingTrustId));
            propertiesMap.put("bond", getBondProperty(livingTrustId));
            return propertiesMap;

    }

    @Transactional
    public List<PropertyResponse> getCashProperty(Long livingTrustId) {
        try {
            List<Property> properties = propertyRepository.findAllByLivingTrust_IdAndType(livingTrustId, "cash");
            return properties.stream().map(PropertyResponse::new).toList();
        } catch (DataIntegrityViolationException e) {
            throw new BaseException(BaseResponseStatus.INVAILD_LIVINGTRUSTID);
        }
    }
    @Transactional
    public List<PropertyResponse> getSecurityProperty(Long livingTrustId) {
        try {
            List<Property> properties = propertyRepository.findAllByLivingTrust_IdAndType(livingTrustId, "security");
            return properties.stream().map(PropertyResponse::new).toList();
        } catch (DataIntegrityViolationException e) {
            throw new BaseException(BaseResponseStatus.INVAILD_LIVINGTRUSTID);
        }
    }
    @Transactional
    public List<PropertyResponse> getRealtyProperty(Long livingTrustId) {
        try {
            List<Property> properties = propertyRepository.findAllByLivingTrust_IdAndType(livingTrustId, "realty");
            return properties.stream().map(PropertyResponse::new).toList();
        } catch (DataIntegrityViolationException e) {
            throw new BaseException(BaseResponseStatus.INVAILD_LIVINGTRUSTID);
        }
    }
    @Transactional
    public List<PropertyResponse> getBondProperty(Long livingTrustId) {
        try {
            List<Property> properties = propertyRepository.findAllByLivingTrust_IdAndType(livingTrustId, "bond");
            return properties.stream().map(PropertyResponse::new).toList();
        }  catch (DataIntegrityViolationException e) {
            throw new BaseException(BaseResponseStatus.INVAILD_LIVINGTRUSTID);
        }
    }
}
