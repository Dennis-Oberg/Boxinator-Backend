package com.Dennis.Boxinator.Controller;

import com.Dennis.Boxinator.Model.Box;
import com.Dennis.Boxinator.Repository.BoxRepository;
import com.Dennis.Boxinator.Service.BoxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BoxController {

    private final BoxService boxService;
    //boxrespoitory aint being used since i created my bad db-connection
    private final BoxRepository boxRepository;

    public BoxController(BoxService service, BoxRepository boxRepository) {
        this.boxService = service;
        this.boxRepository = boxRepository;
    }

    @PostMapping("/addbox")
    public ResponseEntity<String> add(@RequestBody Box box) {
        if (box == null) {
            throw new NullPointerException("No Box Added");
        } else if (box.validateWeigth(box.getWeightInKiloGrams())) {

            boxService.add(box);
            return new ResponseEntity<>(String.format("Added Box %s", box.getBoxName()), HttpStatus.OK);
        }
        return new ResponseEntity<>(String.format("Failed To Add %s", box.getBoxName()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/listboxes")
    public ResponseEntity<List<Box>> get() {
        //using the interface from JpaRepository
        //return new ResponseEntity<>(boxRepository.findAll(), HttpStatus.OK);

        //using my bad solution
        return new ResponseEntity<>(boxService.getBoxes(), HttpStatus.OK);
    }
}
