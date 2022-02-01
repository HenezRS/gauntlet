package com.henez.gauntlet.world.mapobjects;

import com.henez.gauntlet.datastructures.GameList;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;

public final class ActorFactory {
    private ActorFactory() {}

    private static int nextId = 0;
    private static int nextFighterId = 0;
    private static GameList<String> generatedNames = new GameList<String>().addAllAndReturn("kuponut",
                                                                                            "dislife",
                                                                                            "huntsman",
                                                                                            "magorical",
                                                                                            "chillman",
                                                                                            "beanboi",
                                                                                            "mariko",
                                                                                            "wafflyhawk",
                                                                                            "zurence",
                                                                                            "king of hats",
                                                                                            "poog",
                                                                                            "marrjak",
                                                                                            "detro au",
                                                                                            "merincholy",
                                                                                            "mars tender",
                                                                                            "pepe",
                                                                                            "wojak",
                                                                                            "soyjak",
                                                                                            "chad",
                                                                                            "ash",
                                                                                            "gary",
                                                                                            "zelda",
                                                                                            "link",
                                                                                            "fishsticks",
                                                                                            "ryze",
                                                                                            "jax",
                                                                                            "fiora",
                                                                                            "kaisa",
                                                                                            "vayne",
                                                                                            "kalista",
                                                                                            "jhin",
                                                                                            "ezreal",
                                                                                            "lucian",
                                                                                            "tristana",
                                                                                            "squall",
                                                                                            "rinoa",
                                                                                            "zell",
                                                                                            "selphie",
                                                                                            "irvine",
                                                                                            "quistis",
                                                                                            "cloud",
                                                                                            "aerith",
                                                                                            "cait sith",
                                                                                            "red XIII",
                                                                                            "barret",
                                                                                            "tifa",
                                                                                            "cid",
                                                                                            "zidane",
                                                                                            "garnet",
                                                                                            "dagger",
                                                                                            "vivi",
                                                                                            "steiner",
                                                                                            "eiko",
                                                                                            "quina",
                                                                                            "freya",
                                                                                            "toby",
                                                                                            "bekka",
                                                                                            "ben",
                                                                                            "nilania",
                                                                                            "dragon",
                                                                                            "trav",
                                                                                            "scott",
                                                                                            "matt",
                                                                                            "ash",
                                                                                            "marrjak",
                                                                                            "sigma",
                                                                                            "alpha",
                                                                                            "beta",
                                                                                            "gamma",
                                                                                            "arriate",
                                                                                            "nastyshaman",
                                                                                            "erriana",
                                                                                            "gurldan",
                                                                                            "sylvanas",
                                                                                            "denny",
                                                                                            "denathrius", "cheems", "kez");

    /*public static GameList<PlayerFighter> generatePlayerParty() {
        int pos = 0;
        GameList<PlayerFighter> fighters = new GameList<>();
        fighters.add(new PlayerFighter(pos++, ClassName.warrior, generateName()));
        if(!DebugFlags.singlePlayer) {
            fighters.add(new PlayerFighter(pos++, ClassName.warrior, generateName()));
            fighters.add(new PlayerFighter(pos++, ClassName.warrior, generateName()));
            fighters.add(new PlayerFighter(pos++, ClassName.warrior, generateName()));

            fighters.add(new PlayerFighter(pos++, ClassName.warrior, generateName()));
            fighters.add(new PlayerFighter(pos++, ClassName.warrior, generateName()));
            fighters.add(new PlayerFighter(pos++, ClassName.warrior, generateName()));

            fighters.add(new PlayerFighter(pos++, ClassName.warrior, generateName()));
            fighters.add(new PlayerFighter(pos++, ClassName.warrior, generateName()));
        }

        DebugFlags.equipOverridesFirstPlayer.forEach((key, value) -> fighters.get(0).getEquipSheet().equip(fighters.get(0), key, value));

        return fighters;
    }*/

    private static String generateName() {
        Collections.shuffle(generatedNames);
        return generatedNames.stream().findAny().map(name -> {
            generatedNames.remove(name);
            return name.length() > 12 ? StringUtils.capitalize(name.substring(0, 12)) : StringUtils.capitalize(name);
        }).orElse("no name");
    }

    public static int getNextIdAndIncrement() {
        return nextId++;
    }

    public static int getNextFighterIdAndIncrement() {
        return nextFighterId++;
    }
}
