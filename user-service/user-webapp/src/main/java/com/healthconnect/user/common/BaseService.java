package com.healthconnect.user.common;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import com.healthconnect.user.model.common.Address;
import com.healthconnect.user.model.common.AddressDto;
import com.healthconnect.user.model.common.CityMaster;
import com.healthconnect.user.model.common.CommonDto;
import com.healthconnect.user.model.common.ServiceSearchDto;
import com.healthconnect.user.model.common.SpecialityMaster;
import com.healthconnect.user.model.common.SubSpecialityMaster;
import com.healthconnect.user.model.core.BaseEntity;
import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.enums.BloodGroupType;
import com.healthconnect.user.model.enums.GenderType;
import com.healthconnect.user.model.enums.ServiceCategoryType;
import com.healthconnect.user.model.speciality.SpecialityDto;
import com.healthconnect.user.repository.AddressRepository;
import com.healthconnect.user.repository.CityRepository;
import com.healthconnect.user.repository.HospitalRepository;
import com.healthconnect.user.repository.MedicalCollegeRepository;
import com.healthconnect.user.repository.MedicalCouncilRepository;
import com.healthconnect.user.repository.MedicalDegreeRepository;
import com.healthconnect.user.repository.PhysicianRepository;
import com.healthconnect.user.repository.SpecialityRepository;
import com.healthconnect.user.repository.SubSpecialityRepository;
import com.healthconnect.user.repository.UserRepository;
import com.healthconnect.user.security.AuthenticationFacadeService;

/**
 * A BaseService Class and Common Implementation
 */
public abstract class BaseService<T extends BaseEntity> {
	
	@Autowired
    protected Environment environment;
	
	@Autowired
    protected AddressRepository addressRepository;

	@Autowired
    protected CityRepository cityRepository;
	
	@Autowired
    protected AuthenticationFacadeService authService;
	
	@Autowired
    protected UserRepository userRepository;
	
	@Autowired
    protected SpecialityRepository specialityRepository;
	
	@Autowired
    protected SubSpecialityRepository subSpecialityRepository;
	
	 @Autowired 
	 protected MedicalCouncilRepository medCouncilRepository;
	 
	 @Autowired
	 protected MedicalDegreeRepository degreeRepository;
	 
	 @Autowired
	 protected MedicalCollegeRepository collegeRepository;
	 
	 @Autowired
	 protected HospitalRepository hospitalRepository;
	 
	 @Autowired
	 protected PhysicianRepository physicianRepository;

    protected  <T extends BaseEntity> T setDefaultOnCreate(T entity, User user){
        entity.setCreatedBy(user);
        entity.setCreatedOn(LocalDateTime.now());
        entity.setDeleted(false);
        return entity;
    }

    protected  <T extends BaseEntity> T setDefaultOnUpdate(T entity, User user){
        entity.setLastModifiedBy(user);
        entity.setLastModifiedOn(LocalDateTime.now());
        entity.setDeleted(false);
        return entity;
    }

    protected Address resolveDuplicacyAndGetAddress(AddressDto addressDto, User loggedInUser) {
    	Address address = null;
    	if(!StringUtils.isEmpty(addressDto.getLocality()))
    		address = addressRepository.findAddressByLocalityAndCity(addressDto.getLocality(), addressDto.getCity(), false);
        if(address == null) {
            address = new Address();
            BeanUtils.copyProperties(addressDto, address);
            address.setCityMaster(resolveDuplicacyAndGetCity(addressDto));
            setDefaultOnCreate(address, loggedInUser);
            address = addressRepository.save(address);
        }
        return address;
    }
    
    protected CityMaster resolveDuplicacyAndGetCity(AddressDto addressDto) {
        CityMaster cityMaster = cityRepository.findCityByNameAndState(addressDto.getCity(), addressDto.getState(), false);
        if(cityMaster == null) {
            cityMaster = new CityMaster();
            BeanUtils.copyProperties(addressDto, cityMaster);
            cityMaster = cityRepository.save(cityMaster);
        }
        return cityMaster;
    }
    
    protected List<CommonDto> getBloodGroups(){
    	List<CommonDto> bloodGroups = new ArrayList<>();
    	for(BloodGroupType userType : BloodGroupType.values()) {
    		CommonDto dto = new CommonDto();
    		dto.setKey(userType.name());
    		dto.setValue(userType.getValue());
    		bloodGroups.add(dto);
    	}
    	return bloodGroups;
    }
    
    protected List<CommonDto> getGenders(){
    	List<CommonDto> genders = new ArrayList<>();
    	for(GenderType userType : GenderType.values()) {
    		CommonDto dto = new CommonDto();
    		dto.setKey(userType.name());
    		dto.setValue(userType.getValue());
    		genders.add(dto);
    	}
    	return genders;
    }
    
    protected List<ServiceSearchDto> fetchAllMedicoServices() {
		List<ServiceSearchDto> medicoServices = new ArrayList<>();
		for (ServiceCategoryType category : ServiceCategoryType.values()) {
			List<ServiceSearchDto> searchServices = null;
			switch (category) {
			case SPECIALITY:
				List<SpecialityMaster> specialities = specialityRepository.findAll();
				searchServices = specialities.stream()
						.map(subSpeciality -> new ServiceSearchDto(subSpeciality.getName(),
								subSpeciality.getDescription(), category))
						.collect(Collectors.toList());
				medicoServices.addAll(searchServices);
				break;
				
			case SUB_SPECIALITY:
				List<SubSpecialityMaster> subSpecialities = subSpecialityRepository.findAll();
				searchServices = subSpecialities.stream()
						.map(subSpeciality -> new ServiceSearchDto(subSpeciality.getName(),
								subSpeciality.getDescription(), category))
						.collect(Collectors.toList());
				medicoServices.addAll(searchServices);
				break;
			}
		}
		return medicoServices;
	}
    
    protected List<CommonDto> fetchSpeciality(){
	    return specialityRepository.fetchSpeciality();
    }
    
    protected SpecialityDto fetchSpecialityByName(String name){
    	SpecialityDto specialityDto = new SpecialityDto();
    	SpecialityMaster speciality = specialityRepository.findByName(name);
    	if(speciality != null) {
    		Set<CommonDto> subSpecialities = subSpecialityRepository.findBySpecialityId(speciality.getName());
    		specialityDto.setName(speciality.getName());
    		specialityDto.setDescription(speciality.getDescription());
    		specialityDto.setId(speciality.getRecordId());
    		specialityDto.setSubSpecialityMasters(subSpecialities);
    	} else {
    		throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Invalid Speciality name");
    	}
    	
    	return specialityDto;
    }
    
    protected String resolveTitleByUserType(User user) {
        String title = null;
        switch (user.getUserType()) {
            case DOCTOR:
                title = "Dr.";
                break;
        }
        if(title == null) {
            switch (user.getGenderType()) {
                case MALE:
                    title = "Mr.";
                    break;
                case FEMALE:
                    title = "Miss.";
                    break;
            }
        }
        return title;
    }
    
}
