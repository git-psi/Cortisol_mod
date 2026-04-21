package net.tech.testmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.tech.testmod.TestMod;

public class CortisolHudOverlay {
    private static final ResourceLocation CORTISOL_BAR =new ResourceLocation(TestMod.MOD_ID, "/textures/cortisol/cortisol_meter.png");
    private static final ResourceLocation CORTISOL_ARROW =new ResourceLocation(TestMod.MOD_ID, "/textures/cortisol/cortisol_arrow.png");
    private static float angle = (ClientCortisolData.getPlayerCortisol() * 1.8f) - 90;
    public static IGuiOverlay HUD_CORTISOL=(CortisolHudOverlay::render);

    private static void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        int display_width_bar = 110;
        int display_height_bar = 65;
        //render the bar

        int x_bar = 0;
        int y_bar = screenHeight - display_height_bar;
        float targetAngle = (ClientCortisolData.getPlayerCortisol() * 1.8f) - 90f;
        angle += (targetAngle - angle) * 0.1f;

        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, CORTISOL_BAR);

        guiGraphics.blit(CORTISOL_BAR, x_bar, y_bar, 0, 0, display_width_bar, display_height_bar, display_width_bar, display_height_bar);

        //render the arrow
        int display_width_arrow = 30;
        int display_height_arrow = 90;
        int x_arrow = 40;
        int y_arrow = screenHeight-display_height_arrow+37;
        PoseStack pose = guiGraphics.pose();
        pose.pushPose();

        pose.translate(x_arrow + display_width_arrow / 2f, y_arrow + display_height_arrow / 2f, 0);

        pose.mulPose(Axis.ZP.rotationDegrees(angle));

        pose.translate(-x_arrow - display_width_arrow / 2f, -y_arrow - display_height_arrow / 2f, 0);


        guiGraphics.blit(CORTISOL_ARROW, x_arrow, y_arrow, 0, 0, display_width_arrow, display_height_arrow, display_width_arrow, display_height_arrow);
        pose.popPose();


    }
}
