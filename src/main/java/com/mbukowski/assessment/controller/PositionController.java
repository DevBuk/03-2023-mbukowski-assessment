package com.mbukowski.assessment.controller;

import com.mbukowski.assessment.entity.PositionEntity;
import com.mbukowski.assessment.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/{id}")
    public PositionEntity getPositionById(@PathVariable Long id){
        return positionService.getPositionEntityById(id);
    }

    @PostMapping("/addPosition")
    public PositionEntity addPosition(@RequestBody PositionEntity positionEntity) {
        return positionService.addPosition(positionEntity);
    }

    @PutMapping("/{id}")
    public PositionEntity updatePosition(@PathVariable Long id, @RequestBody PositionEntity positionWithNewDataToUpdate) {
        return positionService.updatePosition(id,positionWithNewDataToUpdate);
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
    }

}
