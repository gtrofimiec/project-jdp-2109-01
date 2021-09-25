package com.kodilla.ecommercee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommerce/examples")
public class ExampleController {

    @GetMapping("/{id}")
    public ExampleDto getOne(@PathVariable("id") String id) {
        return new ExampleDto("test");
    }

    @GetMapping
    public List<ExampleDto> getAll() {
        return Arrays.asList(
                new ExampleDto("test1"),
                new ExampleDto("test2"),
                new ExampleDto("test3")
        );
    }

    @PostMapping
    public ExampleDto save(@RequestBody ExampleDto exampleDto) {
        // save
        return exampleDto;
    }

    @PutMapping("/{id}")
    public ExampleDto update(@PathVariable("id") String id, @RequestBody ExampleDto exampleDto) {
        // udpate
        return exampleDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        // delete
    }


    // it's only to not create another file, do not create dtos in controllers
    public class ExampleDto {
        private String name;

        public ExampleDto(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


