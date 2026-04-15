package net.tech.testmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.tech.testmod.TestMod;
import net.tech.testmod.cortisol.PlayerCortisolProvider;

public class CortisolHudOverlay {
    private static final ResourceLocation CORTISOL_BAR =new ResourceLocation(TestMod.MOD_ID, "/textures/cortisol/cortisol_meter.png");
    private static final ResourceLocation CORTISOL_ARROW =new ResourceLocation(TestMod.MOD_ID, "/textures/cortisol/cortisol_arrow.png");

    public static IGuiOverlay HUD_CORTISOL=((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
       int display_width=150;
       int display_height=120;
        int x= screenWidth - display_width;
        int y = 0;
        float angle= (ClientCortisolData.getPlayerCortisol()*1.8f)-90f;
        System.out.println(angle);
        System.out.println(ClientCortisolData.getPlayerCortisol());


        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, CORTISOL_BAR);

        guiGraphics.blit(CORTISOL_BAR,x,y,0,0, 500,100,display_width,display_height);


        PoseStack pose = guiGraphics.pose();
        pose.pushPose();

        pose.translate(x+display_width/2f, y+display_height / 2f, 0);

        pose.mulPose(Axis.ZP.rotationDegrees(angle));

        pose.translate(-display_width/2f, -display_height/2f, 0);


        guiGraphics.blit(CORTISOL_ARROW,0,0,0,0, 500,100,display_width,display_height);
        pose.popPose();


    });
}
