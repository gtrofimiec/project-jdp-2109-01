package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.GroupAlreadyExistsException;
import com.kodilla.ecommercee.controller.exception.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public Group saveGroup(final Group group) throws GroupAlreadyExistsException {
        if(groupRepository.existsById(group.getId())) {
            throw new GroupAlreadyExistsException();
        } else {
            return groupRepository.save(group);
        }
    }

    public Group getGroup(final Long groupId) throws GroupNotFoundException {
        return groupRepository.findById(groupId)
                .orElseThrow(GroupNotFoundException::new);
    }
}