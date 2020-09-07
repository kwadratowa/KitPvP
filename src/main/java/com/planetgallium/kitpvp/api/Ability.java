package com.planetgallium.kitpvp.api;

import com.planetgallium.kitpvp.util.Resource;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Ability {

    private String name;
    private ItemStack activator;
    private String message;
    private Sound sound;
    private int soundPitch;
    private int soundVolume;
    private List<PotionEffect> effects;
    private List<String> commands;

    public Ability(String name) {

        this.name = name;

        this.effects = new ArrayList<>();
        this.commands = new ArrayList<>();

    }

    public void setActivator(ItemStack activator) {

        this.activator = activator;

    }

    public void setMessage(String message) {

        this.message = message;

    }

    public void setSound(Sound sound, int pitch, int volume) {

        this.sound = sound;
        this.soundPitch = pitch;
        this.soundVolume = volume;

    }

    public void addEffect(PotionEffectType type, int amplifier, int durationSeconds) {

        PotionEffect effect = new PotionEffect(type, durationSeconds * 20, amplifier - 1);
        effects.add(effect);

    }

    public void addCommand(String command) {

        commands.add(command);

    }

    public void toResource(Resource resource, String path) {

        String pathPrefix = path + "." + name;

        resource.set(pathPrefix + ".Activator.Name", activator.getItemMeta().getDisplayName().replace("§", "&"));
        resource.set(pathPrefix + ".Activator.Material", activator.getType().toString());
        resource.set(pathPrefix + ".Message.Message", message);
        resource.set(pathPrefix + ".Sound.Sound", sound.toString());
        resource.set(pathPrefix + ".Sound.Pitch", soundPitch != 0 ? soundPitch : null);
        resource.set(pathPrefix + ".Sound.Volume", soundVolume != 0 ? soundVolume : null);

        for (PotionEffect effect : effects) {
            String type = effect.getType().getName(); // might just be effect.getType()? idk test this
            int amplifier = effect.getAmplifier();
            int duration = effect.getDuration();

            resource.set(pathPrefix + ".Effects." + type + ".Amplifier", amplifier);
            resource.set(pathPrefix + ".Effects." + type + ".Duration", duration);
        }

        resource.set(pathPrefix + ".Commands.Commands", commands.toArray()); // see if this actually changes it into a stringlist

        resource.save();

    }

    public String getName() { return name; }

    public ItemStack getActivator() { return activator; }

    public String getMessage() { return message; }

    public Sound getSound() { return sound; }

    public int getSoundPitch() { return soundPitch; }

    public int getSoundVolume() { return soundVolume; }

    public List<PotionEffect> getEffects() { return effects; }

    public List<String> getCommands() { return commands; }

}