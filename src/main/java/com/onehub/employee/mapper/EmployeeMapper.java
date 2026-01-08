package com.onehub.employee.mapper;

import com.onehub.employee.dto.CreateEmployeeRequest;
import com.onehub.employee.dto.EmployeeResponse;
import com.onehub.employee.model.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "firstname", source = "firstname"),
            @Mapping(target = "surname", source = "surname"),
            @Mapping(target = "dob", source = "dob"),
            @Mapping(target = "gender", source = "gender"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "address", source = "address")
    })
    Employee toEntity(CreateEmployeeRequest request);

    EmployeeResponse toResponse(Employee employee);
}

