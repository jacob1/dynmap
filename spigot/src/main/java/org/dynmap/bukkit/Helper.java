package org.dynmap.bukkit;

import org.bukkit.Bukkit;
import org.dynmap.Log;
import org.dynmap.bukkit.helper.BukkitVersionHelper;

import java.util.LinkedHashMap;
import java.util.Map;

public class Helper {
    private static final Map<String, String> EXACT_VERSION_HELPERS = new LinkedHashMap<>();

    static {
        EXACT_VERSION_HELPERS.put("(MC: 1.21.10)", "v121_10.BukkitVersionHelperSpigot121_10");
        EXACT_VERSION_HELPERS.put("(MC: 1.21.9)", "v121_10.BukkitVersionHelperSpigot121_10");
        EXACT_VERSION_HELPERS.put("(MC: 1.21.8)", "v121_6.BukkitVersionHelperSpigot121_6");
        EXACT_VERSION_HELPERS.put("(MC: 1.21.7)", "v121_6.BukkitVersionHelperSpigot121_6");
        EXACT_VERSION_HELPERS.put("(MC: 1.21.6)", "v121_6.BukkitVersionHelperSpigot121_6");
        EXACT_VERSION_HELPERS.put("(MC: 1.21.5)", "v121_5.BukkitVersionHelperSpigot121_5");
        EXACT_VERSION_HELPERS.put("(MC: 1.21.4)", "v121_4.BukkitVersionHelperSpigot121_4");
        EXACT_VERSION_HELPERS.put("(MC: 1.21.3)", "v121_3.BukkitVersionHelperSpigot121_3");
        EXACT_VERSION_HELPERS.put("(MC: 1.21.2)", "v121_3.BukkitVersionHelperSpigot121_3");
        EXACT_VERSION_HELPERS.put("(MC: 1.21.1)", "v121.BukkitVersionHelperSpigot121");
        EXACT_VERSION_HELPERS.put("(MC: 1.21)", "v121.BukkitVersionHelperSpigot121");
        EXACT_VERSION_HELPERS.put("(MC: 1.20.6)", "v120_5.BukkitVersionHelperSpigot120_5");
        EXACT_VERSION_HELPERS.put("(MC: 1.20.5)", "v120_5.BukkitVersionHelperSpigot120_5");
        EXACT_VERSION_HELPERS.put("(MC: 1.20.4)", "v120_4.BukkitVersionHelperSpigot120_4");
        EXACT_VERSION_HELPERS.put("(MC: 1.20.3)", "v120_4.BukkitVersionHelperSpigot120_4");
        EXACT_VERSION_HELPERS.put("(MC: 1.20.2)", "v120_2.BukkitVersionHelperSpigot120_2");
        EXACT_VERSION_HELPERS.put("(MC: 1.20.1)", "v120.BukkitVersionHelperSpigot120");
        EXACT_VERSION_HELPERS.put("(MC: 1.20)", "v120.BukkitVersionHelperSpigot120");
        EXACT_VERSION_HELPERS.put("(MC: 1.19.4)", "v119_4.BukkitVersionHelperSpigot119_4");
        EXACT_VERSION_HELPERS.put("(MC: 1.19.3)", "v119_3.BukkitVersionHelperSpigot119_3");
        EXACT_VERSION_HELPERS.put("(MC: 1.19.2)", "v119.BukkitVersionHelperSpigot119");
        EXACT_VERSION_HELPERS.put("(MC: 1.19.1)", "v119.BukkitVersionHelperSpigot119");
        EXACT_VERSION_HELPERS.put("(MC: 1.19)", "v119.BukkitVersionHelperSpigot119");
        EXACT_VERSION_HELPERS.put("(MC: 1.18.2)", "v118_2.BukkitVersionHelperSpigot118_2");
        EXACT_VERSION_HELPERS.put("(MC: 1.18.1)", "v118.BukkitVersionHelperSpigot118");
        EXACT_VERSION_HELPERS.put("(MC: 1.18)", "v118.BukkitVersionHelperSpigot118");
        EXACT_VERSION_HELPERS.put("(MC: 1.17.1)", "v117.BukkitVersionHelperSpigot117");
        EXACT_VERSION_HELPERS.put("(MC: 1.17)", "v117.BukkitVersionHelperSpigot117");
        EXACT_VERSION_HELPERS.put("(MC: 1.16.5)", "v116_4.BukkitVersionHelperSpigot116_4");
        EXACT_VERSION_HELPERS.put("(MC: 1.16.4)", "v116_4.BukkitVersionHelperSpigot116_4");
        EXACT_VERSION_HELPERS.put("(MC: 1.16.3)", "v116_3.BukkitVersionHelperSpigot116_3");
        EXACT_VERSION_HELPERS.put("(MC: 1.16.2)", "v116_2.BukkitVersionHelperSpigot116_2");
        EXACT_VERSION_HELPERS.put("(MC: 1.16.1)", "v116.BukkitVersionHelperSpigot116");
        EXACT_VERSION_HELPERS.put("(MC: 1.16)", "v116.BukkitVersionHelperSpigot116");
        EXACT_VERSION_HELPERS.put("(MC: 1.15.2)", "v115.BukkitVersionHelperSpigot115");
        EXACT_VERSION_HELPERS.put("(MC: 1.15.1)", "v115.BukkitVersionHelperSpigot115");
        EXACT_VERSION_HELPERS.put("(MC: 1.15)", "v115.BukkitVersionHelperSpigot115");
        EXACT_VERSION_HELPERS.put("(MC: 1.14.4)", "v114_1.BukkitVersionHelperSpigot114_1");
        EXACT_VERSION_HELPERS.put("(MC: 1.14.3)", "v114_1.BukkitVersionHelperSpigot114_1");
        EXACT_VERSION_HELPERS.put("(MC: 1.14.2)", "v114_1.BukkitVersionHelperSpigot114_1");
        EXACT_VERSION_HELPERS.put("(MC: 1.14.1)", "v114_1.BukkitVersionHelperSpigot114_1");
        EXACT_VERSION_HELPERS.put("(MC: 1.14)", "v114_1.BukkitVersionHelperSpigot114_1");
        EXACT_VERSION_HELPERS.put("(MC: 1.13.2)", "v113_2.BukkitVersionHelperSpigot113_2");
    }

    private static BukkitVersionHelper loadVersionHelper(String classname) {
        try {
            return (BukkitVersionHelper) Class.forName(classname)
                    .getConstructor()
                    .newInstance();
        } catch (Exception x) {
            Log.severe("Error loading " + classname, x);
            return null;
        }
    }

    public static BukkitVersionHelper getHelper() {
        if (BukkitVersionHelper.helper != null) {
            return BukkitVersionHelper.helper;
        }

        String v = Bukkit.getServer().getVersion();
        Log.info("version=" + v);

        if (v.contains("MCPC") || v.contains("BukkitForge")) {
            logForgeUnsupported(v.contains("MCPC") ? "MCPC-Plus" : "BukkitForge");
            return null;
        }

        if (Bukkit.getServer().getClass().getName().contains("GlowServer")) {
            Log.info("Loading Glowstone support");
            BukkitVersionHelper.helper = loadVersionHelper("org.dynmap.bukkit.helper.BukkitVersionHelperGlowstone");
            return BukkitVersionHelper.helper;
        }

        for (Map.Entry<String, String> entry : EXACT_VERSION_HELPERS.entrySet()) {
            if (v.contains(entry.getKey())) {
                BukkitVersionHelper.helper = loadVersionHelper("org.dynmap.bukkit.helper." + entry.getValue());
                return BukkitVersionHelper.helper;
            }
        }

        BukkitVersionHelper.helper = loadVersionHelper("org.dynmap.bukkit.helper.BukkitVersionHelperCB");
        return BukkitVersionHelper.helper;
    }

    private static void logForgeUnsupported(String platform) {
        Log.severe("*********************************************************************************");
        Log.severe("* " + platform + " is not supported via the Bukkit version of Dynmap.");
        Log.severe("* Install the appropriate Forge version of Dynmap.                              *");
        Log.severe("* Add the DynmapCBBridge plugin to enable support for Dynmap-compatible plugins *");
        Log.severe("*********************************************************************************");
    }
}
