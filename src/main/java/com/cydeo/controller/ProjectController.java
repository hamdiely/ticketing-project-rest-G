package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @GetMapping
    public ResponseEntity<ResponseWrapper> getProjects(){
        List<ProjectDTO> projects =projectService.listAllProjects();

        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved",projects, HttpStatus.OK));
    }
    @GetMapping("/{code}")
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("code") String projectCode){

        ProjectDTO project =projectService.getByProjectCode(projectCode);
        return  ResponseEntity.ok(new ResponseWrapper("Project successfully retrieved",project,HttpStatus.OK));
    }
    @PostMapping()
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO project){
     projectService.save(project);
        return  ResponseEntity.ok(new ResponseWrapper("Project successfully created",HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateProject(@RequestBody ProjectDTO project){
        projectService.update(project);
        return  ResponseEntity.ok(new ResponseWrapper("Project successfully updated",HttpStatus.OK));
    }
    @DeleteMapping("/{projectcode}")
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("projectcode") String projectCode){
        projectService.getByProjectCode(projectCode);
        return  ResponseEntity.ok(new ResponseWrapper("Project successfully deleted",HttpStatus.OK));
    }
    @GetMapping("manager/project-status")
    public ResponseEntity<ResponseWrapper> getProjectByManager(){
        List<ProjectDTO> projectDTOList=projectService.listAllProjectDetails();
        return  ResponseEntity.ok(new ResponseWrapper("Projects successfully retrieved",projectDTOList,HttpStatus.CREATED));
    }

    @PutMapping("/manager/complete/{projectCode}")
    public ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable("projectCode") String projectCode){
     projectService.complete(projectCode);
        return  ResponseEntity.ok(new ResponseWrapper("Project successfully deleted",HttpStatus.OK));
    }




}
