package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("v1/ecommerce/groups")
public class GroupController {

    @GetMapping
    public List<GroupDto> getGroups() {
        return Arrays.asList(
                new GroupDto(1L, "underwear"),
                new GroupDto(2L, "jackets")
        );
    }
    @GetMapping("/{groupId}")
    public GroupDto getGroup(@PathVariable ("groupId") Long groupId){
        return new GroupDto (1L, "underwear");
    }
    @PostMapping
    public void createGroup(@RequestBody GroupDto groupDto){

    }
    @PutMapping("/{groupId}")
    public GroupDto updateGroup(@PathVariable ("groupId") Long groupId, @RequestBody GroupDto groupDto){
        return new GroupDto (1L, "updated name");
    }
}