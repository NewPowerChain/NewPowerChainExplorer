package com.service;

import com.entity.*;
import com.msg.RespProjectInfoMsg;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    LegalPersonRepository legalPersonRepository;


    public RespProjectInfoMsg getProjectInfo(String projectHash) {
        Project project = projectRepository.findByProjectHashAndFlagAndStatusGreaterThanEqual(projectHash, 1, 5);
        if (project != null) {
            List<LegalPerson> legalPersons = legalPersonRepository.findByCompanyId(project.getCompanyId());
            Province province = provinceRepository.findByCode(project.getProvince());
            City city = cityRepository.findByCode(project.getCity());
            String fullAddress = getFullAddress(project, province, city);
            //省市地址
            String pcAddress = getPcAddress(province, city);
            List<Component> components = componentRepository.findByProjectCode(project.getCode());
            return RespProjectInfoMsg.convert(project, pcAddress, province.getName(), components.stream().mapToInt(Component::getAssemblyCount).sum(), legalPersons);
        }
        return null;
    }

    private String getPcAddress(Province province, City city) {
        if (province.getName().equals(city.getName())) {
            return city.getName();
        }
        return province.getName() + city.getName();
    }

    private String getFullAddress(Project project, Province province, City city) {
        if (province.getName().equals(city.getName())) {
            return city.getName() + project.getAddress();
        }
        return province.getName() + city.getName() + project.getAddress();
    }
}
