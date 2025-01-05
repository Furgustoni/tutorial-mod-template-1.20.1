package net.furgus.furgusmenagerie.config;

import net.furgus.furgusmenagerie.FurgusMod;
import net.furgus.furgusmenagerie.config.configEntries.BooleanConfigEntry;
import net.furgus.furgusmenagerie.config.configEntries.FloatConfigEntry;
import net.furgus.furgusmenagerie.config.configEntries.IntegerConfigEntry;
import org.apache.logging.log4j.core.config.json.JsonConfiguration;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public final class Config extends JsonConfiguration {
    private static final Config INSTANCE = loadOrCreate();

    public Config(LoggerContext loggerContext, ConfigurationSource source) {
        super(loggerContext, source);
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    private static Config loadOrCreate() {
        try {
            File configFile = new File("config.json");
            LoggerContext context = new LoggerContext("ConfigContext");
            if (configFile.exists()) {
                ConfigurationSource source = new ConfigurationSource(new FileInputStream(configFile));
                return new Config(context, source);
            } else {
                // Create a new configuration file if it does not exist
                configFile.createNewFile();
                ConfigurationSource source = new ConfigurationSource(new FileInputStream(configFile));
                return new Config(context, source);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @BooleanConfigEntry(true)
    public boolean useCustomKeybindSystem;


    public Map<String, Integer> fuelList = Map.of(
            "minecraft:blaze_powder", 1200
    );

    public Map<String, Boolean> validDimensions = Map.of(
            "minecraft:overworld", true,
            "minecraft:the_nether", true,
            "minecraft:the_end", true
    );

    public Map<String, Integer> gunpowderAmmunition = Map.of(
            "minecraft:gunpowder", 100
    );

    public Map<String, Integer> arrowAmmunition = Map.of(
            "minecraft:arrow", 100,
            "minecraft:tipped_arrow", 100,
            "minecraft:spectral_arrow", 100
    );

    public Map<String, Integer> bombBayAmmunition = Map.of(
            "minecraft:tnt", 100
    );
}