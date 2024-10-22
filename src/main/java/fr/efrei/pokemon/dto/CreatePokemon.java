package fr.efrei.pokemon.dto;

import fr.efrei.pokemon.constants.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class CreatePokemon {

    private String id;

    private String name;

    private int level;

    private Type type; // SI mon pokemon est type feu -> Type == "FEU"

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}