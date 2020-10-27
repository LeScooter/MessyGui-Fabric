package com.boehmod.mod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

/**
 * Screen Events - All mixin hooks into {@link Screen}
 */
@Mixin(Screen.class)
final class ClientMixin {

    /**
     * Called when adding a new button to a {@link Screen} instance
     *
     * @param button - Given {@link AbstractButtonWidget} instance to add to the screen
     */
    @Inject(at = @At("HEAD"), method = "addButton")
    protected <T extends AbstractButtonWidget> void addButton(T button, CallbackInfoReturnable<AbstractButtonWidget> callbackInfo) {

        //Fetch the game client and window instances
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        Window gameWindow = minecraftClient.getWindow();

        //Fetch the scaled sizes of the game window
        final int width = gameWindow.getScaledWidth();
        final int height = gameWindow.getScaledHeight();

        //Create a new random instance
        Random random = new Random();

        //Set the max change value
        final int maxChange = 45;
        int maxTries = 150;

        //Fetch the initial button width/height
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();

        //Set the new x/y positions of the button
        int xPosition = button.x += getRandom(-maxChange, maxChange, random);
        int yPosition = button.y += getRandom(-maxChange, maxChange, random);

        //Repeat until button isn't outside of the screen
        while ((xPosition < 0 || (xPosition + buttonWidth) > width || yPosition < 0 || (yPosition + buttonHeight) > height) && maxTries-- > 0) {
            xPosition = button.x + getRandom(-maxChange, maxChange, random);
            yPosition = button.y + getRandom(-maxChange, maxChange, random);
        }

        //Set the new button x/y values
        button.x = xPosition;
        button.y = yPosition;
    }

    /**
     * Fetches a random value between two given values
     *
     * @param min    - Given minimum random value
     * @param max    - Given maximum random value
     * @param random - Given {@link Random} instance
     * @return - Returns a new random integer between the two given values
     */
    private int getRandom(final int min, final int max, final Random random) {
        //Calculate the next random value using the random instance
        return random.nextInt(max - min) + min;
    }
}
