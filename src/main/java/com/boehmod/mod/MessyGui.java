package com.boehmod.mod;

import net.fabricmc.api.ModInitializer;

public class MessyGui implements ModInitializer {

    /**
     * Necessary attributes for this mod
     */
    public static final String MOD_NAME = "MessyGui";
    /**
     * Static instance of this mod container
     */
    private static MessyGui MESSY_GUI;

    /**
     * Default Constructor for the {@link MessyGui} container
     */
    public MessyGui() {
        MESSY_GUI = this;
    }

    /**
     * Get Instance - Fetches the static instance of the {@link MessyGui} mod container
     *
     * @return - Returned static {@link MessyGui} instance
     */
    public static MessyGui getInstance() {
        return MESSY_GUI;
    }

    /**
     * On Initialize - Called whilst initializing the mod
     */
    @Override
    public void onInitialize() {
        //Inform user that the mod has loaded successfully
        System.out.println(String.format("%s has loaded successful!", MOD_NAME));
    }
}
