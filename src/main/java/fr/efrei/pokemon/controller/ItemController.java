package fr.efrei.pokemon.controller;

import fr.efrei.pokemon.dto.CreateItem;
import fr.efrei.pokemon.models.Item;
import fr.efrei.pokemon.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // GET
    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        List<Item> items = itemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // GET
    @GetMapping("/{id}")
    public ResponseEntity<Item> findById(@PathVariable String id) {
        Item item = itemService.findById(id);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    // POST id trainer : b5ad23a3-889c-42d3-85b4-b3e167e3f2e3
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateItem itemBody) {
        itemService.save(itemBody);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Item item = itemService.findById(id);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        itemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Item item) {
        Item itemAModifier = itemService.findById(id);
        if (itemAModifier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        itemService.update(id, item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
