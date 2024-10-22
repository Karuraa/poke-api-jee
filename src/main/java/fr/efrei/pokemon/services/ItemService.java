package fr.efrei.pokemon.services;

import fr.efrei.pokemon.models.Item;

import fr.efrei.pokemon.dto.CreateItem;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.models.Trainer;
import fr.efrei.pokemon.repositories.ItemRepository;
import fr.efrei.pokemon.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository repository;
    private final TrainerService trainerService;

    @Autowired
    public ItemService(ItemRepository repository, TrainerService  trainerService) {
        this.repository = repository;
        this.trainerService = trainerService;
    }

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Item findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void save(CreateItem itemBody) {
        Item item = new Item();
        item.setName(itemBody.getName());
        item.setDescription(itemBody.getDescription());

        String trainerId = itemBody.getTrainerId();
        Trainer trainer = trainerService.findById(trainerId);

        if (trainer != null) {
            item.setTrainer(trainer);
        }
        repository.save(item);
    }

    public void update(String id, Item itemBody) {
        Item itemAModifier = findById(id);
        itemAModifier.setName(itemBody.getName());
        itemAModifier.setDescription(itemBody.getDescription());
        itemAModifier.setTrainer(itemBody.getTrainer());
        repository.save(itemAModifier);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}