package fr.efrei.pokemon.services;

import fr.efrei.pokemon.dto.UpdateShop;
import fr.efrei.pokemon.models.Pokemon;
import fr.efrei.pokemon.models.Shop;
import fr.efrei.pokemon.dto.CreateShop;
import fr.efrei.pokemon.models.Trainer;
import fr.efrei.pokemon.repositories.ShopRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import fr.efrei.pokemon.models.Item;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {

    private final ShopRepository repository;
    private final ItemService itemService;

    @Autowired
    public ShopService(ShopRepository repository, ItemService itemService){
        this.repository= repository;
        this.itemService=itemService;
    }

    public List<Shop> findAll(){
        return repository.findAll();
    }

    public Shop findById(String id){
        return repository.findById(id).orElse(null);
    }

    public void save(CreateShop shopBody) {

        Shop shop = new Shop();
        shop.setName(shopBody.getName());
        shop.setLocation(shopBody.getLocation());

        List<String> itemIds = shopBody.getItem();
        List<Item> itemsAAjouter = new ArrayList<>();

        for (String idItem : itemIds) {
            Item item = itemService.findById(idItem);
            if (item != null) {
                itemsAAjouter.add(item);
            }
        }
        shop.setItem(itemsAAjouter);
        repository.save(shop);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(String id, UpdateShop shopBody) {
        Shop shop = findById(id);
        if (shopBody.getName() != null) {
            shop.setName(shopBody.getName());
        }
        if(shopBody.getItem() != null && !shopBody.getItem().isEmpty()) {
            List<Item> itemList = new ArrayList<>();
            List<String> itemIds = shopBody.getItem();
            for(String idItem: itemIds) {
                Item item = itemService.findById(idItem);
                if(item != null) {
                    itemList.add(item);
                }
            }
            itemList.addAll(shop.getItem());
            shop.setItem(itemList);
        }
        repository.save(shop);
    }
}
