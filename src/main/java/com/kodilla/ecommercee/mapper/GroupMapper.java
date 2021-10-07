package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMapper {

    public GroupDto mapGroupToGroupDto(Group group) {
        ProductMapper productMapper = new ProductMapper();
        return new GroupDto(group.getName(),
                productMapper.mapToProductDtoList(group.getProductList()));
    }

    public Group mapGroupDtoToGroup(GroupDto groupDto) {
        ProductMapper productMapper = new ProductMapper();
        return new Group(groupDto.getName(),
                productMapper.mapToProductList(groupDto.getProductDtoList()));
    }

    public List<GroupDto> mapToGroupDtoList(List<Group> groupList) {
        return groupList.stream()
                .map(this::mapGroupToGroupDto)
                .collect(Collectors.toList());
    }
}